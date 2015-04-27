	.file	"example1.s"
        .text
	.globl	f
f:

### This is where your code begins ...
### Example 1: return the length of the input array in %eax

        movl    $0, %eax        # initialize length count in eax
        jmp     test
loop:   incl    %eax            # increment count
        addq    $4, %rdi        # and move to next array element

test:   movl    (%rdi), %ecx    # load array element
        cmpl    $0, %ecx        # test for end of array
        jne     loop            # repeat if we're not done ...

### This is where your code ends ...

	ret





