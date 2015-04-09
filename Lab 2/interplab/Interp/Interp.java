import ast.*;
import compiler.*;

public class Interp {

    public static void main(String[] args) {
        Handler  handler    = new Handler();
        Defn[]   program    = null;
        Function main       = null;
        int      numGlobals = 0;
        new Parser(System.in);
        try {
            // Parse an input program:
            program = Parser.Top();

            // Run static analysis on the program:
            Context ctxt = new Context(handler);
            main         = ctxt.check(program);
            numGlobals   = ctxt.numGlobals;

        } catch (ParseException e) {
            handler.report(new Failure("Syntax Error"));
        } catch (Failure f) {
            handler.report(f);
        }

        // Output the annotated abstract syntax tree:
        if (handler.hasErrors()) {
            handler.dump();
        } else {
            main.run(program, numGlobals);
            //new IndentOutput(System.out).indent(program);
        }
    }
}
