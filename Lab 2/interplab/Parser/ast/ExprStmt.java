package ast;

/** Abstract syntax for expressions that can be used as
 *  statements.
 */
public class ExprStmt extends InitStmt {

    private StmtExpr exp;

    /** Default constructor.
     */
    public ExprStmt(StmtExpr exp) {
        this.exp = exp;
    }

    /** Print an indented description of this abstract syntax node,
     *  including a name for the node itself at the specified level
     *  of indentation, plus more deeply indented descriptions of
     *  any child nodes.
     */
    public void indent(IndentOutput out, int n) {
        out.indent(n, "ExprStmt");
        exp.indent(out, n+1);
    }
}
