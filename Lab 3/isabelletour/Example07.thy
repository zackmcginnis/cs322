(* Datatypes and ASTs -------------------------------------- *)
theory Example07
imports Main
begin

(* What if you want to work with a type that isn't built in?
   Abstract syntax trees, for example:
*)
datatype IExpr = IntExpr int
               | PlusExpr IExpr IExpr

definition one where "one = IntExpr 1"
definition two where "two = IntExpr 2"
definition ex1 where "ex1 = PlusExpr one two"
definition ex2 where "ex2 = PlusExpr ex1 ex1"

value ex1
value ex2

(* What does an IExpr represent?  Or, to put it another way,
   what does an IExpr denote?
*)
fun eval :: "IExpr \<Rightarrow> int"
 where "eval (IntExpr n) = n"
     | "eval (PlusExpr l r) = eval l + eval r"

(* We have a simple *denotational semantics* for IExpr ... *)
value "eval one"
value "eval two"
value "eval ex1"
value "eval ex2"

(* Now go back and extend the definition of IExpr to include
   MultExprs, and then adjust the definition of eval so that
   these new forms of expression are treated as multiplications.
   Add some new examples to check that things are working as
   you expect ...
*)

end
(* --------------------------------------------------------- *)
