package ast;

/** Abstract syntax for function definitions.
 */
public class Function extends Defn {

    /** The return type of this function (or null for
     *  a procedure/void function).
     */
    private Type retType;

    /** The name of this function.
     */
    private String name;

    /** The formal parameters for this function.
     */
    private Formal[] formals;

    /** The body of this function.
     */
    private Stmt body;

    /** Default constructor.
     */
    public Function(Type retType, String name, Formal[] formals, Stmt body) {
        this.retType = retType;
        this.name = name;
        this.formals = formals;
        this.body = body;
    }

    /** Print an indented description of this definition.
     */
    public void indent(IndentOutput out, int n) {
        out.indent(n, "Function");
        out.indent(n+1, (retType==null) ? "void" : retType.toString());
        out.indent(n+1, "\"" + name + "\"");
        for (int i=0; i<formals.length; i++) {
            formals[i].indent(out, n+1);
        }
        body.indent(out, n+1);
    }
}
