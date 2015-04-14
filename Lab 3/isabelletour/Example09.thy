(* Simplifying Expressions, Part 1 ------------------------- *)
theory Example09
imports Main Example08  (* Note that we're importing the previous theory! *)
begin

(* Let's try our hand at simplifying IExpr values.

   Specifically, we'll try some constant folding (recognizing
   that the sum of two known integer expressions can be collapsed
   into a single integer expression).  In addition, we'll look for
   opportunities to apply the identity rule: x + 0 = 0 + x = x.

   We'll handle these simplification steps with a "plus" function
   that takes two IExpr arguments and returns a new IExpr
   representing their sum.  It tries to use the techniques above,
   but if they don't apply, then of course we can just fall back
   to combining the two inputs as a PlusExpr.
*)
fun plus :: "IExpr \<Rightarrow> IExpr \<Rightarrow> IExpr"
 where "plus (IntExpr n) (IntExpr m) = IntExpr (n+m)"
     | "plus (IntExpr n) r = (if n=0 then r
                                     else PlusExpr r (IntExpr n))"
     | "plus l (IntExpr n) = (if n=0 then l
                                     else PlusExpr l (IntExpr n))"
     | "plus l r = PlusExpr l r" 

(* Our main simplification function simply traverses an input expression,
   applying simplification to the arguments of each PlusExpr and then
   using "plus" to see if those arguments can be combined without the
   need for a new PlusExpr.
*)
fun simpIExpr :: "IExpr \<Rightarrow> IExpr"
 where "simpIExpr (PlusExpr l r) = plus (simpIExpr l) (simpIExpr r)"
     | "simpIExpr other          = other"

(* Are we getting the kind of results we expect? *)
value "PlusExpr ex2 ex3"
value "simpIExpr (PlusExpr ex2 ex3)"
(* An improvement, but perhaps more could be done ... *)

end
(* --------------------------------------------------------- *)
