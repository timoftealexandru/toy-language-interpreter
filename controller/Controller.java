package controller;
import model.*;
import model.stmt.Statement;
import repo.*;
import utils.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.concurrent.*;
import java.lang.*;
import java.util.concurrent.Callable;

public class Controller {
    private IRepository repo;
    public Controller(IRepository repo){
        this.repo=repo;
    }

    private ExecutorService executor;
    public List<PrgState> removeCompletedPrgState(List<PrgState> l){
        return l.stream()
                .filter(p->p.isNotCompleted())
                .collect(Collectors.toList());
    }

    public void oneStepForAll(List<PrgState> list){
        list.forEach(prg->this.repo.logPrgStateExec(prg));


        List<Callable<PrgState>> callables= list
                .stream()
                .map((PrgState prg)->(Callable<PrgState>) (()->prg.oneStep()))
                .collect(Collectors.toList());

        try {
            List<PrgState> newPrg = executor
                    .invokeAll(callables)
                    .stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (InterruptedException er) {
                            er.printStackTrace();
                        } catch (ExecutionException err) {
                            err.printStackTrace();
                        }
                        return null;
                    })
                    .filter(p -> p != null)
                    .collect(Collectors.toList());

            list.addAll(newPrg);
            repo.setPrgStates(list);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }

    }
    public void allStep(){
        executor = Executors.newFixedThreadPool(2);
        while(true){
            List<PrgState> list=removeCompletedPrgState(repo.getPrgStates());
            if(list.size()==0){
                break;
            }
            oneStepForAll(list);
        }
        executor.shutdownNow();
    }

    public void executeOneStep(PrgState state){
        IExeStack<Statement> stack=state.getExeStack();
        if(!stack.isEmpty()){
            Statement stmt=stack.pop();
            stmt.execute(state);
        }
    }
    /*
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
    */


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
