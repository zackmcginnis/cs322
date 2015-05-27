package llvm;
import java.io.PrintWriter;

/** Represents a (two input) phi function.
 */
public class Phi2 extends Rhs {

    /** The first predecessor block.
     */
    private Basic b1;

    /** The value passed in from the first predecessor block.
     */
    private Value v1;

    /** The second predecessor block.
     */
    private Basic b2;

    /** The value passed in from the second predecessor block.
     */
    private Value v2;

    /** Default constructor.
     */
    public Phi2(Basic b1, Value v1, Basic b2, Value v2) {
        this.b1 = b1;
        this.v1 = v1;
        this.b2 = b2;
        this.v2 = v2;
    }

    /** Generate a printable string for this instruction.
     */
    public String toString() {
        return "phi " + v1.getType()
                      + " [ " + v1.getName() + ", " + b1.label() + " ], "
                      + " [ " + v2.getName() + ", " + b2.label() + " ]";
    }
}
