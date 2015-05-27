package llvm;
import java.io.PrintWriter;

/** Floating point equality test.
 */
public class FEql extends BinOp {

    /** Default constructor.
     */
    public FEql(Type ty, Value l, Value r) {
        super(ty, l, r);
    }

    /** A helper object for making instances of this class.
     */
    public static final Maker maker = new Maker() {
        public BinOp make(Type ty, Value l, Value r) {
            return new FEql(ty, l, r);
        }
    };

    /** Generate a printable string for this instruction.
     */
    public String toString() { return toString("fcmp oeq"); }
}
