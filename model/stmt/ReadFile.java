package model.stmt;
import model.PrgState;
import utils.*;
import java.lang.*;
import java.io.*;

public class ReadFile implements Statement {
        private String fileId,name;
        public ReadFile(String f, String n){
            fileId=f;
            name=n;
        }
        public PrgState execute(PrgState p){
            Integer e=p.getSymbolTable().getValue(fileId);
            if(e==null){
                throw new InterpretorException("The fileId does not exist" );
            }
            FileData d=p.getFileTable().getValue(e);
            if(d==null){
                throw new InterpretorException(" error : ");
            }
            BufferedReader br=d.getReader();
            try{
                String s=br.readLine();
                int v;
                if(s==null){
                    v=0;
                }
                else{
                    v=Integer.parseInt(s);
                }
                p.getSymbolTable().add(name,v);
            }catch(IOException|NumberFormatException err ){
                throw new InterpretorException (" "+err);
            }
            return p;
        }
        public String toString(){
		    return ""+fileId+" "+ name;
        }


}
