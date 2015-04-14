(* Expressions with variables ------------------------------ *)
theory Example08
imports Main
begin

(* What if we want to allow variables to appear in expressions?
*)
type_synonym Var = string

datatype IExpr = VarExpr  Var
               | IntExpr int
               | PlusExpr IExpr IExpr

definition one where "one = IntExpr 1"
definition two where "two = IntExpr 2"
definition ex1 where "ex1 = PlusExpr one two"
definition ex2 where "ex2 = PlusExpr ex1 ex1"

value ex1
value ex2

definition vx  where "vx  = VarExpr ''x''"
definition vy  where "vy  = VarExpr ''y''"
definition ex3 where "ex3 = PlusExpr vx one"

value ex3

(* Now what does an IExpr represent?  In particular,
   how do we account for the presence of variables?
*)
type_synonym mem = "Var \<Rightarrow> int"
definition memzero :: mem where "memzero v = 0"
definition memalt  :: mem where "memalt = memzero(''x'' := 41)"

fun eval :: "IExpr \<Rightarrow> mem \<Rightarrow> int"
 where "eval (VarExpr v)    m = m v"
     | "eval (IntExpr n)    m = n"
     | "eval (PlusExpr l r) m = eval l m + eval r m"

(* the following commands need a little update if we want to
   see some numeric results ...
 *)
value "eval one"
value "eval two"
value "eval ex1"
value "eval ex2"

(* How about an example that uses variables: *)
value ex3
value "eval ex3 memzero"
value "eval ex3 memalt"

(* Repeat the exercise from the previous theory file:
   Go back and extend the definition of IExpr to include
   MultExprs, and then adjust the definition of eval so that
   these new forms of expression are treated as multiplications.
   Add some new examples to check that things are working as
   you expect ...
*)

end
(* --------------------------------------------------------- *)
