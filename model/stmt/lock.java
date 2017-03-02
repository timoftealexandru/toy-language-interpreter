package model.stmt;

import model.PrgState;
import utils.InterpreterException;
import utils.Lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Nicu on 1/25/2017.
 */
public class lock implements Statement {
    private Lock lock = new Lock();
    private ReentrantLock l=new ReentrantLock();
    private String var;
    public lock(String var){
        this.var=var;
    }
    public PrgState execute(PrgState p){
        try{
            l.lock();
            int foundIndex=-1;
            try{
                if(p.getSymbolTable().contains(var)){
                    foundIndex = p.getSymbolTable().getValue(var);
                }
                else{
                    System.out.println("Lock Error: Key not found in SymbolTable");
                }
            }catch(InterpreterException e){
                e.printStackTrace();
            }

            if(p.getLockTable().contains(foundIndex)){
                if(p.getLockTable().getValue(foundIndex)==-1){
                    p.getLockTable().setValue(foundIndex,p.getId());
                }
                else{
                    p.getExeStack().push(new lock("var"));
                    System.out.println("already locked");
                }
            }else{
                System.out.println("Lock Error: index not found in locktable");
            }

        }catch(InterpreterException e){
                e.printStackTrace();
        }

        l.unlock();
        return null;
    }
    public String toString(){
        return "lock("+var+")";
    }
}
