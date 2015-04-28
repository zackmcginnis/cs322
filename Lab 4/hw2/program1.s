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
         movl    $0, %ecx        # initialize length count in ecx
         jmp     test1
loop1:   incl    %eax            # increment count
         addq    $4, %rdi        # and move to next array element

test1:   movl    1(%rdi), %eax    # load array element
         cmpl    $0, %eax        # test for end of array
         jne     loop1            # repeat if we're not done ...

loop2:   incl    %ecx            # increment count
         addq    $4, %rsi        # and move to next array element

test2:   movl    1(%rsi), %ecx    # load array element
         cmpl    $0, %ecx        # test for end of array
         jne     loop2            # repeat if we're not done ...

         addl    %eax, %ecx      # add both array counts, store in eax

### This is where your code ends ...

	ret





