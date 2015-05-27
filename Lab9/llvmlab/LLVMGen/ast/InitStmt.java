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

/** An abstract base class for initializers (i.e., statments that can
 *  be used as loop initializers).
 */
public abstract class InitStmt extends Stmt {
}
