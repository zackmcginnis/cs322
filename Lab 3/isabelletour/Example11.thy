(* Pondering Simplifier Correctness ------------------------ *)
theory Example11
imports Main Example08
begin

(* Let's try a slightly more sophisticated approach ... *)

(* "plusConst n e" looks for a way to build an expression that
   is the sum of the (known) integer n and some IExpr e.
   Again, it looks for opportunities to apply constant folding
   and identity rules, but it can also take advantage of
   associativity to replace an expression of the form (x + m) + n
   with x + (m + n) when m and n are both known constants.
*)
fun plusConst :: "int \<Rightarrow> IExpr \<Rightarrow> IExpr"
 where "plusConst n (IntExpr m) = IntExpr (n+m)"
     | "plusConst n (PlusExpr e (IntExpr m)) = plusConst (n+m) e"
     | "plusConst n e = (if n=0 then e
                                else PlusExpr e (IntExpr n))"

(* "plus l r" looks for opportunities to exploit the optimizations
   provided by "plusConst"; that is, it looks for special cases
   where one of the two arguments has a known integer value.
*)
fun plus :: "IExpr \<Rightarrow> IExpr => IExpr"
 where "plus (IntExpr n) e = plusConst n e"
     | "plus e (IntExpr n) = plusConst n e"
     | "plus l r           = PlusExpr l r"

fun simpIExpr :: "IExpr \<Rightarrow> IExpr"
 where "simpIExpr (PlusExpr l r) = plus (simpIExpr l) (simpIExpr r)"
     | "simpIExpr other          = other"

(* Is our "optimization" correct? *)
value "PlusExpr ex2 ex3"
value "simpIExpr (PlusExpr ex2 ex3)"
(* It looks ok here, but can we be sure it will always work? *)

(* What if we could actually prove it correct? *)
theorem "eval (simpIExpr e) m = eval e m"
  sorry

end
(* --------------------------------------------------------- *)
