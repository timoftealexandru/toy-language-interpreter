package model;
import model.stmt.Statement;
import utils.*;

public class PrgState{
    private IExeStack<Statement> stack;
    private ISymbolTable<String, Integer> symTable;
    private IOutput<Integer> out;
    private Statement stmt;
    private IFileTable<Integer,FileData> fTable;
    //private FileTable

    public PrgState(IExeStack<Statement> st,ISymbolTable<String,Integer> sy,IOutput<Integer> o,Statement stm,IFileTable<Integer,FileData> ft){
        stack=st;
        symTable=sy;
        out=o;
        stmt=stm;
        fTable=ft;
        stack.push(stm);
    }
    //getter,setters
    public IFileTable<Integer,FileData> getFileTable(){return fTable; }
    public IOutput<Integer> getOutput(){return out;}
    public IExeStack<Statement> getExeStack(){
        return stack;
    }
    public ISymbolTable<String,Integer> getSymbolTable(){
        return symTable;
    }
    @Override
    public String toString() {
        return stack.toString() + "\n"
                + symTable.toString() + "\n"
                + fTable.toString() + "\n"
                + out.toString();
    }
}
