package DS;
//数学原理 模运算的封闭性
import DS.Queue;

public class SeqQue<T> implements Queue<T>{
    private int front, rear ,maxSize;
    Object[] data;
    public SeqQue(int maxQueueSize)
    {
       maxSize =  maxQueueSize + 1;
       front =  rear = 0;
       data = new Object[maxSize];
    }
    
    public boolean isEmpty()
    {
    	return (front == rear);
    }
    
    public void add(T e)
    {
    	if (!isFull())
    	{
    	    data[rear] = e;
    	    rear = (rear + 1)% maxSize;
       	}
    }
    
    public T remove()
    {
    	T x = (T)data[front];
    	front = (front + 1)% maxSize;
    	return x;
    }
    
    public T getHead()
    {
    	return (T)data[front];
    }

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return (rear + 1)% maxSize == front;
	}
}
