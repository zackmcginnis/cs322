package ast;
import compiler.Failure;

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

    /** Run initialization code on this definition.
     */
    abstract void initialize(int[] globals);
}
