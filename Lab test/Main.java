class Main {
  public static void main(String[] args) {

  
  //normal and LTE
  /*    Stmt s
     = new Seq(new Assign("t", new Int(0)),
       new Seq(new Assign("i", new Int(0)),
       new Seq(new While(new LTE(new Var("i"), new Int(11)),
                         new Seq(new Assign("t", new Plus(new Var("t"), new Var("i"))),
                                 new Assign("i", new Plus(new Var("i"), new Int(1))))),
               new Print(new Var("t")))));
*/	


   //not operator
/*		   
    Stmt s
     = new Seq(new Assign("t", new Int(15)),
       new Seq(new Assign("i", new Int(0)),
       new Seq(new While(new Not(new LT(new Var("t"), new Int(11))),
                         new Seq(new Assign("i", new Plus(new Var("t"), new Var("i"))),
                                 new Assign("t", new Minus(new Var("t"), new Int(1))))),
               new Print(new Var("t")))));
	 */

	 
	 //mult
   /*
    Stmt s
     = new Seq(new Assign("t", new Int(1)),
       new Seq(new Assign("i", new Int(1)),
       new Seq(new While(new LT(new Var("t"), new Int(5)),
                         new Seq(new Assign("i", new Mult(new Var("t"), new Var("i"))),
                                 new Assign("t", new Plus(new Var("t"), new Int(1))))),
               new Print(new Var("i")))));
	 */
	 
  //isEven
  /*
    Stmt s
     = new Seq(new Assign("t", new Int(2)),
       new Seq(new Assign("i", new Int(1)),
       new Seq(new While(new isEven(new Var("t")),
                         new Seq(new Assign("i", new Plus(new Var("t"), new Var("i"))),
                                 new Assign("t", new Plus(new Var("t"), new Int(1))))),
               new Print(new Var("t")))));
	 */

  //halveOp	 
	  /* 
    Stmt s
     = new Seq(new Assign("t", new Int(1)),
       new Seq(new Assign("i", new Int(1)),
       new Seq(new While(new LTE(new Var("i"), new Int(11)),
                         new Seq(new Assign("t", new Plus(new Var("t"), new Var("i"))),
                                 new Assign("i", new Plus(new Var("i"), new Int(1))))),
               new Print(new Var("t")))));
	 */
	 
	 //question 2  works, but interp says 42, comp says 0!
	       Stmt s
     = new Seq(new Assign("t", new Int(0)),
       new Seq(new Assign("x", new Int(6)),
	   new Seq(new Assign("y", new Int(7)),
       new Seq(new While(new LT(new Int(0), new Var("x")),
	                 new Seq(new If(new Not(new isEven(new Var("x"))),
                                new Assign("t", new Plus(new Var("t"), new Var("y"))),
                                new Assign("t", new Var("t"))),
					new Seq(new Assign("y", new Plus(new Var("y"), new Var("y"))),
					new Assign("x", new halveOp(new Var("x")))))),
               new Print(new Var("t"))))));

	 
	 
	 
	 /*
	     int count = 0;
    int passed = 0;
    for (int i=-10; i<=10; i++) {
        for (int j=-10; j<=10; j++) {
            Expr e = new Mult(new IntExpr(i), new IntExpr(j));
            count++;
            if (e.eval(mem)==i*j) {
                passed++;
            } else {
                System.out.println("test for i="+i+", j="+j+" failed");
            }
        }
    }
    System.out.println(passed + "/" + count + " tests passed");
	*/
	 
    System.out.println("Complete program is:");
    s.print(4);

    System.out.println("Running on an empty memory:");
    Memory mem = new Memory();
    s.exec(mem);

    System.out.println("Compiling:");
    Program p     = new Program();
    Block   entry = p.block(s.compile(p, new Stop()));
    System.out.println("Entry point is at " + entry);
    p.show();

    System.out.println("Running on an empty memory:");
    mem      = new Memory();
    Block pc = entry;
    while (pc!=null)  {
      pc = pc.code().run(mem);
    } 

    System.out.println("Done!");
  }
}
