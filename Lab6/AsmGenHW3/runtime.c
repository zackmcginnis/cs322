#include <stdio.h>

//Uncomment the two lines in this file that refer
//to XinitGlobals() when you have implemented support for
//initializing global variables.

extern void XinitGlobals();
extern void Xmain();

int main(int argc, char** argv) {
    XinitGlobals();
    Xmain();
    return 0;
}

void Xprint(int val) {
    printf("output: %d\n", val);
}

//void initGlobals(){

//}

/*
void initGlobals() {
                      

        for (int i=0; i<defns.length; i++) {
            defns[i].compileFunction(a, globals);
        }

            // Initialize global variables:
        for (int i=0; i<vars.length; i++) {
           env = vars[i].declareGlobals(a, env);
        }
        return env;
  }

      void compileFunction(Assembly a, LocEnv globals) {
        a.emit(".globl", a.name(name));
        a.emitLabel(a.name(name));
        a.emitPrologue();
        Frame f = new FunctionFrame(formals, globals);
        //f.dump(a);
        if (body.compile(a, f)) {
            a.emitEpilogue();
        }
        a.emit();
    }
*/
