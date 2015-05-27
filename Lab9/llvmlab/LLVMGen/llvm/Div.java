package llvm;
import java.io.PrintWriter;

/** Integer division.
 */
public class Div extends BinOp {

    /** Default constructor.
     */
    public Div(Type ty, Value l, Value r) {
        super(ty, l, r);
    }

    /** A helper object for making instances of this class.
     */
    public static final Maker maker = new Maker() {
        public BinOp make(Type ty, Value l, Value r) {
            return new Div(ty, l, r);
        }
    };

    /** Generate a printable string for this instruction.
     */
    public String toString() { return toString("sdiv"); }
}
