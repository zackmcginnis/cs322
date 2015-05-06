	.file	"program3.s"
        .text
	.globl	f
f:

#Program 3: The result should be 1 and the contents of the first array
#	   should be reversed.  The second array should not be changed.
### This is where your code begins ...



loop1:  movl    (%rdi), %edx           # initialize %edx at start of array
        movq    (,%edx,4), %rdx
        addq    $4, %rdi  
        jmp     loop2


### Our next step is to swap pairs of elements, exchanging the value
### in memory at %rdi with the value in memory at %rdx.  After each
### exchange, we add $4 to %rdi and subtract $4 from %rdx.  This
### process stops when %rdi >= %rdx, at which point we can be sure
### that the array has been reversed:

loop2:  cmpq    %rdi, %rdx      # compare pointers at two ends of array
        jnl     done
        movl    (%rdi), %ecx    # read values from each end of the array
        movl    (%rdx), %eax
        movl    %eax, (%rdi)    # write them back in reverse order
        movl    %ecx, (%rdx)
        addq    $4, %rdi        # adjust pointers at each end of array
        subq    $4, %rdx
        jmp     loop2           # and repeat ...

done:   movl    $1,  %eax
        # the problem description doesn't specify what value should be
        # returned in %eax so we won't worry about it here ...


### This is where your code ends ...

	ret





