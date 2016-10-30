package model;

import utils.IOutput;

public class printStatement implements Statement {
    Expression exp;
    public printStatement(Expression e){
        exp=e;
    }
    public PrgState execute(PrgState state){
        int res=exp.evaluate(state.getSymbolTable());
        state.getOutput().add(res);
        return state;
    }
    public String toString(){
        return "print(" +exp.toString()+")";
    }

}
