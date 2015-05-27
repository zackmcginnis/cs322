package llvm;
import java.io.PrintWriter;

/** Represents an LLVM double precision floating point constant value.
 */
public class DoubleVal extends Value {

    /** The numeric value associated with this DoubleVal.
     */
    private double num;

    /** Default constructor.
     */
    public DoubleVal(double num) {
        this.num = num;
    }

    /** Return the LLVM type of this value.
     */
    public Type getType() { return Type.DOUBLE; }

    /** Return the LLVM name for this value.
     */
    public String getName() { return Double.toString(num); }
}
