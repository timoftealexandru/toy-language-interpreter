package utils;
import java.util.*;
public class ExeStack<E> implements IExeStack<E> {
    private Stack<E> stack;
    public ExeStack(){
        stack=new Stack<E>();
    }
    public void push(E elem){
        stack.push(elem);
    }
    public E pop(){
        return stack.pop();

    }
    public boolean isEmpty(){
        return stack.empty();
    }
    public E top(){
        return stack.peek();
    }
    public String toString(){
        return stack.toString();
    }
}
