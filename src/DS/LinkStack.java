package DS;
//链栈 先进先出
import DS.Stack;
import DS.LinkList;
public class LinkStack<T> extends LinkList<T> implements Stack<T>
{
	public LinkStack()
	{
		super();
	}
	
	public void push(T x)
	{
		addFront(x);
	}
	
	public T pop()
	{
		return remove(0);
	}
	
	public T getTop()
	{
		return get(0);
	}
  
}
