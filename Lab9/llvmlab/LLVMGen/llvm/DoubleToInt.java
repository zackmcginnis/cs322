package llvm;
import java.io.PrintWriter;

/** The double to integer cast operation.
 */
public class DoubleToInt extends CastOp {

    /** Default constructor.
     */
    public DoubleToInt(Value v) {
        super(v);
    }

    /** A helper object for making instances of this class.
     */
    public static final Maker maker = new Maker() {
        public CastOp make(Value v) {
            return new DoubleToInt(v);
        }
    };

    /** Generate a printable string for this instruction.
     */
    public String toString() {
        return "fptosi " + v + " to " + Type.INT;
    }
}
