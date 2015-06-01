	.file	"program6.s"
        .text
	.globl	f
f:

#Program 6: The overall effect of this program, viewing the contents
#	   of the two arrays as a single sequence of numbers, should be
#	   to "rotate" the values forward, shifting each number towards
##	   the start of the array and moving the number that was
#	   originally at the front to the back.  Note that the lengths
#	   of the two arrays should not be changed during this process.
#	   For example, the contents of two input arrays:
#
 #               { 4, 0, 1, 2, 3 }  and   { 3, 4, 5, 6 }
#
#	   can be viewed as representing the following sequence of
#	   numbers (comprising the numbers from the first array followed
#	   by the numbers from the second):
#
 #               0, 1, 2, 3, 4, 5, 6.
#
#	   After rotating this sequence of numbers as specified, we
#	   obtain the following new sequence:
##
  #              1, 2, 3, 4, 5, 6, 0.
#
#	   And so, for this particular case the contents of the two
#	   arrays after rotation should be as follows:
#
 #               { 4, 1, 2, 3, 4 }  and   { 3, 5, 6, 0 }.
#
#	   Be sure that your program works correctly even if one or both
#	   of the input arrays is empty.  For example, a rotation of the
#	   two arrays:
#
 #               { 4, 0, 1, 2, 3 }  and   { 0 }
#
#	   should produce the following output arrays:
#
#		{ 4, 1, 2, 3, 0 }  and   { 0 }.
#
#	   In all cases, the return result (i.e., the value in eax at
#	   the end of your program) should be the total number of
#	   elements in the two arrays.  (So, for the two examples shown
 #          above, the return results would be 7 and 4, respectively.)
#
### This is where your code begins ...

        movl    $42, %eax     # replace this with your code!

### This is where your code ends ...

	ret





