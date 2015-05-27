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

/** Abstract syntax representation for a formal parameter.
 */
public class Formal {

    /** The type of the parameter.
     */
    private Type type;

    /** The name of the parameter.
     */
    private String name;

    /** Default constructor.
     */
    public Formal(Type type, String name) {
        this.type = type;
        this.name = name;
    }

    /** Print an indented description of this formal parameter.
     */
    public void indent(IndentOutput out, int n) {
        out.indent(n, "Formal");
        out.indent(n+1, type.toString());
        out.indent(n+1, "\"" + name + "\", loc="+loc);
    }

    /** Records the LLVM location for the variable
     *  referenced here.
     */
    private llvm.Location loc;

    /** Extend the given environment with an entry for this
     *  formal parameter.
     */
    public TypeEnv extend(Context ctxt, TypeEnv locals) {
        loc = ctxt.current.addLocal(name, type);
        return new TypeEnv(name, type, loc, locals);
    }

    /** Return the type associated with this formal parameter.
     */
    public Type getType() {
        return type;
    }

    /** Check to see if this array of formal parameter includes
     *  two definitions for the same variable name.
     */
    public static boolean containsRepeats(Formal[] formals) {
        for (int i=0; i<formals.length; i++) {
            for (int j=i+1; j<formals.length; j++) {
                 if (formals[i].name.equals(formals[j].name)) {
                     return true;
                 }
            }
        }
        return false;
    }

    /** Generate an LLVM formal parameter reference corresponding to this
     *  source language formal parameter.
     */
    llvm.Formal toLLVM() {
        return new llvm.Formal(type.toLLVM().ptr(), name);
    }
}
