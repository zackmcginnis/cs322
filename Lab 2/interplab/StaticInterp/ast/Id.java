package ast;
import compiler.Failure;

/** Abstract syntax for variable references.
 */
public class Id extends Expr {

    /** The name of this identifier/variable reference.
     */
    private String name;

    /** Default constructor.
     */
    public Id(String name) {
        this.name = name;
    }

    /** Print an indented description of this abstract syntax node,
     *  including a name for the node itself at the specified level
     *  of indentation, plus more deeply indented descriptions of
     *  any child nodes.
     */
    public void indent(IndentOutput out, int n) {
        out.indent(n, "Id(\"" + name + "\", slot=" + slot + ")");
    }

    /** Records the slot number for the variable that is
     *  associated with the variable used here.  A negative
     *  slot number references a global, while a non-negative
     *  references a local.
     */
    private int slot = 0;

    /** Calculate the type of this expression, using the given context
     *  and type environment.
     */
    public Type typeOf(Context ctxt, TypeEnv locals)
      throws Failure {
        TypeEnv te = ctxt.findVar(name, locals);
        slot       = te.getSlot();
        return te.getType();
    }
}
