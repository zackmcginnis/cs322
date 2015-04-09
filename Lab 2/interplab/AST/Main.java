import ast.*;

public class Main {
    public static void main(String[] args) {
        Defn[] program
          = new Defn[] {
              new Globals(Type.INT, new VarIntro[] {
                new InitVarIntro("x", new IntLit(1))
              }),
              new Function(null, "main", new Formal[0],
                new Block(new Stmt[] {
                  new While(new Lt(new Id("x"), new IntLit(10)),
                    new Block(new Stmt[] {
                      new Print(new Id("x")),
                      new ExprStmt(new Assign("x",
                                              new Add(new Id("x"),
                                                      new IntLit(1))))
                    })
                  )
                })
              )
            };

        new IndentOutput(System.out).indent(program);
    }
}
