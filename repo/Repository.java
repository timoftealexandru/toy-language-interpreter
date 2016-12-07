package repo;
import java.io.PrintWriter;
import java.util.*;
import model.stmt.Statement;
import utils.*;
import model.*;
import java.io.*;
import java.lang.Object;
import java.io.InputStream;

public class Repository implements IRepository {
    private String fileName;
    private PrgState programState;
    public List<PrgState> prgStates;

    public Repository(String fn){
        fileName=fn;
        this.prgStates=new ArrayList<>();
    }

    @Override
    public void addPrgState(PrgState prg){
        prgStates.add(prg);
    }

    @Override
    public List<PrgState> getPrgStates(){
        return prgStates;
    }

    @Override
    public void setPrgStates(List<PrgState> newp){
        prgStates=newp;
    }

    @Override
    public PrgState getCurrentProgramState(){
        if (programState == null) throw new RuntimeException("error: no program to execute");
        return programState;
    }

    @Override
    public void setMain(PrgState state) {
        this.programState = state;
    }

    @Override
    public void logPrgStateExec(PrgState prgState) {
        try (PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)))) {
            logFile.append(prgState.getExeStack().toString());
            logFile.append("\n");
            logFile.append(prgState.getSymbolTable().toString());
            logFile.append("\n");
            logFile.append(prgState.getOutput().toString());
            logFile.append("\n");
            logFile.append(prgState.getHeap().toString());
            logFile.append("\n");
            logFile.close();
        } catch (IOException error) {
            throw new RuntimeException("error: could not write to the given file");
        }

    }

    @Override
    public PrgState deserialize(String fName){
        try(ObjectInputStream str= new ObjectInputStream (new FileInputStream(fName)))
        {
            Object o =str.readObject();
            if(o instanceof PrgState){
                return (PrgState) o;
            }
            throw(new InterpreterException("Eroare la deserializare "));
        }
		catch(Exception e1){
            throw new InterpreterException("Eroare: "+e1);
        }
    }

    @Override
    public void serialize(PrgState ps,String fName){
        try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(fName))){
			oos.writeObject(ps);
        }
		catch(Exception e){
            throw new InterpreterException("Eroare: "+e);
        }
    }

}
