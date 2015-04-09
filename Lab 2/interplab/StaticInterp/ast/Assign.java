package ast;
import compiler.Failure;

public class Assign extends StmtExpr {

    /** The variable that we are assigning to.
     */
    private String lhs;

    /** The expression whose value will be saved.
     */
    private Expr rhs;

    /** Default constructor.
     */
    public Assign(String lhs, Expr rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    /** This attribute should be filled in during static analysis to
     *  record the type of the right hand side of this assignment.
     *  This information will be useful for the purposes of code
     *  generation.  The type attribute is set to null when an Assign
     *  node is first created to indicate that the type has yet to be
     *  determined.
     */
    protected Type type = null;

    /** Print an indented description of this abstract syntax node,
     *  including a name for the node itself at the specified level
     *  of indentation, plus more deeply indented descriptions of
     *  any child nodes.
     */
    public void indent(IndentOutput out, int n) {
        out.indent(n, (type==null) ? "Assign" : ("Assign " + type));
        out.indent(n+1, "\"" + lhs + "\", slot="+slot);
        rhs.indent(out, n+1);
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
        return check(ctxt, locals);
    }

    /** Type check this statement expression, using the specified
     *  context and the given typing environment.
     */
    public Type check(Context ctxt, TypeEnv locals)
      throws Failure {
        TypeEnv te = ctxt.findVar(lhs, locals);
        type = te.getType();
        slot = te.getSlot();
        rhs  = rhs.matchType(ctxt, locals, type, "AssignTypes");
        return type;
    }
}
