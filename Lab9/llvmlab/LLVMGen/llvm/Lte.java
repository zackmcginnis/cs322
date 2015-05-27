package llvm;
import java.io.PrintWriter;

/** Integer less than or equal test..
 */
public class Lte extends BinOp {

    /** Default constructor.
     */
    public Lte(Type ty, Value l, Value r) {
        super(ty, l, r);
    }

    /** A helper object for making instances of this class.
     */
    public static final Maker maker = new Maker() {
        public BinOp make(Type ty, Value l, Value r) {
            return new Lte(ty, l, r);
        }
    };

    /** Generate a printable string for this instruction.
     */
    public String toString() { return toString("icmp sle"); }
}
