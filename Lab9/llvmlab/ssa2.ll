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
  %i.addr = alloca i32
  %t.addr = alloca i32
  store i32 0, i32* %i.addr
  store i32 0, i32* %t.addr
  br label %test

test:
  %i.test = load i32* %i.addr
  %cmp = icmp slt i32 %i.test, 10
  br i1 %cmp, label %body, label %done

body:
  %t.body = load i32* %t.addr
  %i.body = load i32* %i.addr
  %t.new  = add i32 %t.body, %i.body
  store i32 %t.new, i32* %t.addr
  %i.new  = add i32 1, %i.body
  store i32 %i.new, i32* %i.addr
  br label %test

done:
  %t.done = load i32* %t.addr
  call void @Xprint(i32 %t.done)
  ret void
}

