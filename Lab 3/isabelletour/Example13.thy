(* A Simple Compiler --------------------------------------- *)
theory Example13
imports Main Example08
begin

(* Let's try our hand at compiling IExpr values ... which means
   that we're going to need a target language ... *)

(* Individual instructions: *)

datatype Instr = LDC int
               | LOAD Var
               | ADD

type_synonym stack = "int list"

fun execInstr :: "Instr \<Rightarrow> mem \<Rightarrow> stack \<Rightarrow> stack"
 where "execInstr (LDC n) m s = n # s"
     | "execInstr (LOAD v) m s = (m v) # s"
     | "execInstr ADD m (x # y # s) = (x + y) # s"

(* Complete programs: *)

type_synonym Prog  = "Instr list"

fun execProg :: "Prog \<Rightarrow> mem \<Rightarrow> stack \<Rightarrow> stack"
 where "execProg [] m s = s"
     | "execProg (i#p) m s = execProg p m (execInstr i m s)"

(* Compilation rules: *)

fun compile :: "IExpr \<Rightarrow> Prog"
 where "compile (IntExpr n) = [LDC n]"
     | "compile (VarExpr v) = [LOAD v]"
     | "compile (PlusExpr l r) = compile l @ compile r @ [ADD]"

value "ex2"
value "compile ex2"
value "ex3"
value "compile ex3"

(* But is our compiler correct? *)

end
(* --------------------------------------------------------- *)
