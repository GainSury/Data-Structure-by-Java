package exercise;

import java.util.Iterator;

import DS.SeqList;

public class JosephCircle {
    
	public static <T> SeqList<T> outArray(SeqList a,int k,int m)
	{
		int num = a.getCapacity();
		SeqList<T> result = new SeqList<T>();
		//
		Iterator<T> it1 = a.iterator();
		for(int i = 0;i < k - 1;i++) //迭代器指向k
			it1.next();
		while(true){
		for(int i = 0;i < m - 1;i++) //开始数数
			it1.next();
		if (a.isEmpty())
		    break;
		result.add(it1.next());
			
		it1.remove();
		}
		return result;
	}
	public static void main(String[] args)
    {
       SeqList<Integer> test = new SeqList<Integer>();
       test.add(1);
       test.add(4);
       test.add(8);
       test.add(155);
       test.add(2);
       test.add(7);
       test.add(126);
       test.add(415);
       test.add(110);
       for(Integer x:test)
    	   System.out.print(x + " ");
       SeqList<Integer> result = outArray(test,2,3);
       System.out.println();
       for(Integer x:result)
    	   System.out.print(x + " ");
//       System.out.println();
//       int i = 100,x= 100;
//       Iterator<Integer> it1 = test.iterator();
//       while (i != 0){
//    	   x = it1.next();
//    	   System.out.print(x + " ");
//    	   if (i <= 99)
//    		   it1.remove();
//    	   if(test.isEmpty())
//    		   break;
//    	   i--;
//       }
       
       
    }
}
