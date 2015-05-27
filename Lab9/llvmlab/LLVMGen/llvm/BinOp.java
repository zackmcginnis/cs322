package llvm;
import java.io.PrintWriter;

/** Represents binary operators, where op is add ty, fadd ty, ....
 */
public abstract class BinOp extends Rhs {

    /** The type of the result.
     */
    protected Type ty;

    /** The left operand.
     */
    private Value l;

    /** The right operand.
     */
    private Value r;

    /** Default constructor.
     */
    public BinOp(Type ty, Value l, Value r) {
        this.ty = ty;
        this.l = l;
        this.r = r;
    }

    /** A single method class whose instances can be used as
     *  helpers to build BinOp objects.
     */
    public static abstract class Maker {

        public abstract BinOp make(Type ty, Value l, Value r);
    }

    /** Generate a string for this right hand side using the given string
     *  as the operation name.
     */
    public String toString(String op) {
        return op + " " + ty + " " + l.getName() + ", " + r.getName();
    }
}
