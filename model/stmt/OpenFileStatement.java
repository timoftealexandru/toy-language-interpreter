package model.stmt;
import java.io.*;
import java.util.*;

import model.PrgState;
import utils.*;
public class OpenFileStatement implements Statement {
    private String fName;
    private String varName;
    public OpenFileStatement(String vName,String fName){
        this.fName=fName;
        this.varName=vName;
    }
    public PrgState execute(PrgState p){
        if(isOpen(fName, p.getFileTable())){
            throw new InterpretorException("File is already open "+ this.fName);
        }
        try{
            BufferedReader br=new BufferedReader(new FileReader (fName));
            FileData fd=new FileData(fName,br);
            int id=FileIdGenerator.generateId();
            p.getFileTable().add(id,fd);
            p.getSymbolTable().add(varName,id);
        }
        catch(IOException e){
            throw new InterpretorException("Error while reading from file: "+e,e);
        }
        return p;
    }
    private boolean isOpen(String fn,IFileTable<Integer,FileData> ft){
        for(Map.Entry<Integer,FileData> it: ft.getAll()){
            if(fn.equals(it.getValue().getFileName()))
                return true;
        }
        return false;
    }
    public String toString(){
        return ""+fName+" "+varName;
    }
    
}
