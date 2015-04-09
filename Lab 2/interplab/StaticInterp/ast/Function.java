package ast;
import compiler.Failure;

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
    }

    /** Print an indented description of this definition.
     */
    public void indent(IndentOutput out, int n) {
        out.indent(n, "Function");
        out.indent(n+1, (retType==null) ? "void" : retType.toString());
        out.indent(n+1, "\"" + name + "\", frameSize="+frameSize);
        for (int i=0; i<formals.length; i++) {
            formals[i].indent(out, n+1, i);
        }
        body.indent(out, n+1);
    }

    /** Records the total number of slots that will be used in the
     *  frame (i.e., local variable array) for every call to this
     *  function.  The frame provides space for arguments, local
     *  variables, and return value.  If the function does not use
     *  any of these items, then it will have a zero frame size and
     *  will not require a local variable array.
     */
    private int frameSize = 0;

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
        // Check for duplicate names in the formal parameter list:
        if (Formal.containsRepeats(formals)) {
            ctxt.report(new Failure("FormalsUnique"));
        }
  
        // Build an environment for this function's local variables:
        TypeEnv locals = TypeEnv.empty;
        for (int i=0; i<formals.length; i++) {
            locals = formals[i].extend(locals, frameSize++);
        }
  
        // Type check the body of this function:
        ctxt.retType   = this.retType;
        ctxt.frameSize = frameSize;
        body.check(ctxt, locals);
  
        // Record the final frame size for this function, ensuring that
        // there is space for the return value, if appropriate.
        frameSize = ctxt.frameSize;
        if (retType!=null && frameSize==0) {
            frameSize = 1;
        }
    
        // Check that non-void functions are guaranteed to return:
        if (ctxt.retType!=null && !body.guaranteedToReturn()) {
            ctxt.report(new Failure("MustReturn"));
        }
    }

    /** Check to see if this function would be a valid main function.
     *  We assume that we have already checked that the function's
     *  name is "main", so it just remains to check that the return
     *  type is void, and that it has no formal parameters.
     */
    void checkMain(Context ctxt) {
        if (retType!=null) {
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
}
