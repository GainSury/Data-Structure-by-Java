package DS;

import DS.aList;
import DS.AbsList;
import java.util.Arrays;
import java.util.Iterator;
import java.lang.StringBuilder;

public class SeqList<T> extends AbsList<T> implements aList<T>{
	private int incrementSize;
	protected T[] data;
	public boolean isEmpty(){
		return length == 0;
	}
	public SeqList(){this(16);}
	public SeqList(int capacity){
		if(capacity <= 0) capacity = 16;
		length = 0;
		incrementSize = 0;
		data = (T[]) new Object[capacity];
	}
	public SeqList(T[] elem){
		length = elem.length;
		incrementSize = 0;
		data = Arrays.copyOf(elem, length);
		}
	
	public void serInc(int inc){
		incrementSize = inc;
	}
	
	
	public void setCapacity(int newsize){
		data = Arrays.copyOf(data, newsize);
	}
	
	public int getCapacity(){return data.length;}
	public int size(){return length;}
	
	public T get(int i){
		if (i < 0|| i > length -1) return null;
		 return data[i];
	}
	
	public boolean set(int i,T x){
		if (i < 0|| i > length -1) return false;
		else {
			data[i] = x;
			return false;
		}
	}
	
		
	public int indexOf(int begin,int end,T o){
		if(o == null){
			for(int i = 0;i < length;i++)
				if(data[i] == null)
					return i;
		}
		else{
			for(int i = 0;i < length;i++)
				if(compare(o,(T)data[i] )== 0)
					return i;
		}
		return -1;
	}

	protected int compare(T a,T b){
		if (a instanceof Comparable && b instanceof Comparable)
			return ((Comparable) a).compareTo((Comparable) b);
		else
			return ((String) a).compareTo((String)b);
	}
	
	private void grow(){
		int newsize = data.length + incrementSize;
		if(incrementSize == 0)
			data = Arrays.copyOf(data, newsize * 2);
		else 
			data = Arrays.copyOf(data, newsize);
		
	}
	
	public boolean add(int i,T x){
		if (length == data.length) grow();
		if (i < 0 || i >= length)
			return false;
		for (int j = length-1;j>=1;j--)
			data[j+1]=data[j];
		data[i] = x;
		length ++ ;
		return true;
	}
	
	public void add(T t){
		if(length == data.length) grow();
		data[length]=t;
		length++;
	}
	
	public void append(T x){
		add(x);
	}
	
	public void addSort(T x){
		insertOrder(length,x);
		length++;
	}
	
	public void sort(){
		for(int i =1;i<=length-1;i++)
			insertOrder(i,(T)data[i]);
	}
	protected void insertOrder(int end,T x){
		if(length == data.length) grow();
		int k;
		for(k =end-1;k>=0;k--){
			if (compare(x,(T)data[k])<0)
				data[k+1] = data[k];
		}
		data[k+1]=x;
	}
	
	public T remove(int i){
		if(i<0 || i >length -1)
			throw new IndexOutOfBoundsException(" 下标越界 ，i=" + i);
		T olddata = (T)data[i];
		for(int j = i;j <length-1;j++)
			data[j] = data[j+1];
		data[--length] = null;
		return olddata;
  	}
	
	public T remove(T o){
		int i = indexOf(o);
		return remove(i);
	}
	
	public void clear(){
		for(int i=0;i<length;i++)
			data[i] = null;
		length = 0;
	}
	public String toString(){
		StringBuilder strb = new StringBuilder();
		strb = strb.append("(");
		for(int i=0;i<length-1;i++)
			strb = strb.append(data[i].toString() + ",");
		strb = strb.append(data[length-1]+")");
		String s = new String(strb);
		strb = null;
		return s;
	}
	public Object[] toArray(){
		return Arrays.copyOf(this.data, this.length);
	}
	
	public T[] toArray(T[] a){
		if(a.length < length)
			return (T[])Arrays.copyOf(this.data,this.length, a.getClass());
		System.arraycopy(this.data, 0, a, 0, this.length);
		if (a.length > this.length)
			a[length] = null;
		return a;
	}
	
	public Iterator<T> iterator(){
		return new MyIterator();
	}
	
	
	class MyIterator implements Iterator<T>{
		private int index = 0;
		
		@Override
		public boolean hasNext(){
			return index != length();
		}
		
		
		public T next1(){
			return get(index++);
		}
	    
		@Override
		public void remove(){
			 SeqList.this.remove(index - 1);
			 index--;
		}
		@Override
		public T next(){
			if(hasNext())
				return next1();
			else
				index = 0;
			    return next1();
		}
	}



}
