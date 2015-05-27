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

/** Abstract syntax for a unary minus operation.
 */
public class UMinus extends Expr {

    /** The expression to be negated.
     */
    private Expr exp;

    /** Default constructor.
     */
    public UMinus(Expr exp) {
        this.exp = exp;
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
        out.indent(n, "UMinus");
        exp.indent(out, n+1);
    }

    /** Calculate the type of this expression, using the given context
     *  and type environment.
     */
    public Type typeOf(Context ctxt, TypeEnv locals)
      throws Failure {
        type = exp.typeOf(ctxt, locals);
        if (!type.isNumeric()) {
            throw new Failure("UMinusNumeric");
        }
        return type;
    }

    /** Generate LLVM code that will evaluate this expression,
     *  and then pass the resulting value on to the following
     *  code, represented by the continuation argument.
     */
    public Code compile(final llvm.Function fn, final ValCont k) {
        llvm.UnOp.Maker m = (type==Type.DOUBLE) ? llvm.FNeg.maker : llvm.Neg.maker;
        return exp.unary(fn, type.toLLVM(), m, k);
    }
}
