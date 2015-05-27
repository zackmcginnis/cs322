package llvm;
import java.io.PrintWriter;

/** Integer greater than or equal test.
 */
public class Gte extends BinOp {

    /** Default constructor.
     */
    public Gte(Type ty, Value l, Value r) {
        super(ty, l, r);
    }

    /** A helper object for making instances of this class.
     */
    public static final Maker maker = new Maker() {
        public BinOp make(Type ty, Value l, Value r) {
            return new Gte(ty, l, r);
        }
    };

    /** Generate a printable string for this instruction.
     */
    public String toString() { return toString("icmp sge"); }
}
