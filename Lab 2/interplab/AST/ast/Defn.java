package ast;

/** Abstract syntax for definitions (either global variables or functions.
 */
public abstract class Defn {

    /** Print an indented description of this definition.
     */
    public abstract void indent(IndentOutput out, int n);
}
