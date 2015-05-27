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

/** An abstract base class for cast expressions (i.e.,
 *  expressions that take an operand of one type and
 *  convert the resulting value in to a different type).
 */
public abstract class CastExpr extends Expr {

    /** The operand for the cast.
     */
    protected Expr exp;

    /** Default constructor.
     */
    public CastExpr(Expr exp) {
        this.exp = exp;
    }

    /** Captures a general pattern for compiling a cast operator by evaluating 
     *  this expression and then applying the specified LLVM primitive.
     */
    public Code cast(final llvm.Function fn, final llvm.CastOp.Maker op, final ValCont k) {
        return exp.compile(fn, new ValCont() {
            Code with(final Value v) {
                Reg r = fn.reg(v.getType());
                return new llvm.Op(r, op.make(v), k.with(r));
            }
        });
    }
}
