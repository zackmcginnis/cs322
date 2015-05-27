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

    /** This attribute will be filled in during static analysis to
     *  record the type of this identifier.
     */
    private Type type = null;

    /** Print an indented description of this abstract syntax node,
     *  including a name for the node itself at the specified level
     *  of indentation, plus more deeply indented descriptions of
     *  any child nodes.
     */
    public void indent(IndentOutput out, int n) {
        out.indent(n, "Id(\"" + name + "\", loc=" + loc + ")");
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
        TypeEnv te  = ctxt.findVar(name, locals);
        loc         = te.getLoc();
        return type = te.getType();
    }

    /** Generate LLVM code that will evaluate this expression,
     *  and then pass the resulting value on to the following
     *  code, represented by the continuation argument.
     */
    public Code compile(final llvm.Function fn, final ValCont k) {
        Reg r = fn.reg(type.toLLVM());
        return new llvm.Op(r, new llvm.Load(loc), k.with(r));
    }
}
