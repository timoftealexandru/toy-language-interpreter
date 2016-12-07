package repo;
import model.*;
import java.io.Serializable;
import java.util.*;

public interface IRepository extends Serializable {
    PrgState getCurrentProgramState();
    void logPrgStateExec(PrgState p);
    void setMain(PrgState main);
    public void addPrgState(PrgState p);
    public List<PrgState> getPrgStates();
    public void setPrgStates(List<PrgState> l);
    void serialize(PrgState p,String fName);
    PrgState deserialize(String fName);
}
