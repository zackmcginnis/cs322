	.file	"program2.s"
        .text
	.globl	f
f:

#Program 2: The result should be the total number of zeros in the first
#	   array plus the total number of nonzeros in the second array.

### This is where your code begins ...

        movl    $0, %eax        # eax will count the number of elements
        movq    $0, %rdi
        movq    $0, %rsi
        movl    (%rdi), %edx    # read array element of first byte which signifies length
        movl    (%rsi), %e8

loop1:  cmpl    $0, %edx
        je      loop2
        decl    %edx            #decrement the value of the length of the array (to know how many values to count)
        addq    $4, %rdi        # move to next element in the array
        movl    (%rdi), %ecx    # read array element
        cmpl    $0, %ecx        # are we done?
        je      incloop1
        jmp     loop1

incloop1: incl  %eax
          jmp   loop1

loop2:  cmpl    $0, %e8
        je      done
        decl    %e8            #decrement the value of the length of the array (to know how many values to count)
        addq    $4, %rsi        # move to next element in the array
        movl    (%rsi), %e9    # read array element
        cmpl    $0, %e9        # are we done?
        je      loop2
        jmp     incloop2

incloop2: incl  %eax
          jmp   loop2

done:

### This is where your code ends ...

	ret
