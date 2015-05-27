#include <stdio.h>

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
