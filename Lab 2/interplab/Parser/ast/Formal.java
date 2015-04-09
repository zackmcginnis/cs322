package ast;

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
        out.indent(n+1, "\"" + name + "\"");
    }
}
