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

/** An abstract syntax base class for expressions.
 */
public abstract class Expr {

    /** Print an indented description of this abstract syntax node,
     *  including a name for the node itself at the specified level
     *  of indentation, plus more deeply indented descriptions of
     *  any child nodes.
     */
    public abstract void indent(IndentOutput out, int n);

    /** Calculate the type of this expression, using the given context
     *  and type environment.
     */
    public abstract Type typeOf(Context ctxt, TypeEnv locals)
      throws Failure;

    /** A helper function that supports type checking of numeric values with
     *  implicit coercion between integer and double types.  This methods is
     *  used to support type checking of assignments, calls, and return statements.
     */
    public Expr matchType(Context ctxt, TypeEnv locals, Type reqd, String err)
      throws Failure {
        Type actual = typeOf(ctxt, locals);
        if (reqd.equals(Type.INT) && actual.equals(Type.DOUBLE)) {
            return new DoubleToInt(this);
        } else if (reqd.equals(Type.DOUBLE) && actual.equals(Type.INT)) {
            return new IntToDouble(this);
        } else if (!reqd.equals(actual)) {
            ctxt.report(new Failure(err));
        }
        return this;
    }

    /** Generate LLVM code that will evaluate this expression,
     *  and then pass the resulting value on to the following
     *  code, represented by the continuation argument.
     */
    public abstract Code compile(final llvm.Function fn, final ValCont k);

    /** Captures a general pattern for compiling a unary operator by evaluating
     *  this expression and then applying the specified LLVM primitive.
     */
    public Code unary(final llvm.Function fn, final llvm.Type ty, final llvm.UnOp.Maker op, final ValCont k) {
        return compile(fn, new ValCont() {
            Code with(final Value v) {
                Reg r = fn.reg(ty);
                return new llvm.Op(r, op.make(ty, v), k.with(r));
            }
        });
    }
}
