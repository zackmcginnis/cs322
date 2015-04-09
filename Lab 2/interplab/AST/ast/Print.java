package ast;

/** Abstract syntax for print statements.
 */
public class Print extends Stmt {

    /** The value that should be printed out.
     */
    private Expr exp;

    /** Default constructor.
     */
    public Print(Expr exp) {
        this.exp = exp;
    }

    /** This attribute will be filled in during static analysis to
     *  record the type of the value that is to be printed.
     */
    private Type type = null;

    /** Print an indented description of this abstract syntax node,
     *  including a name for the node itself at the specified level
     *  of indentation, plus more deeply indented descriptions of
     *  any child nodes.
     */
    public void indent(IndentOutput out, int n) {
        out.indent(n, (type==null) ? "Print" : ("Print " + type));
        exp.indent(out, n+1);
    }
}
