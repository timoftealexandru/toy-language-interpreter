package utils;
import java.util.*;
public class Output<E> implements IOutput<E> {
    private ArrayList<E> outs;
    public Output(){
        outs=new ArrayList<>();
    }
    public void add(E elem){
        outs.add(elem);
    }
    public int size(){
        return outs.size();
    }
    public String toString(){
        return outs.toString();
    }
}
