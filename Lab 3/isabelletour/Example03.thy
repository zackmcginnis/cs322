(* The definition command ---------------------------------- *)
theory Example03
imports Main
begin

(* We can give names to values so that we can use them later: *)
definition fortytwo where "fortytwo = (6*7::int)"

(* Aside: Precedence matters - try removing the parentheses ... *)

(* For example: *)
value "fortytwo+1"

(* We can also include types as part of definitions: *)
definition three :: int where "three = 3"

value "three"

end
(* --------------------------------------------------------- *)
