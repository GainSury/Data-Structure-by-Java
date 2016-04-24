package DS;

import java.lang.Iterable;
import java.util.Iterator;

import DS.aList;


public abstract class AbsList<T> implements Iterable<T>,aList<T>{
    protected int length;
    abstract public T get(int i);
    abstract public boolean set(int i,T x);
    abstract public int indexOf(int begin,int end,T o);
    abstract public boolean add(int i,T x);
    abstract public void clear();
    abstract public T remove(int i);
    abstract public Iterator<T> iterator();
    
    public boolean isEmpty(){return length == 0;}
    public int length(){return length;}
    public void add(T x){add(length,x);}
    public void append(T x){add(length,x);}
    public int indexOf(T o){
    	return indexOf(0,length,o);
    }
    
    public int indexOf(int begin,T o){
    	return indexOf(begin,length,o);
    }
    public T remove(T o){
    	return remove(indexOf(o));
    }
}
