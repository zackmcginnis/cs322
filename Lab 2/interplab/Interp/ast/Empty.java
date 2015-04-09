package ast;
import compiler.Failure;

/** Abstract syntax for empty statements.
 */
public class Empty extends Stmt {

    /** Print an indented description of this abstract syntax node,
     *  including a name for the node itself at the specified level
     *  of indentation, plus more deeply indented descriptions of
     *  any child nodes.
     */
    public void indent(IndentOutput out, int n) {
        out.indent(n, "Empty");
    }

    /** Type check this statement, using the specified context, with
     *  the given initial typing environment, and returning the typing
     *  environment for a following statement.
     */
    public TypeEnv check(Context ctxt, TypeEnv locals)
      throws Failure {
        return locals;
    }

    /** Execute this statement using the given arrays to store the
     *  values of global and local variables, respectively, and
     *  returning a boolean true if, and only if this statement
     *  has executed a return.
     */
    public boolean exec(int[] globals, int[] locals) {
        throw new Error("exec not implemented for Empty");
    }
}
