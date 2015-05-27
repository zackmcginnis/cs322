package llvm;
import java.io.PrintWriter;

/** Represents cast operators.
 */
public abstract class CastOp extends Rhs {

    /** The value to be recast.
     */
    protected Value v;

    /** Default constructor.
     */
    public CastOp(Value v) {
        this.v = v;
    }

    /** A single method class whose instances can be used as
     *  helpers to build CastOp objects.
     */
    public static abstract class Maker {

        public abstract CastOp make(Value v);
    }
}
