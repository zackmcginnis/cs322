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
import compiler.Handler;
import java.util.HashMap;

/** Provides a context for use in type checking that captures the
 *  global variable environment, function environment, and return
 *  type of the function whose body is being checked (if any), as
 *  well as an appropriate handler object for reporting errors.
 */
public class Context {

    /** The handler that we will use to report errors.
     */
    private Handler handler;

    /** Default constructor.
     */
    public Context(Handler handler) {
        this.handler = handler;
    }

    /** Use this context to report an error to the specified handler.
     */
    public void report(Failure f) {
        handler.report(f);
    }

    /** Stores an environment for the global variables in the
     *  current program.
     */
    TypeEnv globals = null;

    /** Add a global variable with the specified name and type.
     */
    Global addGlobal(Type type, String name) {
        Global loc = new Global(type.toLLVM().ptr(), name);
        globals    = new TypeEnv(name, type, loc, globals);
        return loc;
    }

    /** Stores an environment for the functions in the current
     *  program.
     */
    FunctionEnv functions = null;

    /** Holds a pointer to the function whose body we are currently
     *  checking, or null if we are checking global declarations.
     */
    Function current = null;

    /** Return the return type of the current function.
     */
    public Type getRetType() {
        return current.getRetType();
    }

    /** Run the type checker in this context on a given program.
     */
    public void check(Defn[] defns)
      throws Failure {
        // Reset the environments for this program:
        globals   = null;
        functions = null;
        current   = null;

        // Build global variable and function environments for this program:
        for (int i=0; i<defns.length; i++) {
            defns[i].extendGlobalEnv(this);
        }

        // Type check each function definition in this program:
        FunctionEnv.check(this, functions);

        // Check for main function:
        FunctionEnv main = FunctionEnv.find("main", functions);
        if (main==null) {
            report(new Failure("MainDefined"));
        } else {
            main.getFunction().checkMain(this);
        }
    }

    /** Look for the type of this variable, starting in the given
     *  type environment for local variables, but then falling back
     *  to the environment for global variables in the context.
     */
    TypeEnv findVar(String name, TypeEnv locals)
      throws Failure {
        // Look for a definition of this variable in the local environment:
        TypeEnv te = TypeEnv.find(name, locals);
  
        // If there is no local definition, try the global environment:
        if (te==null) {
            te = TypeEnv.find(name, globals);
        }
  
        // If we still have not found a definition, then there is an error:
        if (te==null) {
            throw new Failure("VarDefined");
        }
        return te;
    }
}
