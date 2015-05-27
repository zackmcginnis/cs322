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

/** Abstract syntax for the logical AND operator, &&.
 */
public class LAnd extends LogBinExpr {

    /** Default constructor.
     */
    public LAnd(Expr left, Expr right) {
        super(left, right);
    }

    /** Return a string that provides a simple description of this
     *  particular type of operator node.
     */
    String label() { return "LAnd"; }

    /** Generate LLVM code that will evaluate this expression,
     *  and then pass the resulting value on to the following
     *  code, represented by the continuation argument.
     */
    public Code compile(final llvm.Function fn, final ValCont k) {
        return left.compile(fn, new ValCont() {
            Code with(final Value lv) {
                final Basic first = fn.block();
                final Basic join  = fn.block();
                Code  evalRight   = right.compile(fn, new ValCont() {
                    Code with(final Value rv) {
                        Basic second = fn.block(new llvm.Goto(join));
                        Rhs   merge  = new llvm.Phi2(first, lv, second, rv);
                        Reg   r      = fn.reg(llvm.Type.BOOLEAN);
                        join.set(new llvm.Op(r, merge, k.with(r)));
                        return new llvm.Goto(second);
                    }
                });
                first.set(new llvm.Cond(lv, fn.block(evalRight), join));
                return new llvm.Goto(first);
            }
        });
    }
}
