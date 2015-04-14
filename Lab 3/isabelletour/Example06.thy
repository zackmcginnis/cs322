(* Proofs: ------------------------------------------------- *)
theory Example06
imports Main
begin

(* Isabelle isn't just a tool for defining and calculating
   values: it can also be used to help us prove properties.

   Isabelle helps to automate the task of constructing proofs:

   - it checks all of the proof steps to make sure that they
     are valid

   - it has built in algorithms/tactics for simplifying
     complicated terms, searching for proofs by exploring
     different proof strategies, etc...

   But, in general, it still needs guidance from a human
   operator (which is why we refer to Isabelle as a proof
   *assistant*.  In this lab, we'll limit ourselves to
   a very small (but important) set of the tools that
   Isabelle provides:

   - the ability to perform proofs by induction

   - automatic proof search (using "auto")

   - the definition of intermediate "lemmas"

   By following the model of some of the examples that we
   provide, you should be able to construct very simple
   proofs on your own.  However, this is an advanced and
   deep topic, so you can be assured that we won't expect
   you to construct substantial new proofs on your own.
*)

(* We previously defined a function for calculating the
   maximum of two inputs:
*)
definition myMax :: "int \<Rightarrow> int \<Rightarrow> int"
 where "myMax x y = (if x > y then x else y)"
value "myMax 5 4"
value "myMax 4 5"

(* We would expect the maximum of two numbers to be
   greater than or equal to each of those two numbers:
*)
lemma "(myMax x y \<ge> x) \<and> (myMax x y \<ge> y)"
  apply (simp add: myMax_def)
  done

(* We also defined a function for adding up the numbers
   in a list of integers:
*)
fun sum :: "int list \<Rightarrow> int"
  where "sum []       = 0"
      | "sum (x # xs) = x + sum xs"

(* We would expect the sum of a concatenated pair of lists
   to give the same result as adding the sums of the
   individual lists:
*)
theorem "sum (xs @ ys) = sum xs + sum ys"
  apply (induction xs)
  apply auto
  done

end
(* --------------------------------------------------------- *)
