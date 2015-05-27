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

/** Represents a continuation that takes a value representing the
 *  result of a previous calculation and returns a code sequence
 *  that uses that value to complete a computation.
 */
abstract class ValCont {

    abstract Code with(final Value v);
}
