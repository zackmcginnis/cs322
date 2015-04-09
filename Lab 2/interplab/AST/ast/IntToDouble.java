package ast;

/** Abstract syntax for an int to double cast operation.
 */
public class IntToDouble extends CastExpr {

    /** Default constructor.
     */
    public IntToDouble(Expr exp) {
        super(exp);
    }

    /** Print an indented description of this abstract syntax node,
     *  including a name for the node itself at the specified level
     *  of indentation, plus more deeply indented descriptions of
     *  any child nodes.
     */
    public void indent(IndentOutput out, int n) {
        out.indent(n, "IntToDouble");
        exp.indent(out, n+1);
    }
}
