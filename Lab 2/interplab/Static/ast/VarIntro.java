package ast;
import compiler.Failure;

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
        out.indent(n+1, "\"" + name + "\"");
    }

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
        return new TypeEnv(name, type, locals);
    }
}
