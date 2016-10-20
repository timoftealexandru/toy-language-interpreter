package model;
import utils.*;

public class ConstExp implements Expression{
    private int value;
    public ConstExp(int value){
        this.value=value;
    }
    public int evaluate(ISymbolTable s){
        return value;
    }
    public String toString(){
        return ""+value;
    }
}
