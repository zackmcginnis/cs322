package llvm;
import java.io.PrintWriter;

/** Integer equality test.
 */
public class Eql extends BinOp {

    /** Default constructor.
     */
    public Eql(Type ty, Value l, Value r) {
        super(ty, l, r);
    }

    /** A helper object for making instances of this class.
     */
    public static final Maker maker = new Maker() {
        public BinOp make(Type ty, Value l, Value r) {
            return new Eql(ty, l, r);
        }
    };

    /** Generate a printable string for this instruction.
     */
    public String toString() { return toString("icmp eq");  }
}
