package ast;

/** An abstract base class for arithmetic binary expressions.
 */
public abstract class ArithBinExpr extends BinExpr {

    /** Default constructor.
     */
    public ArithBinExpr(Expr left, Expr right) {
        super(left, right);
    }
}
