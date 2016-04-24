package DS;

import DS.LinkList;
import DS.Queue;

public class LinkQue<T> extends LinkList<T> implements Queue<T>{
    public LinkQue()
    {
    	super();
    }
    
    public boolean isFull()
    {
    	return false; // ���������;
    }
    
    public T getHead()
    {
    	return remove(0);
    }
}
