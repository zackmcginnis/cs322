package ast;

/** Abstract syntax for integer literals.
 */
public class IntLit extends Expr {

    /** The numeric value of this integer literal.
     */
    private int val;

    /** Default constructor.
     */
    public IntLit(int val) {
        this.val = val;
    }

    /** Print an indented description of this abstract syntax node,
     *  including a name for the node itself at the specified level
     *  of indentation, plus more deeply indented descriptions of
     *  any child nodes.
     */
    public void indent(IndentOutput out, int n) {
        out.indent(n, "IntLit(" + val + ")");
    }
}
