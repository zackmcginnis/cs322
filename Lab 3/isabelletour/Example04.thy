(* Lists --------------------------------------------------- *)
theory Example04
imports Main
begin

(* We'll take a quick look at some of the other types that
   are built in to Isabelle.  Although some of Isabelle's
   notation is a bit different, this might still remind you
   of some of the things you saw in the types lab last
   term using Haskell ...
*)

(* In addition to integers, represented by the int type,
   Isabelle also includes support for lists.  Following
   the conventions of the ML programming language, list
   types are written using a postfix syntax.  For example,
   the type of list of integers is written as "int list":
*)

value "[1, 2, 3, 4, 5] :: int list"

(* In a similar way, the type of a list of lists of integers
   is written as "int list list":
*)
value "[[1,2,3], [4,5,6], [5,0,3]] :: int list list"

(* Note that all of the elements in an Isabelle list have
   the same type.
*)

(* Lists can be created by enumerating a sequence of elements,
   as in the examples above.  As a special case, the empty list
   of elements is written "[]".  We can also create a new list
   by "cons"ing and extra element x on to the front of another
   list xs; the result is written "x # xs" and we might refer
   to the value "x" as the head of this new list and to "xs"
   as the tail.  Finally, we can concatenate list values together
   using the @ operator.
*)
value "[] :: int list"
value "4 # [] :: int list"
value "3 # 4 # [] :: int list"
value "2 # 3 # 4 # [] :: int list"
value "1 # 2 # 3 # 4 # [] :: int list"

value "[1,2,3] @ [4, 5, 6] :: int list"

(* Try changing the list expressions above to introduce a type
   error, for example, by replacing one of the integer constants
   with a list of integers, and see what happens ...
*)

end
(* --------------------------------------------------------- *)
