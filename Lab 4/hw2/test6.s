	.file	"test6.s"
        .text
	.globl	f
f:

### This is where your code begins ...

### Example 6: Sort the elements in the array into increasing order.
### We'll use a form of selection sort here.

sort:   movl    (%rdi), %eax    # get value at start of array
        cmpl    $0, %eax        # are we done?
        je      done

        movq    %rdi, %rdx      # prepare to scan forward over array
scan:   addq    $4, %rdx
        movl    (%rdx), %ecx
        cmpl    $0, %ecx        # reached end of array?
        je      save
        cmpl    %eax, %ecx      # is this smaller than previous minimum?
        jnl     scan
        movl    %eax, (%rdx)    # save previous smallest back in array
        movl    %ecx, %eax      # and move new smallest into eax
        jmp     scan

save:   movl    %eax, (%rdi)    # save smallest number at front of array
        addq    $4, %rdi        # now sort the rest of the array
        jmp     sort

done:   # the problem description doesn't specify what value should be
        # returned in %eax so we won't worry about it here ...

### This is where your code ends ...

	ret





