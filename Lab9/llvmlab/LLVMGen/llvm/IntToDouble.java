package llvm;
import java.io.PrintWriter;

/** The integer to double cast operation.
 */
public class IntToDouble extends CastOp {

    /** Default constructor.
     */
    public IntToDouble(Value v) {
        super(v);
    }

    /** A helper object for making instances of this class.
     */
    public static final Maker maker = new Maker() {
        public CastOp make(Value v) {
            return new IntToDouble(v);
        }
    };

    /** Generate a printable string for this instruction.
     */
    public String toString() {
        return "sitofp " + v + " to " + Type.DOUBLE;
    }
}
