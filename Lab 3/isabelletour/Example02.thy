(* The value command --------------------------------------- *)
theory Example02
imports Main
begin

(* The value command can be used to perform simple calculations. *)
value "6*7::int"

(* Move the cursor on to or past the command to see its output. *)

(* Isabelle files blend two languages:

   - The *outer* language is made up using constructs like
     "theory", "begin", and "value".  This is the language
     that we us to give commands, introduce definitions,
     construct proofs, etc.

   - The *inner* language that contains types like int and
     nat, values like 6 and 7, and operations like *.  This
     is the language that we use for the programs and
     properties that we want to describe and reason about.

   Isabelle users distinguish between the two by writing
   inner language fragments between double quotes.

   As a special case, double quotes are not needed around
   single identifiers/names.
*)

(* Use a value command to calculate the sum of
   the numbers from 1 to 5. *)

(* YOUR CODE GOES HERE! *)

(* Note that the "value" command mostly leaves undefined
   variables untouched, unless it can simplify them away: *)

value "x + 1::int"
value "x + 0::int"
value "x * 1::int"
value "x * 0::int"

end
(* --------------------------------------------------------- *)
