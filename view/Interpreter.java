package view;
import model.expr.ConstExp;
import model.expr.VarExpr;
import model.stmt.*;
import utils.*;
import model.*;
import repo.*;
import controller.*;
class Interpreter {
    public static void main(String[] args) {
        Statement s1 =
                new CompStatement(
                        new CompStatement(new OpenFileStatement("test", "var_f"), new ReadFile("var_f", "var_n")),
                        new CompStatement(new PrintStatement(new VarExpr("var_c")),
                                new CompStatement(
                                        new IfStatement(
                                                new VarExpr("var_c"),
                                                new CompStatement(new ReadFile("var_f", "var_c"), new PrintStatement(new VarExpr("var_c"))),
                                                new PrintStatement(new ConstExp(0))
                                        ),
                                        new CloseReadFile("var_f")
                                )
                        )
                );

        Statement s2 = new CompStatement(
                new OpenFileStatement("var_f", "test.in"),
                new CompStatement(
                        new ReadFile("var_f", "var_c"),
                        new CompStatement(
                                new PrintStatement(new VarExpr("var_c")),
                                new CompStatement(
                                        new IfStatement(
                                                new VarExpr("var_c"),
                                                new CompStatement(
                                                        new ReadFile("var_f", "var_c"),
                                                        new PrintStatement(new VarExpr("var_c"))
                                                ),
                                                new PrintStatement(new ConstExp(0))
                                        ),
                                        new CloseReadFile("var_f"))
                        )
                )
        );

        PrgState first = new PrgState(new ExeStack<Statement>(), new SymbolTable<String, Integer>(), new Output<Integer>(), s2, new FileTable<Integer, FileData>());

        Repository repo = new Repository("./logs/allLogs.txt");
        Controller ctrl = new Controller(repo);
        repo.addPrgState(first);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", s1.toString(), ctrl));

        menu.show();
    }
}