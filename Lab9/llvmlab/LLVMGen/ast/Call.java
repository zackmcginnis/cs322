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

public class Call extends StmtExpr {

    /** The name of the function that is being called.
     */
    private String name;

    /** The sequence of expressions provided as arguments.
     */
    private Expr[] args;

    /** Default constructor.
     */
    public Call(String name, Expr[] args) {
        this.name = name;
        this.args = args;
    }

    /** Print an indented description of this abstract syntax node,
     *  including a name for the node itself at the specified level
     *  of indentation, plus more deeply indented descriptions of
     *  any child nodes.
     */
    public void indent(IndentOutput out, int n) {
        out.indent(n, "Call");
        out.indent(n+1, "\"" + name + "\"");
        for (int i=0; i<args.length; i++) {
            args[i].indent(out, n+1);
        }
    }

    /** Records the Function object associated with this call.
     */
    private Function f;

    /** Calculate the type of this expression, using the given context
     *  and type environment.
     */
    public Type typeOf(Context ctxt, TypeEnv locals)
      throws Failure {
        Type rt = check(ctxt, locals);
        if (rt==null) {
            throw new Failure("CallReturnType");
        }
        return rt;
    }

    /** Type check this statement expression, using the specified
     *  context and the given typing environment.
     */
    public Type check(Context ctxt, TypeEnv locals)
      throws Failure {
        if (ctxt.current==null) {
            throw new Failure("GlobalsNoCalls");
        }
        FunctionEnv fe = FunctionEnv.find(name, ctxt.functions);
        if (fe==null) {
            throw new Failure("FunctionDefined");
        }
  
        // Record the function object associated with this name:
        f = fe.getFunction();
  
        // And then check the rest of the call:
        return f.checkArgs(ctxt, locals, args);
    }

    /** Generate code to evaluate this expression and discard any result
     *  that it produces.
     */
    Code compileDiscard(final llvm.Function fn, final Code andThen) {
        return f.compile(fn, args, andThen);
    }

    /** Generate LLVM code that will evaluate this expression,
     *  and then pass the resulting value on to the following
     *  code, represented by the continuation argument.
     */
    public Code compile(final llvm.Function fn, final ValCont k) {
        return f.compile(fn, args, k);
    }
}
