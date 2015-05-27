package llvm;
import java.io.PrintWriter;

/** Load a value from memory.
 */
public class Load extends Rhs {

    /** A location from which a value will be loaded.
     */
    Location v;

    /** Default constructor.
     */
    public Load(Location v) {
        this.v = v;
    }

    /** Generate a printable string for this instruction.
     */
    public String toString() {
        return "load " + v + ", align " + v.getType().ptsTo().getAlign();
    }
}
