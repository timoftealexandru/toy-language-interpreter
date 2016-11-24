package model.stmt;

import model.PrgState;

public interface Statement {
    public PrgState execute(PrgState p);
}
