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

/** Abstract syntax for global variable definitions.
 */
public class Globals extends Defn {

    /** The type of the variable(s) being defined.
     */
    private Type type;

    /** The names and initial values of the variables.
     */
    private VarIntro[] vars;

    /** Default constructor.
     */
    public Globals(Type type, VarIntro[] vars) {
        this.type = type;
        this.vars = vars;
    }

    /** Print an indented description of this definition.
     */
    public void indent(IndentOutput out, int n) {
        out.indent(n, "Globals");
        out.indent(n+1, type.toString());
        for (int i=0; i<vars.length; i++) {
           vars[i].indent(out, n+1);
        }
    }

    /** Extend the environments in the given context with entries from
     *  this definition.
     */
    void extendGlobalEnv(Context ctxt)
      throws Failure {
        for (int i=0; i<vars.length; i++) {
           vars[i].extendGlobalEnv(ctxt, type);
        }
    }

    /** Generate code on the specified output channel to provide
     *  definitions for global variables introduced here.
     */
    void declareGlobals(PrintWriter out) {
        for (int i=0; i<vars.length; i++) {
            vars[i].declareGlobals(out);
        }
    }

    /** Generate code that will initialize any global variables
     *  introduced here and then continue with andThen.
     */
    Code initGlobals(llvm.Function fn, final Code andThen) {
        Code code = andThen;
        for (int i=vars.length-1; i>=0; i--) {
            code = vars[i].initGlobals(fn, code);
        }
        return code;
    }
}
