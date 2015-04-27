	.file	"example3.s"
        .text
	.globl	f
f:

### This is where your code begins ...

### Example 3: return the position of the largest number from the array
### in %eax.  Similar to the code for Example 2, except that we use
### %edx to hold the biggest number found so far, which frees up the %eax
### register to hold the position of that biggest number in the array.
### We also need a register (we'll use %esi) to count the number of the
### current element.

        movl    $0, %esi        # first element is index 0
        movl    %esi, %eax      # position of first element in the array
        movl    (%rdi), %edx    # value of first element in the array

loop:   addq    $4, %rdi        # move to next array element
        incl    %esi            # update count
        movl    (%rdi), %ecx    # and load in value

        cmpl    $0, %ecx        # are we done?
        je      done

        cmpl    %ecx, %edx      # compare this element with current max
        jnl     loop            # continue if current max still largest

        movl    %esi, %eax      # save index of new largest value
        movl    %ecx, %edx      # save new largest value
        jmp     loop
done:

### This is where your code ends ...

	ret





