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
        out.indent(n+1, "\"" + lhs + "\", loc="+loc);
        rhs.indent(out, n+1);
    }

    /** Records the LLVM location for the variable
     *  referenced here.
     */
    private llvm.Location loc;

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
        loc  = te.getLoc();
        rhs  = rhs.matchType(ctxt, locals, type, "AssignTypes");
        return type;
    }

    /** Generate LLVM code that will evaluate this expression,
     *  and then pass the resulting value on to the following
     *  code, represented by the continuation argument.
     */
    public Code compile(final llvm.Function fn, final ValCont k) {
        return rhs.compile(fn, new ValCont() {
            Code with(final Value v) {
                return new llvm.Store(v, loc, k.with(v));
            }
        });
    }
}
