	.file	"program1.s"
        .text
	.globl	f
f:


###    For each of the following tasks, you are asked to write a fragment of
###    x86-64 code that takes the starting addresses of two arrays, represented
###    as above, in the rdi and rsi registers as its input and returns a single
###    integer as its result in eax.

###    Program 1: The result should be the total value of the numbers in the
###	   two arrays, not including the length fields at the start of
###	   each array.  The two arrays themselves should not be
###	   modified.

        movl    (%rdi), %ecx    # eax will record the running total
        movl    (%rsi), %edx

loop:   cmpl    $0, %ecx
        je      loop2
        addq    $4, %rdi        # move to next element in the array
        movl    (%rdi), %ebx
        addl    %ebx, %eax    
        decl    %ecx
        jmp     loop

#loop1:  movl    (%rsi), %eax    # eax will record the running total
#        jmp     loop2

loop2:  cmpl    $0, %edx
        je      done
        addq    $4, %rsi        # move to next element in the array
        movl    (%rsi), %ecx    # read array element
        addl    %ecx, %eax  
        decl    %edx
        jmp     loop2

done:

### This is where your code ends ...

	ret





