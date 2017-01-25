package model.stmt;

import model.stmt.*;
import model.PrgState;
import model.expr.Expression;
import utils.IExeStack;

/**
 * Created by Nicu on 1/24/2017.
 */
public class ForStatement implements Statement{
    private Expression exp1;
    private Statement stmt,stmt1,stmt2;

    public ForStatement(Statement s1,Expression e1,Statement s2, Statement s){
        this.exp1=e1;
        this.stmt=s;
        this.stmt1=s1;
        this.stmt2=s2;
    }
    public PrgState execute(PrgState p){
        IExeStack exeStack = p.getExeStack();
        //Statement st = p.getExeStack().pop();
        //System.out.println("aici---"+st);
        //v=exp1;(while(v<exp2) stmt;v=exp3)
        Statement newSt =
                new CompStatement(
                    stmt1,
                    new WhileStatement(
                            exp1,
                            new CompStatement(
                                    stmt,
                                    stmt2
                            )
                    )
                );
        exeStack.push(newSt);
        return null;
    }
    public String toString(){
        return " For(" +stmt1+";"+exp1+";"+ stmt2+"){"+stmt+"}";
    }
}

