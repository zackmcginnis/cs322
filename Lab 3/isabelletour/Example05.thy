(* Functions ----------------------------------------------- *)
theory Example05
imports Main
begin

(* Isabelle also supports function types, written using the =>
   symbol.  For example, the following definition introduces a
   function that adds one to its argument.
*)
definition addOne :: "int => int"
 where "addOne n = n+1"

(* The Isabelle environment will also let you use special symbols
   that are common in mathematical work but hard to type on a regular
   keyboard.  For example, the function arrow can also be written in
   prettier form using the \<Rightarrow> symbol instead of =>.  For this to work,
   just type in => and a little "autocomplete" box will pop up under
   the cursor showing the special symbol.  Hit "Tab" to take advantage
   of the special symbol, or just carry on typing to ignore it.

   Give this a try!  Define a function "myAddOne" that looks just like
   the definition above but uses the \<Rightarrow> symbol instead of =>.
*)

(* YOUR CODE GOES HERE! *)

(* How about defining a function that will take an integer input and
   output its square?
*)

(* YOUR CODE GOES HERE! *)

(* Of course, now that we've defined some functions, we can try to
   use them:
*)
value "addOne (addOne 2)"

(* Now try doing something with the square function that you defined
   and evaluate some simple expressions to check that it's working
   as you expect.
*)

(* YOUR CODE GOES HERE! *)

(* Function with more than one argument can be described using
   multiple function type arrows.  For example:
*)
definition diffSquares :: "int \<Rightarrow> int \<Rightarrow> int"
 where "diffSquares x y = x*x - y*y"
value "diffSquares 5 4"

(* using the name myMax because max is build in ... *)
definition myMax :: "int \<Rightarrow> int \<Rightarrow> int"
 where "myMax x y = (if x > y then x else y)"
value "myMax 5 4"
value "myMax 4 5"

(* This example also illustrates the use of an if-then-else
   expression.  Note that the parentheses are required here
   to avoid a syntax error.  If in doubt, it doesn't hurt
   to add parens!
*)

(* Suppose that we want to add up all of the numbers in a list.
   This can be accomplished by using a recursively defined function.
   But Isabelle will only allow definition commands to be used for
   nonrecursive definitions.  If you want to give a recursive
   function definition instead, then you need to use a fun command
   (and I mean that the command begins with the letters "fun",
   although that doesn't mean you shouldn't enjoy using it :-)
*)
fun sum :: "int list \<Rightarrow> int"
  where "sum []       = 0"
      | "sum (x # xs) = x + sum xs"

(* Notice that there are two defining equations here, one that
   describes how to calculate the sum of an empty list and one
   that describes how to calculate the sum of a non-empty list.
   The two equations are separated by a vertical bar.
*)

(* Let's try some quick tests: *)
value "sum [1,2,3,4,5]"

(* Position the cursor just after the definition of sum and look
   at the output window.  The message that you're seeing (don't worry;
   you don't need to understand it in detail) is Isabelle's way of
   indicating that it has proved that a call to this function will
   always return with a final result.  In other words, we might say
   that every call is guaranteed to terminate or, in mathematical
   terms, that the function is "total".

   Intuitively, it's clear why that works in this case: the size of
   the argument list gets one element smaller every time we make a
   recursive call.  As a result, given enough recursive calls, we'll
   get to a point where the argument list is empty, and we'll avoid
   a further recursive call, breaking the loop.

   But, absent any restrictions, we can also write down recursive
   function definitions that do not have this property, and that
   can enter an infinite loop when called with some (or possibly
   all) inputs.

   To illustrate that point, define a new function in the space below
   that will cause an infinite loop, and see how Isabelle responds ...
*)

(* YOUR CODE GOES HERE! *)

(* You will know that you've succeeded if the output window shows
   "Unfinished goals" ... if, instead, it says "Found termination
   order", or similar, then the system has figured out that your
   function will terminate, whatever argument you give it, even
   if you didn't expect that!

   This reflects the restriction that Isabelle, at least in the
   mode we are using, will only permit us to define total
   functions.  In other words, Isabelle requires that any
   function can be applied to any argument, so long as the
   types are compatible, and produce an output result, provided,
   in some cases, that you have enough patience to wait for it!

   For that reason, you'd better comment out your definition of
   a non terminating function if you want Isabelle to accept
   your file! 
*)

(* QUESTION: If Isabelle can tell whether or not a function
   is total, doesn't that mean it can solve the halting problem?




*) 

(* In practice, Isabelle is pretty good at determining whether a
   function will terminate:
*)
fun countdown :: "int \<Rightarrow> int list"
 where "countdown n = (if n>0 then (n # countdown (n - 1)) else [])"
value "countdown 5"
value "sum (countdown 5)"

(* But it isn't foolproof ... try uncommmenting the following, for
   example:
*)
(* 
fun countup :: "int \<Rightarrow> int \<Rightarrow> int list"
 where "countup i n = (if i<n then (i # countup (i+1) n) else [])"
*)

(* As a final note, Isabelle requires that every function is total,
   but that doesn't mean you have to say what the result will be for
   every possible input.  Here, for example, is a variation of the
   sum function that leaves the sum of the empty list undefined:
*)

fun sm :: "int list \<Rightarrow> int"
 where "sm (x # xs) = x + sm xs"
  (* notice the message in the output window here ... *)

value "sm [1,2,3]"
  (* and notice the value that is displayed here ... *)

end
(* --------------------------------------------------------- *)
