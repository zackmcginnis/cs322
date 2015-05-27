package ast;
import compiler.Failure;
import java.io.PrintWriter;
import llvm.Basic;
import llvm.Code;
import llvm.Global;
import llvm.Local;
import llvm.Location;
import llvm.Reg;
import llvm.Rhs;
import llvm.Value;
import java.util.HashMap;

/** Abstract syntax for function definitions.
 */
public class Function extends Defn {

    /** The return type of this function (or null for
     *  a procedure/void function).
     */
    private Type retType;

    /** The name of this function.
     */
    private String name;

    /** The formal parameters for this function.
     */
    private Formal[] formals;

    /** The body of this function.
     */
    private Stmt body;

    /** Default constructor.
     */
    public Function(Type retType, String name, Formal[] formals, Stmt body) {
        this.retType = retType;
        this.name = name;
        this.formals = formals;
        this.body = body;
    
        localVars = new HashMap<String,Local>();
    }

    /** Print an indented description of this definition.
     */
    public void indent(IndentOutput out, int n) {
        out.indent(n, "Function");
        out.indent(n+1, (retType==null) ? "void" : retType.toString());
        out.indent(n+1, "\"" + name + "\"");
        for (int i=0; i<formals.length; i++) {
            formals[i].indent(out, n+1);
        }
        body.indent(out, n+1);
    }

    /** Records the set of local variables defined in this function.
     */
    private HashMap< String, Local > localVars;

    /** Return a local "address" for the named variable with the given
     *  type that is unique within this function.
     */
    Local addLocal(String name, Type type) {
        String unique = name;
        if (localVars.containsKey(unique)) {
           int i = 0;
           do {
               unique = name + i++;
           } while (localVars.containsKey(unique));
        }
        Local loc = new Local(type.toLLVM().ptr(), unique);
        localVars.put(unique, loc);
        return loc;
    }

    /** Return the return type of this function.
     */
    public Type getRetType() {
        return retType;
    }

    /** Extend the environments in the given context with entries from
     *  this definition.
     */
    void extendGlobalEnv(Context ctxt)
      throws Failure {
        // Check that there is no previously defined function with
        // the same name:
        if (FunctionEnv.find(name, ctxt.functions)!=null) {
            ctxt.report(new Failure("FunctionNamesUnique"));
        }
  
        // Extend the function environment with a new entry for this
        // definition:
        ctxt.functions = new FunctionEnv(name, this, ctxt.functions);
    }

    /** Check that this is a valid function definition.
     */
    void check(Context ctxt)
      throws Failure {
        // Make a note of the current Function.
        ctxt.current = this;
  
        // Check for duplicate names in the formal parameter list:
        if (Formal.containsRepeats(formals)) {
            ctxt.report(new Failure("FormalsUnique"));
        }
  
        // Build an environment for this function's local variables:
        TypeEnv locals = TypeEnv.empty;
        for (int i=0; i<formals.length; i++) {
            locals = formals[i].extend(ctxt, locals);
        }
  
        // Type check the body of this function:
        body.check(ctxt, locals);
    
        // Check that non-void functions are guaranteed to return:
        if (ctxt.getRetType()!=null && !body.guaranteedToReturn()) {
            ctxt.report(new Failure("MustReturn"));
        }
    }

    /** Check to see if this function would be a valid main function.
     *  We assume that we have already checked that the function's
     *  name is "main", so it just remains to check that the return
     *  type is void, and that it has no formal parameters.
     */
    void checkMain(Context ctxt) {
        if (ctxt.getRetType()!=null) {
            ctxt.report(new Failure("MainVoid"));
        }
        if (formals.length!=0) {
            ctxt.report(new Failure("MainNoParameters"));
        }
    }

    /** Check that the given list of arguments is valid for a
     *  call to this function, and then return the resulting
     *  argument type.
     */
    Type checkArgs(Context ctxt, TypeEnv locals, Expr[] args)
      throws Failure {
        if (args.length != formals.length) {
            throw new Failure("CallNumberOfArgs");
        }
        for (int i=0; i<formals.length; i++) {
            args[i] = args[i].matchType(ctxt,
                                        locals,
                                        formals[i].getType(),
                                        "FormalTypeMismatch");
        }
        return retType;
    }

    /** Generate code for function definitions.
     */
    public void generateCode(PrintWriter out) {
        llvm.Formal[] fms  = new llvm.Formal[formals.length];
        llvm.Type     rt   = (retType==null) ? null : retType.toLLVM();
        llvm.Function fn   = new llvm.Function(rt, name, fms);
        Code          code = body.compile(fn, new llvm.RetVoid());
  
        // Add code to save parameters in stack frame:
        for (int i=formals.length-1; i>=0; i--) {
            fms[i] = formals[i].toLLVM();
            code   = new llvm.Store(fms[i].getParam(), fms[i], code);
        }
  
        // Add stack frame slots for all local variables, including parameters:
        for (Local loc : localVars.values()) {
            code = new llvm.Op(loc, new llvm.Alloca(loc.getType().ptsTo()), code);
        }
        fn.block("entry", code);
        fn.print(out);
    }

    /** Generate LLVM code for a function call that appears in an expression
     *  where the final result will be passed on for possible use in the
     *  following computation.
     */
    Code compile(final llvm.Function fn, Expr[] actuals, final ValCont k) {
        llvm.Type ty   = retType.toLLVM();
        Value[]   args = new Value[actuals.length];
        Rhs       call = new llvm.Call(ty, name, args);
        Reg       r    = fn.reg(ty);
        return addArgs(fn, actuals, args, new llvm.Op(r, call, k.with(r)));
    }

    /** Generate LLVM code for a function call whose result, if any, will not
     *  be used in the following computation.  This requires two cases to
     *  allow for the possibility of calling a void function or calling a
     *  function whose return value will be discarded.
     */
    Code compile(final llvm.Function fn, Expr[] actuals, final Code andThen) {
        Value[] args = new Value[actuals.length];
        Code    code;
        if (retType==null) {
            code = new llvm.CallVoid(name, args, andThen);
        } else {
            llvm.Type ty   = retType.toLLVM();
            Rhs       call = new llvm.Call(ty, name, args);
            code           = new llvm.Op(fn.reg(ty), call, andThen);
        }
        return addArgs(fn, actuals, args, code);
    }

    /** Generate code to evaluate the arguments for a function call prior to
     *  subsequent code, including the actual function call.
     */
    Code addArgs(llvm.Function fn, Expr[] actuals, final Value[] args, Code code) {
        for (int i=actuals.length-1; i>=0; i--) {
            final int  j = i;
            final Code c = code;
            code = actuals[i].compile(fn, new ValCont() {
                       Code with(Value v) {
                          args[j] = v;
                          return c;
                       }
                   });
        }
        return code;
    }
}
