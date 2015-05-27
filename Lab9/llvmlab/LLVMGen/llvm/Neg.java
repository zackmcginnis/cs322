package llvm;
import java.io.PrintWriter;

/** Integer unary minus.
 */
public class Neg extends UnOp {

    /** Default constructor.
     */
    public Neg(Type ty, Value v) {
        super(ty, v);
    }

    /** A helper object for making instances of this class.
     */
    public static final Maker maker = new Maker() {
        public UnOp make(Type ty, Value v) {
            return new Neg(ty, v);
        }
    };

    /** Generate a printable string for this instruction.
     */
    public String toString() {
        return "sub " + v.getType() + " 0, " + v.getName();
    }
}
