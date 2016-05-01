package DS;

import java.io.Serializable;
import java.lang.Comparable;
import java.util.Iterator;

import DS.aList;

import DS.Lnode;

public class LinkList<T> extends AbsList<T> implements Iterable<T>,Serializable{
    protected Lnode<T> first,last;
    LinkList<T>.LinkIterator itr = null;
    public LinkList(){
    	first=last=null;
    	length = 0;
    	this.itr = new LinkIterator(); //迭代器    	
    }
    private int compare(Lnode<T> a,Lnode<T> b){//比较用
    	return a.compareTo(b);
    }
    public void clear(){
    	first = last = null;
    	length = 0;
    }
    public void removeAll(){
    	clear();
    }
    
    
    protected Lnode<T> getNode(int i)
    {   
    	if(length == 0)
    		return null;
    	if (i<0||i>length-1)
    		return null;
    	if(i==0) return first;
    	Lnode<T> p = first;
    	int j=0;
    	while(p!= null && j<i){ //实际从1计数，逻辑从零计数
    		p=p.next;
    		j++;
    	}
    	return p;
    }
    
    public T get(int i)
    {
    	Lnode<T> p = getNode(i);
    	if(p==null) return null;
    	else 
    		return p.data;
    }
    public boolean set(int i,T x)
    {
    	Lnode<T> p = getNode(i);
    	if (p == null) return false;
    	else {
    		p.data=x;
    		return true;
    	}
    }
    public boolean add(int i,T x)
    {
    	Lnode<T> p,s;
    	int j = i-1;//逻辑位置从零计数，实现位置从1计数
    	s = new Lnode<T> (x,null);
    	if(first == null || length == 0)
    	{
    		first = s;
    		last = s;
    	}
    	else if(j < 0)
    	{
    		s.next = first;
    		first = s;
    	
    	}
    	else if( j >= length-1)
    	{
    		last.next = s;
    		last = s;
    	}
    	else
    	{
    		p = getNode(j);
    		s.next = p.next;
    		p.next = s;
    	}
    	length++;
		return false;
	
    }
    
    public void add(T key)//从尾部添加元素
    {
    	add(length,key);
    }
    
    public void addBack(T key)
    {
    	add(length,key);
    }
    public void addFront(T key)
    {
    	add(0,key);
    }
                                                                     
    
    protected Lnode<T> removeNode(int i)
    {
    	Lnode<T> p,q;
    	if (first==null)
    		return null;
    	if(i == 0)
    	{
    		p = first;
    		first = first.next;
    		length = length - 1;
    		return p;
    	}
    	if ( i >=0 && i <= length -1)
    	{
    		p = getNode(i-1);
    		q = p.next;
    		p.next= q.next;
    		if(q==last)
    			last=p;
    		length = length-1;
    		return q;
    		
    	}
    	return null;
    }
    
    public T remove(int i)
    {
    	Lnode<T> p =removeNode(i);
    	if(p!=null)
    		return p.data;
    	else 
    		return null;
    }
    
    public T remove()
    {
    	return removeNode(0).data;
    }
    
    public T removeFront()
    {
    	return removeNode(0).data;
    }
    
    public T removeBack()
    {
    	return removeNode(length - 1).data;
    }
    
    
    public int indexOf(int begin,int end,T key)
    {
    	Lnode<T> p = getNode(begin);
    	int i = begin;
    	while(p != null && i < end)
    	{
    		if(p.data.equals(key)) 
    			return i;
    		p = p.next;
    		i++;
    	}
    	return -1;
    	
    }
    public T search(T key)
    {
    	Lnode<T> p = getNode(0);
    	while(p != null)
    	{
    		if(p.data.equals(key))
    			return p.data;
    		p = p.next;
    	}
    	return null;
    }
    public boolean contains(T key)
    {
    	if (indexOf (key) == -1)
    		return false;
    	else return true;
    }
    public void sort()
    {
    	LinkList<T> sl = new LinkList<T>();
    	Lnode<T> p;
    	p = this.removeNode(0);
    	while(p!=null)
    	{
    		sl.insertOrder(p);
    		p = this.removeNode(0);
    	}
    	this.first = sl.first;
    	this.last = sl.last;
    	this.length = sl.length;
    }
    public void addSort(T x)
    {
    	Lnode<T> s = new Lnode<T>(x,null);
    	insertOrder(s);
    }
    public void insertOrder(Lnode<T> s)
    {
    	Lnode<T> p1,p2;
    	length = length +1;
    	if (first == null)
    	{
    		first = s;
    		last = first;
    		last.next = null;
    		return;
    	}
    	if(compare(s,first) < 0)
    	{
    		s.next = first;
    		first = s;
    		return;
    	}
    	if (compare(s,last)>=0)
    	{
    		last.next = s;
    		last = s;
    		last.next = null;
    		return ;
    	}
    	p2 = first;
    	p1 = p2;
    	while(p2!= null)
    	{
    		if(compare(s,p2) > 0)
    		{
    			p1 = p2;
    			p2 = p2.next;
    		}
    		else break;
    	}
    	s.next = p2;
    	p1.next = s;
    	return;
    }

   
    public String toString()
    {
    	String s;
    	Lnode<T> p;
    	p = first;
    	
    	s = "(";
        while(p != null)
        {
        	s += p.data.toString() +",";
        	p = p.next;
        }
        if (!s.equals("("))
            s = s.substring(0, s.length()-1);
        s = s + ")\n";
        
        return s;
    }
    public Object[] toArray(){
    	Object[] a = new Object[length];
    	Lnode<T> p = first;
    	for (int i = 0; i < length; i++)
    	{
    		a[i] = p.data;
    		p = p.next;
    	}
    	return a;
    }
    public <E> E[] toArray(E[] a)
    {
    	if (a.length < this.length)
    		a = (E[])java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), this.length);
    	
    	Object[] result = a;
    	Lnode<T> x = this.first;
    	for(int i = 0; i < this.length; i++)
    	{
    		result[i] = x.data;
    		x = x.next;
    	}
    	if (a.length > this.length)
    		a[this.length] = null;
    	return a;
    }
    
    public Iterator<T> iterator()
    {
    	this.itr =  new LinkIterator();
    	return  this.itr;
    }
    private class LinkIterator implements Iterator<T>{
    	private int index = 0;
    	private Lnode<T> current = first;
    	@Override
    	public boolean hasNext()
    	{
    		return (index != length()&& current != null); 
    	}
    	@Override
    	public T next()
    	{
    		T temp = current.data;
    		current = current.next;
    		index++;
    		return temp;
    	}
    	
    	@Override
    	public void remove()
    	{
    		
    	}
    }
	
	
	
    
}