package ast;

/** Abstract syntax for a variable introduction that
 *  specifies an initial value (via an expression)
 *  for a variable that is brought in to scope via a
 *  VarDecl.
 */
public class InitVarIntro extends VarIntro {

    /** An expression whose that will be evaluated
     *  to provide the initial value for this variable.
     */
    private Expr exp;

    /** Default constructor.
     */
    public InitVarIntro(String name, Expr exp) {
        super(name);
        this.exp = exp;
    }

    /** Print an indented description of this abstract syntax node,
     *  including a name for the node itself at the specified level
     *  of indentation, plus more deeply indented descriptions of
     *  any child nodes.
     */
    public void indent(IndentOutput out, int n) {
        out.indent(n, "InitVarIntro");
        out.indent(n+1, "\"" + name + "\"");
        exp.indent(out, n+1);
    }
}
