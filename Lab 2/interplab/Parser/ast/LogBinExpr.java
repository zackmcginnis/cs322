package ast;

/** An abstract base class for logical binary expressions.
 */
public abstract class LogBinExpr extends BinExpr {

    /** Default constructor.
     */
    public LogBinExpr(Expr left, Expr right) {
        super(left, right);
    }
}
