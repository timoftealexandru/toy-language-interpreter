package controller;
import model.*;
import repo.*;
import utils.*;

public class Controller {
    private IRepository repo;
    public Controller(IRepository repo){
        this.repo=repo;
    }
    public void executeOneStep(PrgState state){
        IExeStack<Statement> stack=state.getExeStack();
        if(!stack.isEmpty()){
            Statement stmt=stack.pop();
            stmt.execute(state);
        }
    }
    public void executeAll(){
        PrgState currentState=repo.getCurrent();
        IExeStack<Statement> stack=currentState.getExeStack();
        while(!stack.isEmpty()){
            executeOneStep(currentState);

        }
        System.out.println(currentState);
    }
}
