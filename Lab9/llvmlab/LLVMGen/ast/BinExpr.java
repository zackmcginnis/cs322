package ast;
import compiler.Failure;
import java.io.PrintWriter;
import llvm.Basic;
import llvm.Code;
import llvm.Global;
import llvm.Local;
import llvm.Location;
import llvm.Reg;
import llvm.Rhs;
import llvm.Value;

/** An abstract base class for binary expressions (i.e.,
 *  expressions that have a left and a right operand).
 */
public abstract class BinExpr extends Expr {

    /** The left subexpression.
     */
    protected Expr left;

    /** The right subexpression.
     */
    protected Expr right;

    /** Default constructor.
     */
    public BinExpr(Expr left, Expr right) {
        this.left = left;
        this.right = right;
    }

    /** This attribute will be filled in during static analysis to record
     *  the type of the arguments for this operator (both left and right
     *  arguments are required to have the same type).  This information
     *  will be useful for situations in code generation where we need
     *  to distinguish between using an integer or a floating point
     *  version of an operation.  The type attribute is set to null when
     *  a node is first created to indicate that the type has yet
     *  to be determined.
     */
    protected Type type = null;

    /** Print an indented description of this abstract syntax node,
     *  including a name for the node itself at the specified level
     *  of indentation, plus more deeply indented descriptions of
     *  any child nodes.
     */
    public void indent(IndentOutput out, int n) {
        out.indent(n, (type==null) ? label() : (label() + " " + type));
        left.indent(out, n+1);
        right.indent(out, n+1);
    }

    /** Return a string that provides a simple description of this
     *  particular type of operator node.
     */
    abstract String label();

    /** A helper function for type checking binary arithmetic operations that
     *  allow two numeric arguments with implicit casting from integer to double
     *  values.
     */
    protected Type matchTypes(Context ctxt, TypeEnv locals)
      throws Failure {
        Type lt = left.typeOf(ctxt, locals);
        Type rt = right.typeOf(ctxt, locals);
        if (lt.equals(rt)) {
            return lt;
        } else if (lt.equals(Type.INT) && rt.equals(Type.DOUBLE)) {
            left = new IntToDouble(left);
            return Type.DOUBLE;
        } else if (lt.equals(Type.DOUBLE) && rt.equals(Type.INT)) {
            right = new IntToDouble(right);
            return Type.DOUBLE;
        }
        return null;
    }

    /** Captures a general pattern for compiling a binary operator by evaluating
     *  each of the two arguments and then applying the specified LLVM primitive.
     *  Note that we include two operators, one for use when an integer result is
     *  required and for use when a floating point result is expected.
     */
    public Code binary(final llvm.Function fn, final llvm.BinOp.Maker iop, final llvm.BinOp.Maker fop, final ValCont k) {
        return left.compile(fn, new ValCont() {
            Code with(final Value lv) {
                return right.compile(fn, new ValCont() {
                    Code with(final Value rv) {
                        llvm.Type        ty = type.toLLVM();
                        Reg              r  = fn.reg(ty);
                        llvm.BinOp.Maker op = (type==Type.DOUBLE) ? fop : iop;
                        return new llvm.Op(r, op.make(ty, lv, rv), k.with(r));
                    }
                });
            }
        });
    }
}
