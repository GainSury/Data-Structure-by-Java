package DS;



public class treeMapBySeatchTree<T extends Comparable<T>,V>{
	private B2STree<T> keys;
	HashMap<T,V> maps;
	
	
	//默认哈希表的大小是1000
	//构造函数
	treeMapBySeatchTree()
	{
		keys = new B2STree<T>();
		maps = new HashMap<T,V>(1000);
	}
	
	treeMapBySeatchTree(int num)
	{
		keys = new B2STree<T>();
		maps = new HashMap<T,V>(num);
	}
	
	treeMapBySeatchTree(treeMapBySeatchTree o)
	{
		this.keys = o.keys;
		this.maps = o.maps;
	}
	
	public int size()
	{
		return keys.size();
	}
	
	public boolean isEmpty()
	{
		return this.size() == 0;
	}
	
	public T firstKey()
    {
        return keys.min();	
    }
    
	public T lastKey()
    {
        return keys.max();	
    }
    
    public void add(T key,V value){
    	keys.add(key);
    	maps.add(key, value);
    }
    
    public V remove(T key)
    {
    	keys.remove(key);
    	return maps.remove(key);
    }
    
    public boolean containsKey(T key)
    {
    	return maps.containsKey(key);
    }
    
    public V get(T key)
    {
    	return maps.get(key);
    }

    
    public static void main(String[] args)
    {
    	treeMapBySeatchTree<Integer,String> test = new treeMapBySeatchTree<Integer,String>();
    	
    	test.add(2, "beijing2");
    	test.add(3, "beijing3");
    	test.add(4, "beijing4");
    	test.add(5, "beijing5");
    	test.add(6, "beijing6");
    	test.add(1, "beijing1");
    	test.add(7, "beijing7");
    	test.add(8, "beijing8");
      	test.add(9, "beijing9");
      	
      	
      	System.out.println(test.get(1));
      	System.out.println(test.get(2));
      	System.out.println(test.get(3));
      	System.out.println(test.get(4));
      	System.out.println(test.get(5));
      	System.out.println(test.get(6));
      	System.out.println(test.get(7));
      	System.out.println(test.get(8));
      	System.out.println(test.get(9));
      	System.out.println(test.lastKey());
      	System.out.println(test.firstKey());
    }
}