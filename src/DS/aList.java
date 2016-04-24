package DS;



public interface aList<T>{
	boolean isEmpty();
	int length();
	T get(int i);
	boolean set(int i,T x);
	boolean add(int i,T x);
	T remove(int i);
	int indexOf(T x);
}