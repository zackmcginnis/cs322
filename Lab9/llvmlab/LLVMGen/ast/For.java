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

/** Abstract syntax for for loops.
 */
public class For extends Stmt {

    /** The initialization expression.
     */
    private StmtExpr init;

    /** The test portion of this loop.
     */
    private Expr test;

    /** The step portion of this for loop.
     */
    private StmtExpr step;

    /** The body of this loop.
     */
    private Stmt body;

    /** Default constructor.
     */
    public For(StmtExpr init, Expr test, StmtExpr step, Stmt body) {
        this.init = init;
        this.test = test;
        this.step = step;
        this.body = body;
    }

    /** Print an indented description of this abstract syntax node,
     *  including a name for the node itself at the specified level
     *  of indentation, plus more deeply indented descriptions of
     *  any child nodes.
     */
    public void indent(IndentOutput out, int n) {
        out.indent(n, "For");
  
        if (init!=null) {
           init.indent(out, n+1);
        } else {
            out.indent(n+1, "No init");
        }
  
        if (test!=null) {
           test.indent(out, n+1);
        } else {
            out.indent(n+1, "No test");
        }
  
        if (step!=null) {
           step.indent(out, n+1);
        } else {
            out.indent(n+1, "No step");
        }
  
        body.indent(out, n+1);
    }

    /** Type check this statement, using the specified context, with
     *  the given initial typing environment, and returning the typing
     *  environment for a following statement.
     */
    public TypeEnv check(Context ctxt, TypeEnv locals)
      throws Failure {
        if (init!=null) {
            init.check(ctxt, locals);
        }
        if (test!=null) {
            try {
                if (!test.typeOf(ctxt, locals).equals(Type.BOOLEAN)) {
                    ctxt.report(new Failure("WhileBoolean"));
                }
            } catch (Failure f) {
                ctxt.report(f);
            }
        }
        if (step!=null) {
            step.check(ctxt, locals);
        }
        body.check(ctxt, locals); // discard final environment
        return locals;
    }

    /** Generate LLVM code that will execute this statement and
     *  then continue with the follow on code.
     */
    public Code compile(final llvm.Function fn, final Code andThen) {
        // Remember to allow for the possibility that
        // init, test, and step could each be null ...
        throw new Error("compile not implemented for For");
    }
}
