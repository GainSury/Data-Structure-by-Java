package DS;

import java.io.Serializable;

public class Lnode<T> implements Comparable<Lnode<T>>,Serializable{
	public T data;
	public Lnode<T> next;
	public Lnode(T key){
		data = key;
		next = null;
	}
	public Lnode(T key,Lnode<T> next){ //定义数据域
		data = key;
		this.next = next;
	}
	public boolean equals(Object e){
		Lnode<T> node = (Lnode<T>) e;
		return data.equals(node.data);
	}
	
	public int compareTo(Lnode<T> e){
		Comparable<T> x = null;
		if(data instanceof Comparable){
			x = (Comparable<T>)data;
			return (int)x.compareTo(e.data);
		}
		else throw new ClassCastException("this type is uncomparable");
			
	}
	public String toString(){
		return data.toString();
	}
}
