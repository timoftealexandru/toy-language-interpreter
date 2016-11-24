package repo;
import model.*;
public interface IRepository {
    void addPrgState(PrgState p);
    PrgState getCurrent();
    void logPrgStateExec();
}
