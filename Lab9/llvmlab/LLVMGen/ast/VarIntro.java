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

/** Abstract syntax for a variable introduction (i.e., the part of
 *  a variable declaration that introduces the name and an optional
 *  initialization expression for a new variable.
 */
public class VarIntro {

    /** The name of the variable that is being introduced.
     */
    protected String name;

    /** Default constructor.
     */
    public VarIntro(String name) {
        this.name = name;
    }

    /** Print an indented description of this abstract syntax node,
     *  including a name for the node itself at the specified level
     *  of indentation, plus more deeply indented descriptions of
     *  any child nodes.
     */
    public void indent(IndentOutput out, int n) {
        out.indent(n, "VarIntro");
        out.indent(n+1, "\"" + name + "\", loc="+loc);
    }

    /** Records the LLVM location for the variable
     *  introduced here.
     */
    protected llvm.Location loc;

    /** Extend the global environment in the given context with an entry
     *  for the variable that is introduced here, using the given type.
     */
    void extendGlobalEnv(Context ctxt, Type type)
      throws Failure {
        ctxt.report(new Failure("GlobalsInitialized"));
    }

    /** Check to see if this array of variable introductions includes
     *  two introductions for the same variable name.
     */
    public static boolean containsRepeats(VarIntro[] varIntros) {
        for (int i=0; i<varIntros.length; i++) {
            for (int j=i+1; j<varIntros.length; j++) {
                 if (varIntros[i].name.equals(varIntros[j].name)) {
                     return true;
                 }
            }
        }
        return false;
    }

    /** Check this variable environment, returning an extended
     *  environment that includes an entry for the newly introduced
     *  variable.
     */
    TypeEnv check(Context ctxt, TypeEnv locals, Type type)
      throws Failure {
        loc = ctxt.current.addLocal(name, type);
        return new TypeEnv(name, type, loc, locals);
    }

    /** Generate code on the specified output channel to provide
     *  definitions for global variables introduced here.
     */
    void declareGlobals(PrintWriter out) {
        llvm.Type ty = loc.getType().ptsTo();
        // The following line should be moved out of this file to
        // eliminate a dependency on the concrete syntax of LLVM.
        out.println(loc.getName() + " = global " + ty
                                  + " " + ty.defaultValue()
                                  + ", align " + ty.getAlign());
    }

    /** Generate code that will initialize any global variables
     *  introduced here and then continue with andThen.
     */
    Code initGlobals(llvm.Function fn, final Code andThen) {
        /* Variable introductions without initializer expressions
         * should be prevented by static analysis, so no action
         * is required here.
         */
        return andThen;
    }

    public Code compile(final llvm.Function fn, final llvm.Type ty, final Code andThen) {
        // Do nothing if there is no initializer
        return andThen;
    }
}
