package DS;

public interface Queue<T>{
	boolean isEmpty();
	boolean isFull();
	T getHead();
	void add(T x);
	T remove();

}
