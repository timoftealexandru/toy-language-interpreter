package main;
import repo.*;
import  utils.*;
import model.*;
import controller.*;


public class Main {
    public static void main(String Args[]){
        Statement progStmt= new AssignStmt("a", new ArithmeticExpr('-',new ConstExp(7),new
                ArithmeticExpr('*',new ConstExp(3), new ConstExp(5))));
        Repository repo=new Repository();
        Controller ctrl=new Controller(repo);
        PrgState initialPrgState=new PrgState(new ExeStack<Statement>(), new SymbolTable<String, Integer>(),new Output<Integer>(), progStmt);
        repo.addPrgState(initialPrgState);
        ctrl.executeAll();
    }
}
