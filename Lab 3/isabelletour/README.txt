------------------------------------------------------------
CS 322 Spring 2015, Lab 3:

            A tour of the Isabelle Proof Assistant
                       and its use in
          Formalizing Programming Language Semantics

------------------------------------------------------------

This lab provides an introduction to the use of the Isabelle
proof assistant and some examples of of how it can be used in
the formalization of programming language semantics.

This lab is broadly inspired by the opening chapters of the
recently released book "Concrete Semantics" by Tobias Nipkow
and Gerwin Klein.  (It has also been adapted to use some of
the same terms and ideas as in the previous, interpretour
lab.)  As it happens, Tobias Nipkow is one of the primary
developers of Isabelle, and Gerwin Klein lead the work on the
formal verification of the seL4 microkernel.  (In fact,
Gerwin was also the developer of the jflex lexical analyzer
generator that we used in CS 321!)  The full text of the book
(in pdf format), together with code and slides, is available
for free download and study from:

   http://concrete-semantics.org

Besides an introduction to Isabelle, this book also covers
topics that are very relevant to us in CS 322, including
operational semantics, compiler correctness, type systems,
program analyses, denotational semantics, and Hoare logic.
The book, however, goes in to much greater depth than we
have time for in this lab.  Some of you may enjoy spending
time reading the book and working through the exercises
that it provides later on.  For the purposes of CS 322,
however, we won't assume or require that you have looked
at the book.

We will be using the 2013-2 release of Isabelle, which is
already installed on the LinuxLab computers.  However, you
will need to use the addpkg tool to select the "isabelle 2013"
package, then log out and log back in before it becomes
usable; I will walk you through this process at the start of
the lab.  Once that has all been done, you should be able to
start the GUI interface using the command:

  Isabelle2013-2

or, perhaps by running:

  isabelle jedit

Do not be alarmed if the program take a little while to start
when it is run for the first time.  This is mostly because it
is building (or, to put it another way, compiling) a
collection of its built-in theory files, but start up times
should be faster after that initial run.

-----------------
ASIDE: If you prefer to use Isabelle on your own machine, you
can download a copy (for free, for Windows, Mac, or Linux)
from:

   http://isabelle.in.tum.de

The version advertised on that page is more recent than the
one that is installed on the LinuxLab (2014 vs 2013-2).  I am
not aware of any incompatibilities between the newer and old
versions that will have an impact on this tour, but if you do
run in to any difficulties, you can still download the 2013-2
release directly from:

  http://isabelle.in.tum.de/website-Isabelle2013-2/index.html 

-----------------

Isabelle is a complex and sophisticated piece of software that
has been used for formal verification work in some large and
complicated projects.  We will only be exploring a very small
fraction of what Isabelle can do here, and do not expect any
one of us (the instructors included!) to leave the lab as
experts in Isabelle!

One final note: Technically speaking, Isabelle is referred to
as a *generic* proof assistant because it can be used with many
different logical systems.  The default configuration that we
are using here is one particular instance of that and is
commonly referred to as Isabelle/HOL, the latter part of the
name being an abbreviation for "Higher Order Logic".  So now
you know :-)

The rest of this file contains some notes that describe key
goals for each of the example "theory" files that are included
in the tour.  After you have started Isabelle, I recommend
that you use the "Open ..." option in the "File" menu to
navigate to the "isabelletour" folder and select the set of
Example??.thy files from there.  After that, you can use the
drop down list at the top of the editor window to switch
between the example files.  Again, we will walk through
this process in the lab session.

------------------------------------------------------------
Example00.thy: Basic Syntax

Introduce the basic structure of an Isabelle theory file and
the notation that we'll use for comments.  An opportunity to
show "bracketed comments" to anyone who hasn't seen them
before.

------------------------------------------------------------
Example01.thy: The Interface

Point to various components of the Isabelle/jEdit interface
that might be useful.

------------------------------------------------------------
Example02.thy: The value command

Introduce "value" as a way of finding the values of simple
terms.  Comment on the distinction between inner and
outer languages in theory files, the former (corresponding
to logical statements) being written inside double quotes.

------------------------------------------------------------
Example03.thy: The definition command

Illustrate simple uses of definition as a way to name
values of interest so that you can refer to them later.
This is a simple way to handle things that do not require
a recursive definition.

------------------------------------------------------------
Example04.thy: Lists

A quick look at lists.  The type constructor is written postfix,
as in "int list", following the conventions of ML.  List values
can be written as comma separated enumerations surrounded by
square brackets.  The cons and append operations are # and @,
respectively.  Also an opportunity to illustrate how the interface
responds to type errors in input code.

------------------------------------------------------------
Example05.thy: Functions

Non-recursive function definitions can be introduced using the
definition command.  Recursive functions require the use of the
fun command, and will be rejected if the built-in termination
checker cannot prove that they are total.  Of course, we cannot
solve the halting problem, so the termination is necessarily
conservative, rejecting some definitions that do, in fact,
define total functions.  Oh well.

This example also gives an opportunity to talk about the
possibility of using prettier symbols in Isabelle scripts,
such as the single character implication/function arrow
symbol in place of =>.

A final example highlights the fact that a function does
not have to be fully defined to be total.  In other words;
function definitions can be underspecified.

------------------------------------------------------------
Example06.thy: Proofs

Introduce the idea of using Isabelle to construct proofs,
and showing two simple examples of proofs of properties
about programs introduced in the previous theory files.

