	.file	"program3.s"
        .text
	.globl	f
f:

#Program 3: The result should be 1 and the contents of the first array
#	   should be reversed.  The second array should not be changed.
### This is where your code begins ...

        movl    (%rdi), %edx      # initialize %rdx at start of array
        #movq    $0, %rcx
        #movl    $1, %eax
loop1:  cmpl    $0, %edx
        je      loop2
        decl    %edx
        addq    $4, %rdi        # move to next array element
        movq    (%rdi), %rcx    # read in array element
        jmp     loop1

### Our next step is to swap pairs of elements, exchanging the value
### in memory at %rdi with the value in memory at %rdx.  After each
### exchange, we add $4 to %rdi and subtract $4 from %rdx.  This
### process stops when %rdi >= %rdx, at which point we can be sure
### that the array has been reversed:

loop2:  cmpq    %rdi, %rdx      # compare pointers at two ends of array
        jnl     done
        movl    (%rdi), %ecx    # read values from each end of the array
        movl    (%rcx), %eax
        movl    %eax, (%rdi)    # write them back in reverse order
        movl    %ecx, (%rcx)
        addq    $4, %rdi        # adjust pointers at each end of array
        subq    $4, %rcx
        jmp     loop2           # and repeat ...

done:   # the problem description doesn't specify what value should be
        # returned in %eax so we won't worry about it here ...


### This is where your code ends ...

	ret





