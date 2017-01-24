package model.stmt;

import model.PrgState;
import utils.IExeStack;

/**
 * Created by Nicu on 1/22/2017.
 */
public class SleepStatement implements Statement{
    private Integer number;

    public SleepStatement(Integer nr){
        number=nr;
    }
    public PrgState execute(PrgState p){
        IExeStack exeStack = p.getExeStack();
        if(number!=0){
            exeStack.push(new SleepStatement(number-1));
        }
        return null;
    }
    public String toString(){
        return "Sleep(" +number+")";
    }
}
