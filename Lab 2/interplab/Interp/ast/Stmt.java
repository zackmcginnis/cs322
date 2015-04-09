package ast;
import compiler.Failure;

/** Abstract syntax for statements.
 */
public abstract class Stmt {

    /** Print an indented description of this abstract syntax node,
     *  including a name for the node itself at the specified level
     *  of indentation, plus more deeply indented descriptions of
     *  any child nodes.
     */
    public abstract void indent(IndentOutput out, int n);

    /** Type check this statement, using the specified context, with
     *  the given initial typing environment, and returning the typing
     *  environment for a following statement.
     */
    public abstract TypeEnv check(Context ctxt, TypeEnv locals)
      throws Failure;

    /** Return true if this statement can be guaranteed to return,
     *  ensuring that any immediately following statement will not
     *  be executed.
     */
    public boolean guaranteedToReturn() {
        // Most statement will continue on the next statement
        // after execution, suggesting that a return result of
        // false is a reasonable default here.
        return false;
    }

    /** Execute this statement using the given arrays to store the
     *  values of global and local variables, respectively, and
     *  returning a boolean true if, and only if this statement
     *  has executed a return.
     */
    public abstract boolean exec(int[] globals, int[] locals);
}
