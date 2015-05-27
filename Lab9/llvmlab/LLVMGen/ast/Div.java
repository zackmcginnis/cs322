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

/** Abstract syntax for the division operator, /.
 */
public class Div extends ArithBinExpr {

    /** Default constructor.
     */
    public Div(Expr left, Expr right) {
        super(left, right);
    }

    /** Return a string that provides a simple description of this
     *  particular type of operator node.
     */
    String label() { return "Div"; }

    /** Generate LLVM code that will evaluate this expression,
     *  and then pass the resulting value on to the following
     *  code, represented by the continuation argument.
     */
    public Code compile(final llvm.Function fn, final ValCont k) {
        return binary(fn, llvm.Div.maker, llvm.FDiv.maker, k);
    }
}
