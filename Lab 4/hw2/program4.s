	.file	"program4.s"
        .text
	.globl	f
f:
#Program 4: If the two input arrays have the same length, then the
#	   contents of the two arrays should be swapped and the return
#	   result should be 0.  If the two arrays have different
#	   lengths, then the return result should be the absolute value
#	   of the difference between their lengths.
### This is where your code begins ...

    	movl     (%rdi), %ecx
  		movl     (%rsi), %edx
   		cmpl     %ecx, %edx
   		je       loop1a
   		jmp      loop2

loop1a: movl   $0, %eax
        jmp     loop1

loop1:  cmpl    $0, %edx      # compare pointers at two ends of array
        je      loop1b
        addq    $4, %rdi        # adjust pointers at each end of array
        addq    $4, %rsi
        movl    (%rdi), %ecx    # read values from each end of the array
        movl    (%rsi), %eax
        movl    %eax, (%rdi)    # write them back in reverse order
        movl    %ecx, (%rsi)
        decl    %edx
        jmp     loop1           # and repeat ...

loop1b: movl  $0, %eax
        jmp   done

loop2:  subl  %ecx, %edx
        movl  %edx, %eax
        movl  $0, %edx
        cltd
        xorl  %eax, %edx
        subl  %edx, %eax
        jmp  done



done:
### This is where your code ends ...

	ret





