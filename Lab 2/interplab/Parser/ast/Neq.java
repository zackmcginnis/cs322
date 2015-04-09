package ast;

/** Abstract syntax for the != comparison operator.
 */
public class Neq extends RelBinExpr {

    /** Default constructor.
     */
    public Neq(Expr left, Expr right) {
        super(left, right);
    }

    /** Return a string that provides a simple description of this
     *  particular type of operator node.
     */
    String label() { return "Neq"; }
}
