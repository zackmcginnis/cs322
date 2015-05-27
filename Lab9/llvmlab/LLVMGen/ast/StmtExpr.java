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

/** An abstract base class for expressions that can be used in a
 *  a statement (i.e., for expressions that might have a side-effect).
 */
public abstract class StmtExpr extends Expr {

    /** Type check this statement expression, using the specified
     *  context and the given typing environment.
     */
    public abstract Type check(Context ctxt, TypeEnv locals)
      throws Failure;

    /** Generate code to evaluate this expression and discard any result
     *  that it produces.
     */
    Code compileDiscard(final llvm.Function fn, final Code andThen) {
        return compile(fn, new ValCont() {
            Code with(final Value v) {
               return andThen;
            }
        });
    }
}
