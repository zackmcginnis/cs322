	.file	"out.s"

	.data
Xx:
	.long	0
Xy:
	.long	0

	.text
	.globl  XinitGlobals
XinitGlobals:
	pushq	%rbp
	movq	%rsp, %rbp
	movl	$2, %eax
	movl	%eax, Xx
	movl	Xx, %eax
	movl	Xx, %edi
	imull	%edi, %eax
	movl	%eax, Xy
	movq	%rbp, %rsp
	popq	%rbp
	ret

	.globl	Xmain
Xmain:
	pushq	%rbp
	movq	%rsp, %rbp
	movl	Xy, %edi
	call	Xprint
	movq	%rbp, %rsp
	popq	%rbp
	ret

