package repo;
import java.util.*;
import model.*;
public class Repository implements IRepository {
    public ArrayList<PrgState> repo;
    public Repository(){
        repo=new ArrayList<>();
    }
    public void addPrgState(PrgState p){
        repo.add(p);
    }
    public PrgState getCurrent(){
        return repo.get(0);
    }
}
