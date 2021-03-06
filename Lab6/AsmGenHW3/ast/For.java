package ast;
import compiler.Failure;

/** Abstract syntax for for loops.
 */
public class For extends Stmt {

    /** The initialization expression.
     */
    private StmtExpr init;

    /** The test portion of this loop.
     */
    private Expr test;

    /** The step portion of this for loop.
     */
    private StmtExpr step;

    /** The body of this loop.
     */
    private Stmt body;

    /** Default constructor.
     */
    public For(StmtExpr init, Expr test, StmtExpr step, Stmt body) {
        this.init = init;
        this.test = test;
        this.step = step;
        this.body = body;
    }

    /** Print an indented description of this abstract syntax node,
     *  including a name for the node itself at the specified level
     *  of indentation, plus more deeply indented descriptions of
     *  any child nodes.
     */
    public void indent(IndentOutput out, int n) {
        out.indent(n, "For");
  
        if (init!=null) {
           init.indent(out, n+1);
        } else {
            out.indent(n+1, "No init");
        }
  
        if (test!=null) {
           test.indent(out, n+1);
        } else {
            out.indent(n+1, "No test");
        }
  
        if (step!=null) {
           step.indent(out, n+1);
        } else {
            out.indent(n+1, "No step");
        }
  
        body.indent(out, n+1);
    }

    /** Type check this statement, using the specified context, with
     *  the given initial typing environment, and returning the typing
     *  environment for a following statement.
     */
    public TypeEnv check(Context ctxt, TypeEnv locals)
      throws Failure {
        if (init!=null) {
            init.check(ctxt, locals);
        }
        if (test!=null) {
            try {
                if (!test.typeOf(ctxt, locals).equals(Type.BOOLEAN)) {
                    ctxt.report(new Failure("WhileBoolean"));
                }
            } catch (Failure f) {
                ctxt.report(f);
            }
        }
        if (step!=null) {
            step.check(ctxt, locals);
        }
        body.check(ctxt, locals); // discard final environment
        return locals;
    }

    /** Generate code for executing this statement.
     *  Returns true if there is a chance that execution may
     *  continue with the next statement.
     */
    public boolean compile(Assembly a, Frame f) {
        // Remember to allow for the possibility that
        // init, test, and step could each be null ...
        //init and step = statement
        //test = boolean
        //body = statement

        //create new labels lab1 and lab2
        //for body and test, respectively
        String lab1 = a.newLabel();
        String lab2 = a.newLabel();

        //see if init is included
        //and compile if true
        if (init != null)
          init.compileExpr(a, f);

        //jump to test
        a.emit("jmp", lab2);

        //lab1 contains the body of for loop
        a.emitLabel(lab1);
        body.compile(a, f);

        //see if step is included
        //and compile if true.
        //will compile after body in 
        //each loop iteration
        if (step != null)
          step.compileExpr(a, f);

        //contains the test portion of the for loop
        //if null, will compile body in an infinite loop
        //if not null, will compile body if true, and
        //repeat until test = false

        a.emitLabel(lab2);
        if (test != null)
           test.branchTrue(a, f, lab1);
        else
        a.emit("jmp", lab1);
        return true; 
    }
}
