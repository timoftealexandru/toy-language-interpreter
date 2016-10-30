package main;
import repo.*;
import  utils.*;
import model.*;
import controller.*;

import javax.swing.plaf.nimbus.State;


public class Main {
    public static void main(String Args[]){

        Statement s1= new CompStatement(new AssignStmt("a", new ArithmeticExpr('-',new ConstExp(2), new
                ConstExp(2))),
                new CompStatement(new IfStatement(new VarExpr("a"),new AssignStmt("v",new ConstExp(2)), new
                        AssignStmt("v", new ConstExp(3))), new printStatement(new VarExpr("v"))));

        Statement s2= new AssignStmt("b", new ArithmeticExpr('-',new ConstExp(7),new
                ArithmeticExpr('/',new ConstExp(4), new ConstExp(0))));
        Repository repo=new Repository();
        Controller ctrl=new Controller(repo);

        try{
            PrgState first=new PrgState(new ExeStack<Statement>(), new SymbolTable<String, Integer>(),new Output<Integer>(), s1);
          repo.addPrgState(first);

            PrgState second=new PrgState(new ExeStack<Statement>(), new SymbolTable<String, Integer>(),new Output<Integer>(), s2);
 //           repo.addPrgState(second);
            ctrl.executeAll();
        }catch(Exception e){
            System.out.println("Exception: " + e);
        }

    }
}
