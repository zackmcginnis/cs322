package llvm;
import java.io.PrintWriter;

/** Floating point unary minus.
 */
public class FNeg extends UnOp {

    /** Default constructor.
     */
    public FNeg(Type ty, Value v) {
        super(ty, v);
    }

    /** A helper object for making instances of this class.
     */
    public static final Maker maker = new Maker() {
        public UnOp make(Type ty, Value v) {
            return new FNeg(ty, v);
        }
    };

    /** Generate a printable string for this instruction.
     */
    public String toString() {
        return "fsub " + v.getType() + " 0.0, " + v.getName();
    }
}
