package ast;

/** Abstract syntax for variable references.
 */
public class Id extends Expr {

    /** The name of this identifier/variable reference.
     */
    private String name;

    /** Default constructor.
     */
    public Id(String name) {
        this.name = name;
    }

    /** Print an indented description of this abstract syntax node,
     *  including a name for the node itself at the specified level
     *  of indentation, plus more deeply indented descriptions of
     *  any child nodes.
     */
    public void indent(IndentOutput out, int n) {
        out.indent(n, "Id(\"" + name + "\")");
    }
}
