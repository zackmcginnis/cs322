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

/** Abstract syntax for a variable introduction that
 *  specifies an initial value (via an expression)
 *  for a variable that is brought in to scope via a
 *  VarDecl.
 */
public class InitVarIntro extends VarIntro {

    /** An expression whose that will be evaluated
     *  to provide the initial value for this variable.
     */
    private Expr exp;

    /** Default constructor.
     */
    public InitVarIntro(String name, Expr exp) {
        super(name);
        this.exp = exp;
    }

    /** Print an indented description of this abstract syntax node,
     *  including a name for the node itself at the specified level
     *  of indentation, plus more deeply indented descriptions of
     *  any child nodes.
     */
    public void indent(IndentOutput out, int n) {
        out.indent(n, "InitVarIntro");
        out.indent(n+1, "\"" + name + "\", loc="+loc);
        exp.indent(out, n+1);
    }

    /** Extend the global environment in the given context with an entry
     *  for the variable that is introduced here, using the given type.
     */
    void extendGlobalEnv(Context ctxt, Type type)
      throws Failure {
        if (TypeEnv.find(name, ctxt.globals)!=null) {
            ctxt.report(new Failure("GlobalsUnique"));
        }
        exp = exp.matchType(ctxt, null, type, "InitVarEntryType");
        loc = ctxt.addGlobal(type, name);
    }

    /** Check this variable environment, returning an extended
     *  environment that includes an entry for the newly introduced
     *  variable.
     */
    TypeEnv check(Context ctxt, TypeEnv locals, Type type)
      throws Failure {
        exp  = exp.matchType(ctxt, locals, type, "InitVarEntryType");
        return super.check(ctxt, locals, type);
    }

    /** Generate code that will initialize any global variables
     *  introduced here and then continue with andThen.
     */
    Code initGlobals(llvm.Function fn, final Code andThen) {
        return exp.compile(fn, new ValCont() {
            Code with(final Value v) {
                return new llvm.Store(v, loc, andThen);
            }
        });
    }

    public Code compile(final llvm.Function fn, final llvm.Type ty, final Code andThen) {
        return exp.compile(fn, new ValCont() {
            Code with(final Value v) {
                return new llvm.Store(v, loc, andThen);
            }
        });
    }
}
