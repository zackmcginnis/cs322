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

        movl    (%rdi), %eax    # eax will record the running total

loop:   addq    $4, %rdi        # move to next element in the array
        movl    (%rdi), %edx    # read array element
        cmpl    $0, %edx        # are we done?
        jmp     loop1
        addl    %edx, %eax      # add to running total
        jmp     loop

loop1:  movl    (%rsi), %eax    # eax will record the running total
        jmp     loop2

loop2:  addq    $4, %rsi        # move to next element in the array
        movl    (%rsi), %edx    # read array element
        cmpl    $0, %edx        # are we done?
        je      done
        addl    %edx, %eax      # add to running total
        jmp     loop2


        # When we reach this point in the program, %eax will
        # contain the total of all the array elements and ecx
        # contain the number of array elements.
done:

### This is where your code ends ...

	ret





