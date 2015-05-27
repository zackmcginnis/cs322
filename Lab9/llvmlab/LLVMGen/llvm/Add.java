package llvm;
import java.io.PrintWriter;

/** Integer addition.
 */
public class Add extends BinOp {

    /** Default constructor.
     */
    public Add(Type ty, Value l, Value r) {
        super(ty, l, r);
    }

    /** A helper object for making instances of this class.
     */
    public static final Maker maker = new Maker() {
        public BinOp make(Type ty, Value l, Value r) {
            return new Add(ty, l, r);
        }
    };

    /** Generate a printable string for this instruction.
     */
    public String toString() { return toString("add");  }
}
