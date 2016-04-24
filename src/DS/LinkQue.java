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
    	return false; // 链表可增长;
    }
    
    public T getHead()
    {
    	return remove(0);
    }
}
