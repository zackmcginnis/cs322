	.file	"program5.s"
        .text
	.globl	f
f:

#Program 5: If the two input arrays have the same length, then the
#	   contents of the two arrays should be compared in pairwise
#	   fashion, and the elements of the arrays should be swapped so
#	   that the first array contains the minimum value of each pair
#	   and the second array contains the maximum value.  For
#	   example, if the two input arrays are:
#
 ##              { 5, 0, 1, 2, 3, 4}
   #            { 5, 4, 3, 2, 1, 0}
#
 #          then the output arrays will be:
#
 #              { 5, 0, 1, 2, 1, 0}
  #             { 5, 4, 3, 2, 3, 4}
#
#	   If the two arrays are of equal length, then the return result
#	   should be the dot (or inner) product of the numbers in the
#	   two arrays.  (i.e., the sum of the products of each
#	   corresponding pair of numbers in the array.)  For the example
#	   above, the return value would be 10 (i.e., 0*4 + 1*3 + 2*2 +
#	   3*1 + 4*0).
#
#	   If the two arrays have different lengths, then the return
#	   result should be the sum of the lengths of the two arrays.
#
### This is where your code begins ...

        movl     (%rdi), %ecx
  		movl     (%rsi), %edx
   		cmpl     %ecx, %edx
   		je       loop1a
   		jmp      loop2

loop1a: movl   %ecx, %eax
        cmpl   $0, %ecx
        jz     done
        jmp     loop1

loop1:  cmpl    $0, %eax
        je      dotloop
        addq    $4, %rdi         
        addq    $4, %rsi
 		movl    (%rdi), %ecx
  		movl    (%rsi), %edx

  		cmpl    %edx, %ecx
  		decl    %eax
  		jnl     swaploop
  		jmp     loop1


swaploop: 

         jmp   loop1

dotloop:          
         #eax contains dot product (i.e., 0*4 + 1*3 + 2*2 +
        
 
loop2:  addl   %ecx, %edx
        movl   %edx, %eax
        jmp    done
     #eax contains sum of lengths of the two 


done:
### This is where your code ends ...

	ret






