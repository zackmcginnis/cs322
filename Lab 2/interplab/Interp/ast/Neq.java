package ast;
import compiler.Failure;

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

    /** Evaluate this expression, returning the result as an integer.
     *  Booleans are encoded as false=0, true=nonzero.  Two arrays are
     *  passed in as arguments to supply the values for global and local
     *  variables, respectively.
     */
    public int eval(int[] globals, int[] locals) {
        return b2i(left.eval(globals, locals) != right.eval(globals, locals));
    }
}
