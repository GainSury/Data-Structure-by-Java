package DS;

class Pair<K,V>
{
	public K key;
	public V value;
    public Pair(K k1,V v1)
    {
    	key = k1;
    	value = v1;
    }
    
    public boolean equals(Object o)
    {
    	Pair<K,V> pair = (Pair<K,V>)o;
    	return key.equals(pair.key);
    }
    
    public int hashCode()
    {
    	return key.hashCode();
    }
    
    public String toString()
    {
    	return "("+ key.toString() + "," + value.toString() + ")";
    }
    
   
}

public class HashMap<K,V>
{
    HashTable<Pair<K,V>> ht;
    public HashMap(int len)
    {
    	ht = new HashTable<Pair<K,V>>(len);
    
    }
    
    public void add(K key,V value)
    {
    	ht.add(new Pair<K,V>(key,value));
    }
    
    public void put(K key,V value)
    {
    	ht.add(new Pair<K,V>(key,value));
    }
    
    public V get(K key)
    {
        Pair<K,V> pair = ht.search(new Pair<K,V>(key,null));
        if(pair == null)
        	return null;
        else return pair.value;
    }
    
    public boolean containsKey(K key)
    {
    	Pair<K,V> pair = ht.search(new Pair<K,V>(key,null));
        if(pair == null)
        	return false;
        else return true; 
    }
    
    public V remove(K key)
    {
    	Pair<K,V> pair = ht.remove(new Pair<K,V>(key,null));
    	if(pair == null)
    		return null;
    	else return pair.value;
    }
    
    public static void main(String[] args)
    {
    	HashMap<Integer,String> hm = new HashMap<Integer,String>(89);
    	hm.put(1, "beijing1");
    	hm.put(2, "beijing2");
    	hm.put(3, "beijing3");
    	hm.put(4, "beijing4");
    	hm.put(5, "beijing5");
    	hm.put(6, "beijing6");
    	hm.put(7, "beijing7");
    	hm.put(8, "beijing8");
      	hm.put(9, "beijing9");
      	
      	
      	System.out.println(hm.get(1));
      	System.out.println(hm.get(2));
      	System.out.println(hm.get(3));
      	System.out.println(hm.get(4));
      	System.out.println(hm.get(5));
      	System.out.println(hm.get(6));
      	System.out.println(hm.get(7));
      	System.out.println(hm.get(8));
      	System.out.println(hm.get(9));
    }
    
    
    
}


