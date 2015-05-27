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

/** Abstract syntax for definitions (either global variables or functions.
 */
public abstract class Defn {

    /** Print an indented description of this definition.
     */
    public abstract void indent(IndentOutput out, int n);

    /** Extend the environments in the given context with entries from
     *  this definition.
     */
    abstract void extendGlobalEnv(Context ctxt)
      throws Failure;

    /** Generate an LLVM version of the given program
     *  in text form using the specified output channel.
     */
    public static void compile(PrintWriter out, Defn[] program) {
        // Declare external runtime library functions:
        // (Note: This code should be moved out of this file to
        // eliminate a dependency on the concrete syntax of LLVM.)
        out.println("; declare external runtime library functions");
        out.println("declare void @Xprint(i32)");
        out.println();

        // Generate declarations for global variables:
        out.println("; declare global variables");
        for (int i=0; i<program.length; i++) {
            program[i].declareGlobals(out);
        }
        out.println();

        // Generate code for function definitions:
        out.println("; function definitions");

        // Generate code for initGlobals function:
        initGlobals(program).print(out);

        // Process regular function definitions:
        for (int i=0; i<program.length; i++) {
            program[i].generateCode(out);
        }
    }

    /** Generate code for an initGlobals function that will
     *  initialize all of the global variables in the given
     *  program.
     */
    public static llvm.Function initGlobals(Defn[] program) {
        String        name = "initGlobals";
        llvm.Formal[] fms  = new llvm.Formal[0];
        llvm.Function init = new llvm.Function(null, name, fms);
        Code          code = new llvm.RetVoid();
        for (int i=program.length-1; i>=0; i--) {
            code = program[i].initGlobals(init, code);
        }
        init.block("entry", code);
        return init;
    }

    /** Generate code on the specified output channel to provide
     *  definitions for global variables introduced here.
     */
    void declareGlobals(PrintWriter out) { /* By default, no action is required. */ }

    /** Generate code that will initialize any global variables
     *  introduced here and then continue with andThen.
     */
    Code initGlobals(llvm.Function fn, final Code andThen) {
        // No additional code is required for function definitions
        return andThen;
    }

    /** Generate code for function definitions.
     */
    public void generateCode(PrintWriter out) {
        // Nothing to do for non-function definitions!
    }
}
