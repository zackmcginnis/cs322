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

/** Abstract syntax for return statements.
 */
public class Return extends Stmt {

    /** The value that should be returned (or else null).
     */
    private Expr exp;

    /** Default constructor.
     */
    public Return(Expr exp) {
        this.exp = exp;
    }

    /** This attribute will be filled in during static analysis to
     *  record the type of the value that is to be returned.
     */
    private Type type = null;

    /** Print an indented description of this abstract syntax node,
     *  including a name for the node itself at the specified level
     *  of indentation, plus more deeply indented descriptions of
     *  any child nodes.
     */
    public void indent(IndentOutput out, int n) {
        out.indent(n, (type==null) ? "Return" : ("Return " + type));
        if (exp==null) {
            out.indent(n, "[no return value]");
        } else {
            exp.indent(out, n+1);
        }
    }

    /** Type check this statement, using the specified context, with
     *  the given initial typing environment, and returning the typing
     *  environment for a following statement.
     */
    public TypeEnv check(Context ctxt, TypeEnv locals)
      throws Failure {
        if (ctxt.getRetType()==null) {
            if (exp!=null) { // appears in void function
                ctxt.report(new Failure("ReturnVoidRequired"));
                exp.typeOf(ctxt, locals);  // ignore result
            }
        } else {             // return in non-void function
            if (exp==null) {
                ctxt.report(new Failure("ReturnValueRequired"));
            } else {
                type = ctxt.getRetType();
                exp  = exp.matchType(ctxt, locals, type, "ReturnType");
            }
        }
        return locals;
    }

    /** Return true if this statement can be guaranteed to return,
     *  ensuring that any immediately following statement will not
     *  be executed.
     */
    public boolean guaranteedToReturn() {
        // Return statements signal an exit from the function
        // in which they appear, so we will never continue on
        // to a subsequent statement after a return.
        return true;
    }

    /** Generate LLVM code that will execute this statement and
     *  then continue with the follow on code.
     */
    public Code compile(final llvm.Function fn, final Code andThen) {
        return (exp==null)
             ? new llvm.RetVoid()
             : exp.compile(fn, new ValCont() {
                  Code with(final Value v) {
                      return new llvm.Ret(v);
                  }
               });
    }
}
