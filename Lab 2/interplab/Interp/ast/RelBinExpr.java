package ast;
import compiler.Failure;

/** An abstract base class for relational binary expressions.
 */
public abstract class RelBinExpr extends BinExpr {

    /** Default constructor.
     */
    public RelBinExpr(Expr left, Expr right) {
        super(left, right);
    }

    /** Calculate the type of this expression, using the given context
     *  and type environment.
     */
    public Type typeOf(Context ctxt, TypeEnv locals)
      throws Failure {
        type = matchTypes(ctxt, locals);
        if (type==null) {
            ctxt.report(new Failure("RelBinArgs"));
        }
        return Type.BOOLEAN;
    }
}
