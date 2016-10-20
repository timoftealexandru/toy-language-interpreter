package utils;
import java.util.*;
public interface ISymbolTable<E,F> {
    public void add(E key, F value);
    public boolean contains(E key);
    public void setValue(E key,F value);
    public F getValue(E key);
}
