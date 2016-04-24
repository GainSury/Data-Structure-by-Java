package exercise;

import DS.LinkList;
import DS.Lnode;

import java.lang.Comparable;

//class PolyNode implements Comparable{
//	public float coef;
//	public int exp;
//	public PolyNode next;
//	public PolyNode(float c,int e){
//		this.coef = c;
//		this.exp = e;
//		next = null;
//	}
//	public PolyNode(float c,int e,PolyNode next){ //定义数据域
//		this.coef = c;
//		this.exp = e;
//		this.next = next;
//	}
//	public boolean equals (Object e){
//		PolyNode node = (PolyNode) e;
//		return (this.coef == node.coef) && (this.exp == node.exp);
//	}
//	
//	
//	@Override
//	public int compareTo(Object o) {
//		PolyNode node = (PolyNode) o;
//		Comparable x = null;		
//	    return (int)x.compareTo(node.exp);
//	}
//	
//}
//
//class Polyomialy {
//	PolyNode first,last;
//	int length;
//	public Polyomialy(){
//		first = last = null;
//		length = 0;
//	}
//	
//}

class PolyItem implements Comparable<PolyItem>
{
	protected float coef;
	protected int exp;
	public PolyItem(){
		coef = exp = 0;
	}
	
	public PolyItem(float c,int e){
		 this.coef = c;
		 this.exp = e;
	}
	public String toString(){
		String result = new String();
		result = coef +", "+exp;
		return result;
	}

	@Override
	public int compareTo(PolyItem o) {
		// TODO Auto-generated method stub
		if (exp > o.exp)
			return 1;
		else if (exp == o.exp)
			return 0;
		else 
			return -1;
	}
}
	

class Polyomialy extends LinkList<PolyItem>
{

	Polyomialy(){
		super();
	}
   
    public void addP(float c,int a)
    {   int index = indexOf2(0,length,a);
    	if (index == -1)
    	    add(length,new PolyItem(c,a));
    	else{ 
    	Lnode<PolyItem> p = getNode(index);
    	p.data.coef += c;
    	}
    	this.sort();
    }
    
    public int indexOf2(int begin,int end,int exp)
    {
    	Lnode<PolyItem> p = first;
    	int i = begin;
    	while(p != null && i < end)
    	{
    		if(p.data.exp == exp) 
    			return i;
    		p = p.next;
    		i++;
    	}
    	return -1;
    	
    }
    
    public String toString()
    {
    	String a = new String();
    	Lnode<PolyItem> p;
    	if(first == null)
    		return null;
    	p = first;
    	while(p != null){
    	    if (p.next != null)
    		    a += "("+p.toString() +")"+ " ";
    	    else
    	    	a += "("+p.toString() +")";
    	    p = p.next;
    	}
    	a +="\n";
    	return a;
    }
    
    @Override
	public void sort()
    {
		Polyomialy sl = new Polyomialy();
		Lnode<PolyItem> p;
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
     
    public Polyomialy Add(Polyomialy node)
    {
    	Lnode<PolyItem> t1 = first;
    	Lnode<PolyItem> t2 = node.first;
    	float sum;
    	Polyomialy rear = new Polyomialy();
    	while(t1 == null && t2 == null)
    	{
    		if(t1.data.exp == t2.data.exp)
    		{
    			sum = t1.data.coef + t2.data.coef;
    			if(sum != 0)
    				rear.addP(sum, t1.data.exp);
    			t1 = t1.next;
    			t2 = t2.next;
    		}
    		else if (t1.data.exp > t2.data.exp)
    		{
    			rear.addP(t1.data.coef, t1.data.exp);
    			t1 = t1.next;
    		}
    		else if (t1.data.exp < t2.data.exp)
    		{
    			rear.addP(t2.data.coef, t2.data.exp);
    			t2 = t2.next;
    		}
    		
    	}
    	
    	while (t1 != null)
    	{
    		rear.addP(t1.data.coef, t1.data.exp);
    		t1 = t1.next;
    	}
    	
    	while (t2 != null)
    	{
    		rear.addP(t2.data.coef, t2.data.exp);
    		t2 = t2.next;
    	}
    	rear.last.next = null;
    	return rear;
    }
    
	
}

public class Polyomial {
	public static void main(String[] args)
    {
    	Polyomialy test = new Polyomialy();
    	Polyomialy test1 = new Polyomialy();
        test.addP(2, 1);
        test.addP(1,8);
        test.addP(5,8);
        test.addP(3,7);
        test1.addP(3,7);
        test1.addP(1,4);
        test1.addP(3,127);
        test1.addP(3,18);
        
        System.out.print(test.Add(test1));
    }
}
