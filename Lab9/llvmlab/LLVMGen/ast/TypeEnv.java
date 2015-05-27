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

/** Represents a typing environment, mapping identifier
 *  names to corresponding types.
 */
public class TypeEnv {

    private String name;

    private Type type;

    private Location loc;

    private TypeEnv next;

    public TypeEnv(String name, Type type, Location loc, TypeEnv next) {
        this.name  = name;
        this.type  = type;
        this.loc   = loc;
        this.next  = next;
    }

    /** Represents the empty environment that does not bind any
     *  variables.
     */
    public static final TypeEnv empty = null;

    /** Search an environment for a specified variable name,
     *  returning null if no such entry is found, or else
     *  returning a pointer to the first matching TypeEnv
     *  object in the list.
     */
    public static TypeEnv find(String name, TypeEnv env) {
        while (env!=null && !env.name.equals(name)) {
            env = env.next;
        }
        return env;
    }

    /** Return the value associated with this entry.
     */
    public Type getType() {
        return type;
    }

    /** Return the location associated with this entry.
     */
    public Location getLoc() {
        return loc;
    }
}
