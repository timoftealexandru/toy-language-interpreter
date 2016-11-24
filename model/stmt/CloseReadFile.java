package model.stmt;
import model.PrgState;
import utils.*;
import java.io.*;

public class CloseReadFile implements Statement {
    private String fileId;
    public CloseReadFile(String fId){
        fileId=fId;
    }

    public PrgState execute(PrgState p){
        Integer e=p.getSymbolTable().getValue(fileId);
        if(e==null){
            throw new InterpretorException("Faf");
        }
        FileData a=p.getFileTable().getValue(e);
        if(a==null){
            throw new InterpretorException("faef");
        }
        BufferedReader br=a.getReader();
        try{
            br.close();
        }catch(IOException err){
            throw new InterpretorException(" "+err);
        }
        p.getFileTable().delete(e);
        return p;
    }
    public String toString(){
		return " "+fileId;
    }
}