The first proof, showing that the maximum of a pair of
numbers is greater than or equal to each of those numbers
can be proved automatically by using the built-in
mechanisms for simplification.  The only trick is that
we need to let the simplification engine use the definition
of the myMax function, which is not made available by
default.  This is accomplished by using the incantation
add: myMax_def to the simp proof method.

The second is a simple proof by induction.  You can pause
after the (induction xs) line to look at and discuss the
generated subgoals.

The purpose of these examples is to show a style of proof
in which the user gives the proof assistant a little bit
of a hint about how to begin and then lets the automated
part of the sytem take care of the remainder of the proof.

------------------------------------------------------------
Example07.thy: Datatypes and ASTs

What if we want to work with a type of values that is not
included as part of the default, Main environment?

This example shows how we can define a simple datatype
corresponding to the abstract syntax of integer expressions.
This ties in with material that students have seen in CS 321
(the types in Haskell lab, for example) as well as with the
interpreter lecture in CS 322.  We see the form of the
datatype definition, as well as some examples that construct
simple ASTs.

We can also define a simple evaluator as a recursive function
using a fun command, and then test it on our example programs.
This corresponds to providing a *denotational* semantics for
the IExpr type.

There is an opportunity for a quick exercise here, adding an
extra constructor, together with an extra case for eval, to
support multiplications.

------------------------------------------------------------
Example08.thy: Expressions with variables

Extending the abstract syntax type from the previous example to
include variables.  This requires the introduction of a mapping
from variables to values, represented by the mem type.  The
evaluator is now modified to take an extra mem argument.  (Or,
from a different perspective, we now use "mem => int" as the
semantic domain for IExpr instead of just int.)

[Incidentally, I have tried to follow the convention of using
type names beginning with an upper case letter for syntactic
objects (such as IExpr) and type names with a lower case letter
for semantic objects (such as mem, int, ...)]

Variable names are represented by strings, which are formed by
writing the variable name between pairs of single quote
characters, as in ''a''.  (Remember that double quotes are
already used to distinguish between Isabelle's inner and outer
languages.)  I also introduce the syntax  f(x := y)  for the
function that is (almost) the same as f except that it maps x
to y.

There is an opportunity to repeat the MultExpr exercise from
the previous case here.  It is arguably more interesting to
do this here because we'll be building on this theory in most
of the remaining steps.

------------------------------------------------------------
Example09.thy: Simplifying Expressions, Part 1

Writing some code to rewrite IExpr values to do constant
folding and rewrite x + 0 = x, etc.  Mostly just shows
the construction of a more interesting recursive function.

Running the example at the end shows that we make some good
progress, but perhaps not quite as much as we'd like; there
is still opportunity for improvement.

------------------------------------------------------------
Example10.thy: Simplifying Expressions, Part 2

A slightly more sophisticated approach.  Based on the
insight that you can always rewrite an addition with
one constant argument by putting the constant in the
right argument position.  This makes it possible to
do some transformations like:

   (x + m) + n  ===>   x + (m + n)

where m and n are constants and hence m+n can be
computed at compile-time.

The example at the end of this theory suggests that the
code is working correctly.  But now the implementation
is getting quite complicated: are we sure there are no
bugs?

------------------------------------------------------------
Example11.thy: Pondering Simplifier Correctness

This is the same as the previous file except that it adds
a theory (with the stop gap justification of "sorry") at
the end in which we've formulated a suitable correctness
property.  Note that simpIExpr is, in effect, a simple
compiler that just happens to use the same source and
target languages, so this is really just a compiler
correctness property.

------------------------------------------------------------
Example12.thy: Proving Simplifier Correctness

This theory includes the completed proof of the correctness
theorem that was introduced in the previous file.  It's best
to build this up interactively while still in Example11.thy
so that students can see how we work backwards, filling in
new lemmas as necessary to complete the proof.  However you
can also used it as is and say less about the proofs.

Note that adding "rule: foo.induct" on the end of an
induction method indicates that we should base the structure
of the inductive proof on the recursive structure of the
function foo.  The "arbitrary: n m" annotation similarly
guides Isabelle in generating the right version of the
induction result in which m and n can vary from the induction
hypothesis to the specific conclusion that we want to prove.

All kinds of studen exercises are possible here, such as
adding in support for multiplication expressions, and then
trying to normalize every combination of multiplies and adds
with constants in to expressions of the form:  x*n + m  where
n and m are constants.  For example, we know that we can rewrite:

    (x*n+m) + a   =   x*n + (m+a)
    (x*n+m) * a   =   x*(n*a) + (m*a)

(Assuming, of course, that a is also a constant.)

------------------------------------------------------------
Example13.thy: A Simple Compiler

Introduces a very simple virtual machine language for a basic
stack machine, and a compiler that maps IExpr programs to lists
of machine code instructions.  We also include a semantics (or
an interpreter, if you prefer) for the machine language.

Looking at some specific examples and the generated code
suggests that all is working correctly.  But is it possible
that we've overlooked something that could cause a bug?

------------------------------------------------------------
Example14.thy: Proving Compiler Correctness

Builds on the previous file by adding in the completed proof
of correctness.  Again, it might be best to build this proof
interactively while in the previous file.  The proof is
surprisingly straightforward; there is only one lemma.

By the time we have completed these proofs, we have formally
established the standard, desired correctness property for
our simple compiler.  Yay!

------------------------------------------------------------
