package model.stmt;

import model.PrgState;
import model.expr.Expression;

public class PrintStatement implements Statement {
    Expression exp;
    public PrintStatement(Expression e){
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
