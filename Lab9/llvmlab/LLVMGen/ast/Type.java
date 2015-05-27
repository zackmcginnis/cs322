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

/** Abstract syntax for  basic types.
 */
public class Type {

    /** The name of this type in the source language.
     */
    String typename;

    /** Default constructor.
     */
    private Type(String typename) {
        this.typename = typename;
    }

    /** Represents the type of integers.
     */
    public static final Type INT = new Type("int");

    /** Represents the type of double precision floating point numbers.
     */
    public static final Type DOUBLE = new Type("double");

    /** Represents the type of booleans.
     */
    public static final Type BOOLEAN = new Type("boolean");

    /** Generate a printable name for this type.
     */
    public String toString() {
        return typename;
    }

    /** Test for a numeric type.
     */
    public boolean isNumeric() {
        return equals(INT) || equals(DOUBLE);
    }

    /** Determine the LLVM type corresponding to this source language type.
     */
    public llvm.Type toLLVM() {
        return (this==INT)     ? llvm.Type.INT
             : (this==DOUBLE)  ? llvm.Type.DOUBLE
             : (this==BOOLEAN) ? llvm.Type.BOOLEAN
             : null; // TODO: fatal error here
    }
}
