	.file	"test5.s"
        .text
	.globl	f
f:

### This is where your code begins ...

### Example 5: Reverse the order of the elements in the array,
### without using any additional storage.  This requires a
### little more algorithmic sophistication ...

### First we'll search to the end of the array to find the address
### of the last array element in %rdx:

        movq    %rdi, %rdx      # initialize %rdx at start of array
loop1:  addq    $4, %rdx        # move to next array element
        movl    (%rdx), %eax    # read in array element
        cmpl    $0, %eax        # are we done?
        jne     loop1
        # At this point, %rdx holds the address of the zero element
        subq    $4, %rdx        # adjust %rdx to point to the last element

### Our next step is to swap pairs of elements, exchanging the value
### in memory at %rdi with the value in memory at %rdx.  After each
### exchange, we add $4 to %rdi and subtract $4 from %rdx.  This
### process stops when %rdi >= %rdx, at which point we can be sure
### that the array has been reversed:

loop2:  cmpq    %rdx, %rdi      # compare pointers at two ends of array
        jnl     done
        movl    (%rdi), %ecx    # read values from each end of the array
        movl    (%rdx), %eax
        movl    %eax, (%rdi)    # write them back in reverse order
        movl    %ecx, (%rdx)
        addq    $4, %rdi        # adjust pointers at each end of array
        subq    $4, %rdx
        jmp     loop2           # and repeat ...

done:   # the problem description doesn't specify what value should be
        # returned in %eax so we won't worry about it here ...


### This is where your code ends ...

	ret






