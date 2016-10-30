package model;

public class IfStatement implements Statement {
    Expression exp;
    Statement thenS;
    Statement elseS;
    public IfStatement(Expression e, Statement s1,Statement s2){
        exp=e;
        thenS=s1;
        elseS=s2;
    }
    public PrgState execute(PrgState state){
        if(exp.evaluate(state.getSymbolTable())==0){
            state.getExeStack().push(elseS);
        }else{
            state.getExeStack().push(thenS);
        }
        return state;
    }
    public String toString(){
        return "IF("+ exp.toString()+") THEN(" +thenS.toString()+")ELSE("+elseS.toString()+")";

    }
}
