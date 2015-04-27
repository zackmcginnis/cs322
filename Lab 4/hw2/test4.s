	.file	"test4.s"
        .text
	.globl	f
f:

### This is where your code begins ...

### Example 4: return the average value of the numbers in the array.
### We're going to divide by the length of the array so it is nice to
### have the assumption stated that the array is not empty....

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
done:   cltd			# sign extend %eax into %edx
	idivl   %ecx            # quotient goes in %eax, as required ...

### This is where your code ends ...

	ret





