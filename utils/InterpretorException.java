package utils;

public class InterpretorException extends RuntimeException {
    public InterpretorException(String s){
        super(s);
    }
    public InterpretorException(String s,Throwable t){
       super(s,t);
    }
}
