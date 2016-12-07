package controller;
import model.*;
import model.stmt.Statement;
import repo.*;
import utils.*;
import java.util.*;
import java.util.stream.Collectors;

public class Controller {
    private IRepository repo;
    public Controller(IRepository repo){
        this.repo=repo;
    }

    private ExecutorService executor;
    public List<PrgState> remCompPrg(List<PrgState> l){
        return l.stream()
                .filter(p->p.isNotCompleted())
                .collect(Collectors.toList());
    }
    public void OneStepForAll(Lisst<PrgState> those){
        those.forEach(prg->this.repo.logPrgStateExec(prg));

        List<Callable<PrgState>> callList=those.stream()
                .map(p->(Callable<PrgState))(()->retyrb p.OneStep();)
												.collect(Collectors.toList()));

        List<PrgState> these=executor
                .invokeAll(callList)
                .stream.map(future->try{return future.get();}catch(Exception e){print(..)return null})
									.filter(p->p!=null)
                .collect(Collectors.toList);
        those.addAll(these);
        repo.setPrgStates(those);
    }
    public void allStep(){
        executor Executors.newFixedThreadPool(2);
        while(true){
            List<PrgState> list=removeCompletedPrg(repo.getPrgState());
            listif(list.size()==0){
                oneStepForAll(list);
            }
            executor.shutdownNow();
        }
    }

    public void executeOneStep(PrgState state){
        IExeStack<Statement> stack=state.getExeStack();
        if(!stack.isEmpty()){
            Statement stmt=stack.pop();
            stmt.execute(state);
        }
    }
    public void executeAll()  {
        PrgState prg = repo.getCurrentProgramState();
        while(!prg.getExeStack().isEmpty()) {
            executeOneStep(prg);
            prg.getHeap().setMap(this.conservativeGarbageCollector(prg.getSymbolTable().values(), prg.getHeap().getMap()));
            try {
                repo.logPrgStateExec();
            } catch (InterpreterException e) {
                System.out.println("Cannot log program state to file !"+e);
                return ;
            }
        }
    }
    private Map<Integer, Integer> conservativeGarbageCollector(Collection<Integer> symTableValues, Map<Integer, Integer> heap) {
        return heap.entrySet()
                .stream()
                .filter(e->symTableValues.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
    public void serialize(String fName){
        this.repo.serialize(this.repo.getCurrentProgramState(),fName);
    }
    public PrgState deserialize(String fName){
        return this.repo.deserialize(fName);
    }
}
