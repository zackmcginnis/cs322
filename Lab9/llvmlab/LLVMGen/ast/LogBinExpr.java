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

/** An abstract base class for logical binary expressions.
 */
public abstract class LogBinExpr extends BinExpr {

    /** Default constructor.
     */
    public LogBinExpr(Expr left, Expr right) {
        super(left, right);
    }

    /** Calculate the type of this expression, using the given context
     *  and type environment.
     */
    public Type typeOf(Context ctxt, TypeEnv locals)
      throws Failure {
        // Find the type of the left operand:
        Type leftType = left.typeOf(ctxt, locals);
  
        // Check that the left operand produces a value of type boolean:
        if (!leftType.equals(Type.BOOLEAN)) {
            ctxt.report(new Failure("LogBinArgsBoolean"));
        }
  
        // Find the type of the right operand:
        Type rightType = right.typeOf(ctxt, locals);
  
        // Check that the right operand produces a value of type boolean:
        if (!rightType.equals(Type.BOOLEAN)) {
            ctxt.report(new Failure("LogBinArgsBoolean"));
        }
  
        // Logical operators produce results of type boolean:
        return type=Type.BOOLEAN;
    }
}
