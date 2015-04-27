	.file	"example2.s"
        .text
	.globl	f
f:

### This is where your code begins ...

### Example 2: return the largest number from the array in %eax
### We've been told that it's ok to assume that the array is nonempty ...

        movl    (%rdi), %eax    # get the first element in the array
loop:   addq    $4, %rdi        # move to the next element
        movl    (%rdi), %ecx    # load next array element
        cmpl    $0, %ecx        # end of the array?
        je      done
        cmpl    %ecx, %eax      # compare ecx with largest so far (%eax)
        jnl     loop            # continue if %eax still largest
        movl    %ecx, %eax      # we've found a new largest value
        jmp     loop
done:

### This is where your code ends ...

	ret





