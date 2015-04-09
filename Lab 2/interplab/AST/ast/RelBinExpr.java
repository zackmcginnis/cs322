package ast;

/** An abstract base class for relational binary expressions.
 */
public abstract class RelBinExpr extends BinExpr {

    /** Default constructor.
     */
    public RelBinExpr(Expr left, Expr right) {
        super(left, right);
    }
}
