package ast;
import compiler.Failure;

/** Abstract syntax for while statements.
 */
public class While extends Stmt {

    /** The test expression.
     */
    private Expr test;

    /** The body of this loop.
     */
    private Stmt body;

    /** Default constructor.
     */
    public While(Expr test, Stmt body) {
        this.test = test;
        this.body = body;
    }

    /** Print an indented description of this abstract syntax node,
     *  including a name for the node itself at the specified level
     *  of indentation, plus more deeply indented descriptions of
     *  any child nodes.
     */
    public void indent(IndentOutput out, int n) {
        out.indent(n, "While");
        test.indent(out, n+1);
        body.indent(out, n+1);
    }

    /** Type check this statement, using the specified context, with
     *  the given initial typing environment, and returning the typing
     *  environment for a following statement.
     */
    public TypeEnv check(Context ctxt, TypeEnv locals)
      throws Failure {
        try {
            if (!test.typeOf(ctxt, locals).equals(Type.BOOLEAN)) {
                ctxt.report(new Failure("WhileBoolean"));
            }
        } catch (Failure f) {
            ctxt.report(f);
        }
        body.check(ctxt, locals); // discard final environment
        return locals;
    }

    /** Execute this statement using the given arrays to store the
     *  values of global and local variables, respectively, and
     *  returning a boolean true if, and only if this statement
     *  has executed a return.
     */
    public boolean exec(int[] globals, int[] locals) {
        throw new Error("exec not implemented for While");
    }
}
