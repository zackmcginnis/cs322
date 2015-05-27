import ast.*;
import compiler.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/** A top-level program for compiling a source program in to LLVM.
 */
public class LLVMGen {

    public static void main(String[] args) {
        Handler  handler    = new Handler();
        Defn[]   program    = null;

        if (args.length!=1) {
            System.out.println("Please specify a single file name on the command line");
            System.exit(1);
        }

        try {
            // Initialize the parser:
            new Parser(new BufferedReader(new FileReader(args[0])));

            // Parse an input program:
            program = Parser.Top();

            // Run static analysis on the program:
            new Context(handler).check(program);

            // Generate corresponding LLVM code:
            String      name = args[0] + ".ll";
            PrintWriter out  = new PrintWriter(new FileWriter(name));
            Defn.compile(out, program);
            out.close();

        } catch (ParseException e) {
            handler.report(new Failure("Syntax Error"));
        } catch (IOException e) {
            handler.report(new Failure("IO Exception: " + e));
        } catch (Failure f) {
            handler.report(f);
        }

        if (handler.hasErrors()) {
            handler.dump();
        }
    }
}
