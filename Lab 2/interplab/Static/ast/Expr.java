package ast;
import compiler.Failure;

/** An abstract syntax base class for expressions.
 */
public abstract class Expr {

    /** Print an indented description of this abstract syntax node,
     *  including a name for the node itself at the specified level
     *  of indentation, plus more deeply indented descriptions of
     *  any child nodes.
     */
    public abstract void indent(IndentOutput out, int n);

    /** Calculate the type of this expression, using the given context
     *  and type environment.
     */
    public abstract Type typeOf(Context ctxt, TypeEnv locals)
      throws Failure;

    public Expr matchType(Context ctxt, TypeEnv locals, Type reqd, String err)
      throws Failure {
        Type actual = typeOf(ctxt, locals);
        if (reqd.equals(Type.INT) && actual.equals(Type.DOUBLE)) {
            return new DoubleToInt(this);
        } else if (reqd.equals(Type.DOUBLE) && actual.equals(Type.INT)) {
            return new IntToDouble(this);
        } else if (!reqd.equals(actual)) {
            ctxt.report(new Failure(err));
        }
        return this;
    }
}
