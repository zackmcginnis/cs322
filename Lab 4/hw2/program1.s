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

        movl    $0, %eax        # initialize length count in eax
        jmp     test
loop:   incl    %eax            # increment count
        addq    $4, %rdi        # and move to next array element

test:   movl    (%rdi), %ecx    # load array element
        cmpl    $0, %ecx        # test for end of array
        jne     loop            # repeat if we're not done ...

### This is where your code ends ...

	ret





