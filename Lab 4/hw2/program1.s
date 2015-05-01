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

         movl    $0, %eax        # initialize total in eax
         movl    $0, %ecx        # initialize total in ecx
         jmp     test1
loop1:   movl    (%rdi), %eax    # eax will record the running total
         addq    $4, %rdi        # and move to next array element

test1:   movl    (%rdi), %eax    # load array element
         cmpl    $0, %eax        # test for end of array
         jne     loop1
         jmp     test2            # repeat if we're not done ...

loop2:   movl    (%rsi), %ecx    # ecx will record the running total
         addq    $4, %rsi        # and move to next array element

test2:   movl    (%rsi), %ecx    # load array element
         cmpl    $0, %ecx        # test for end of array
         jne     loop2            # repeat if we're not done ...

         addl    %ecx, %eax      # add both array counts, store in eax






        movl    $1, %ecx        # ecx will count the number of elements
        movl    (%rdi), %eax    # eax will record the running total

loop:   addq    $4, %rdi        # move to next element in the array
        movl    (%rdi), %edx    # read array element
        cmpl    $0, %edx        # are we done?
        je      done
        addl    %edx, %eax      # add to running total
        incl    %ecx            # increment count
        jmp     loop

        # When we reach this point in the program, %eax will
        # contain the total of all the array elements and ecx
        # contain the number of array elements.











### This is where your code ends ...

	ret





