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

loop1:  cmpl    $0, %edx
        je      loop2a
        decl    %edx            #decrement the value of the length of the array (to know how many values to count)
        addq    $4, %rdi        # move to next element in the array
        movl    (%rdi), %ecx    # read array element
        cmpl    $0, %ecx        # are we done?
        je      incloop1
        jmp     loop1

incloop1: incl  %eax
          jmp   loop1

loop2a: movl    (%rsi), %edx
        jmp     loop2


loop2:  cmpl    $0, %edx
        je      done
        decl    %edx           #decrement the value of the length of the array (to know how many values to count)
        addq    $4, %rsi        # move to next element in the array
        movl    (%rsi), %ecx    # read array element
        cmpl    $0, %ecx        # are we done?
        je      loop2
        jmp     incloop2

incloop2: incl  %eax
          jmp   loop2

done:

### This is where your code ends ...

	ret
