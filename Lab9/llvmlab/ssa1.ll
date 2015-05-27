; declare external runtime library functions
declare void @Xprint(i32)

; function definitions
define void @XinitGlobals() {
entry:
  ret void
}

define void @Xmain() {
  ; int i=0;
  ; int t=0;
  ; while (i<10) {
  ;    t = t+i
  ;    i = i+1
  ; }
  ; print t

entry:
  br label %test

test:
  %i = phi i32 [ 0, %entry ], [ %ib, %body ]
  %t = phi i32 [ 0, %entry ], [ %tb, %body ]
  %cmp = icmp slt i32 %i, 10
  br i1 %cmp, label %body, label %done

body:
  %tb = add i32 %t, %i
  %ib = add i32 %i, 1
  br label %test

done:
  call void @Xprint(i32 %t)
  ret void
}

