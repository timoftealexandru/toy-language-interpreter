package model;
import utils.*;
public class AssignStmt implements Statement {
    public String var;
    public Expression expr;
    public AssignStmt(String v, Expression e){
        var=v;
        expr=e;
    }
    public PrgState execute(PrgState p) {
        ISymbolTable t= p.getSymbolTable();
        int result = expr.evaluate(t);
        t.add(var,result);
        return p;
    }
    public String toString(){
        return ""+var+"="+expr;
    }
}
