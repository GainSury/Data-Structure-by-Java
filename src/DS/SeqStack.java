package DS;

import DS.SeqList;

public final class SeqStack<T>
{
    private SeqList<T> slist;
    public SeqStack(int num)
    {
    	slist = new SeqList<T>(num);
    }
    
    public SeqStack()
    {
    	slist = new SeqList<T>();
    }
    
    public boolean isEmpty()
    {
    	return slist.isEmpty();
    }
    
    public void push(T e)
    {
    	slist.add(e);
    }
    
    public T pop()
    {
    	return slist.remove(slist.length() - 1);
    }
    
    public String toString()
    {
    	return slist.toString();
    }
    
    public T[] toArray(T a[])
    {
    	return slist.toArray(a);
    }
}
