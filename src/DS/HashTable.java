package DS;

import javax.net.ssl.HandshakeCompletedEvent;

import DS.LinkList;

public class HashTable<T>
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
	
	public HashTable(int len)
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
	
	public void add(T key)
	{
		int ha = hashCode(key);
		table[ha].add(key);
	}
	
	public T remove(T key)
	{
		int ha=hashCode(key);
		return table[ha].remove(key);
	}
	
	public T search(T key)
	{
		int ha = hashCode(key);
		return table[ha].search(key);
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
	
//    public static void main(String[] args)
//    {
////    	for(int i =2; i<1000;i++)  //test isPrime()
////    		if(isPrime(i))
////    			System.out.print(i + " ");
//    	
//    	
//    	
//    }
}
