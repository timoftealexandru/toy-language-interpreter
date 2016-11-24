package repo;
import java.io.PrintWriter;
import java.util.*;

import model.stmt.Statement;
import utils.*;
import model.*;
import java.io.*;

public class Repository implements IRepository {
    private String fileName;
    private PrgState programState;

    public Repository(String fn){
        this.fileName=fn;
    }
    public void addPrgState(PrgState p){
        this.programState=p;
    }
    public PrgState getCurrent(){
        return programState;
    }

    public PrgState getCurrentProgramState() {
        if (programState == null) throw new RuntimeException("error: no program to execute");
        return programState;
    }
    @Override
    public void logPrgStateExec() {
        try (PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)))) {
            logFile.append("Execution Stack\n");
            for (Statement statement : programState.getExeStack().getAll()) {
                logFile.append(statement + "\n");
            }
            logFile.append("\n");

            logFile.append("Symbol Table\n");
            for (Map.Entry<String, Integer> entry : programState.getSymbolTable().getAll()) {
                logFile.append(entry.getKey() + " --> " + entry.getValue() + "\n");
            }
            logFile.append("\n");

            logFile.append("Output\n");
            for (Integer output : programState.getOutput().getAll()) {
                logFile.append(output + "\n");
            }
            logFile.append("\n");
        } catch (IOException error) {
            throw new RuntimeException("error: could not write to the given file");
        }
    }
}
