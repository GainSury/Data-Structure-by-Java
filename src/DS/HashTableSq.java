package DS;

import javax.net.ssl.HandshakeCompletedEvent;

import DS.LinkList;

public class HashTableSq<T>
{
	private LinkList<T> table[];
	
	public static boolean isPrime(int n)
	{
		
		if (n < 2)
			return false;
		if (n == 2)
			return true;
		if (n % 2 == 0)
			return false;
		int m = (int)Math.sqrt(n);
		for(int i = 2;i <= m; i++)
		{
			if(n % i == 0)
				return false;
		}
		return true;
	}
	
	public HashTableSq(int len)
	{
		int np;
		if(HashTable.isPrime(len))
			np = len;
		else
		{
			if(len%2 == 0)
				if(len % 2 == 0)
					len = len +1;
			for(np =len;;np++)
				if(isPrime(np))
					break;
		}
		table = new LinkList[np];
		for(int i = 0;i < table.length;i++)
		{
			table[i] = new LinkList<T>();
		}
	}
	
	public int hashCode(T key)
	{
		int hc = Math.abs(key.hashCode());
		return hc%table.length;
	}
	private int hashCodei(T key) //用来处理平方探测法
	{
		int hc = Math.abs(hashCode(key));
		hc = hc%table.length;
		if(this.searchi(key)!=null) //保证k唯一
			return -1;
		int i =1;
		while(!
				(table[hc].first == null))
		{
//			System.out.println(table[hc].equals(
//						new LinkList<T>()));
			hc += i*i;
			hc = hc%table.length;
		    i++;
		}
		return hc%table.length;
	}
	
	public void add(T key)
	{
		
		int ha = hashCodei(key);
		if(ha == -1)
			return ;
		table[ha].add(key);
	}
	
	public T remove(T key)
	{
		int ha=hashCodei(key);
		return table[ha].remove(key);
	}
	
	private T searchi(T key)
	{
		int ha = hashCode(key);
		return table[ha].search(key);
	}
	
	public T search(T key)//先查询是否为null，然后再查询key是否是要查找的key
	{
		int hc = Math.abs(hashCode(key));
		hc = hc%table.length;
		int i = 1;
		while(table[hc].first != null)
		{
			if (table[hc].first.data.equals(key))
				return table[hc].search(key);
			else 
			{
				hc += i*i;
				hc = hc%table.length;
			    i++;
			}
				
		}
		return null;//检查到null，直接终止
	}
	public String toString()
	{
		String str = "\n";
		for(int i = 0;i<table.length ;i++)
		{
			str = str +i +"|->" + table[i].toString()+"\n";
		}
		return str;
	}
	
    public static void main(String[] args)
    {
//    	for(int i =2; i<1000;i++)  //test isPrime()
//    		if(isPrime(i))
//    			System.out.print(i + " ");
    	
    	HashTableSq<Integer> test = new HashTableSq<Integer>(11);
   	 	for (int i = 0; i < 9;i++)
   	 	{
   	 		test.add(i);
   	 	}
    	System.out.println(test);
    	for (int i = 0; i < 15;i++)
   	 	{
    		System.out.println(test.search(i));
   	 	}
    	
    	 
    	
    }
}
