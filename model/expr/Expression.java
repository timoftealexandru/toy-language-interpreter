package model.expr;
import utils.*;
public interface Expression{
    public int evaluate(ISymbolTable<String,Integer> s);
}
