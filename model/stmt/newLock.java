package model.stmt;

import model.PrgState;
import model.expr.Expression;
import utils.IExeStack;
import utils.ISymbolTable;
import utils.InterpreterException;
import utils.Lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Nicu on 1/25/2017.
 */
public class newLock implements Statement {
    private String var;
    private Lock lock = new Lock();
    private ReentrantLock l=new ReentrantLock();
    public newLock(String v){
        this.var=v;
    }
    public PrgState execute(PrgState p){
    try {

            p.getLockTable().setValue(-1,-1);
            if (p.getSymbolTable().contains(var)) {
                p.getSymbolTable().setValue(var, -1);
            } else {
                p.getSymbolTable().add(var, -1);
            }

        }catch(InterpreterException e){
             e.printStackTrace();
        }

        return null;
    }
    public String toString(){
        return " newLock("+var+")";
    }
}
