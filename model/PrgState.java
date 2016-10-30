package model;
import utils.*;
import java.util.*;
import java.util.*;
public class PrgState{
    private IExeStack<Statement> stack;
    private ISymbolTable<String, Integer> symTable;
    private IOutput<Integer> out;
    private Statement stmt;

    public PrgState(IExeStack<Statement> st,ISymbolTable<String,Integer> sy,IOutput<Integer> o,Statement stm){
        stack=st;
        symTable=sy;
        out=o;
        stmt=stm;
        stack.push(stm);
    }
    //getter,setters
    public IOutput getOutput(){return out;}
    public IExeStack getExeStack(){
        return stack;
    }
    public ISymbolTable getSymbolTable(){
        return symTable;
    }
    public String toString(){
        StringBuffer s=new StringBuffer();
        s.append("Exe ");
        s.append(stack);
        s.append("\n ");
        s.append(symTable);
        s.append("\n ");
        s.append(out);
        s.append(stmt);
        return s.toString();
    }
}
