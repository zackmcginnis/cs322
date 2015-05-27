package llvm;
import java.io.PrintWriter;

/** Represents unary operators.
 */
public abstract class UnOp extends Rhs {

    /** The type of the result.
     */
    protected Type ty;

    /** The type of the result.
     */
    protected Value v;

    /** Default constructor.
     */
    public UnOp(Type ty, Value v) {
        this.ty = ty;
        this.v = v;
    }

    /** A single method class whose instances can be used as
     *  helpers to build UnOp objects.
     */
    public static abstract class Maker {

        public abstract UnOp make(Type ty, Value v);
    }
}
