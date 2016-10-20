package model;
import utils.*;
public class CompStatement implements Statement {
    private Statement first;
    private Statement second;

    public CompStatement(Statement f,Statement s){
        first=f;
        second=s;
    }
    public PrgState execute(PrgState p){
        IExeStack exe = p.getExeStack();
        exe.push(second);
        exe.push(first);
        return p;
    }
    public String toString(){
        return "{"+first+" "+second+"}";
    }
}
