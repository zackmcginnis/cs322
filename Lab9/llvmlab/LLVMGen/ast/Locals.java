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

/** Abstract syntax for local variable declarations.
 */
public class Locals extends InitStmt {

    /** The type of the declared variables.
     */
    private Type type;

    /** The names of the declared variables.
     */
    private VarIntro[] vars;

    /** Default constructor.
     */
    public Locals(Type type, VarIntro[] vars) {
        this.type = type;
        this.vars = vars;
    }

    /** Print an indented description of this abstract syntax node,
     *  including a name for the node itself at the specified level
     *  of indentation, plus more deeply indented descriptions of
     *  any child nodes.
     */
    public void indent(IndentOutput out, int n) {
        out.indent(n, "Locals");
        out.indent(n+1, type.toString());
        for (int i=0; i<vars.length; i++) {
           vars[i].indent(out, n+1);
        }
    }

    /** Type check this statement, using the specified context, with
     *  the given initial typing environment, and returning the typing
     *  environment for a following statement.
     */
    public TypeEnv check(Context ctxt, TypeEnv locals)
      throws Failure {
        if (VarIntro.containsRepeats(vars)) {
            ctxt.report(new Failure("LocalsUnique"));
        }
        for (int i=0; i<vars.length; i++) {
           locals = vars[i].check(ctxt, locals, type);
        }
        return locals;
    }

    /** Generate LLVM code that will execute this statement and
     *  then continue with the follow on code.
     */
    public Code compile(final llvm.Function fn, final Code andThen) {
        llvm.Type ty   = type.toLLVM();
        Code      code = andThen;
        for (int i=vars.length-1; i>=0; i--) {
           code = vars[i].compile(fn, ty, code);
        }
        return code;
    }
}
