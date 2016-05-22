package DS;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class RadixSortParellel implements Sort{
	private static int radix;
	private Integer[] p;
	private double[] rp;
	
	public RadixSortParellel(int radix_){
		radix = radix_;
		p = new Integer[8];
		rp = new double[8];
		p[0] = 1;
		rp[0] = 1;
		for(int i = 1;i < 8;i++)
			p[i] = p[i-1]*radix;
		for(int i = 1;i < 8;i++)
			rp[i] = 1.0/p[i];
	}
	
	public final int get_part(int n,int i)  
	{  
	    
    	return (int)(n*rp[i])%radix; 
//    	return (int)(n /p[i])%radix;
//		return n>>p[i]&(radix-1);
	}
	
	private static int id = 0;
	private static int id2 = 0;
	class mergeThread extends Thread {
		Integer[] array;
		int left;
		int right;
		int center;
	    mergeThread(Integer[] array_,int left_,int center_,int right_) {
		   array = array_;
		   left = left_;
		   center = center_;
		   right = right_;
		   start(); // ��ʼ�߳�
	    }
	   // �߳����
	   public void run() 
	   {
		   merge(array,left,center,right);
	   }
		   
	}
	class sortThread extends Thread {
	   Integer[] array;
	   int offset;
	   sortThread(Integer[] array_,int offset_) {
	      array = array_;
	      offset = offset_;
		  start(); // ��ʼ�߳�
	   }
	 
	   // �߳����
	   public void run() 
	   {
		   RadixSort2(array,offset,array.length/8);
	   }
	}
	private int computeOffset(int threadIndex,int DataNum,int threadNum)
	{
		int blockLen=DataNum/threadNum;
		int offset=threadIndex*blockLen;

//		System.out.println(offset);
		return offset;
		
		
	}

//	private final static int RADIX = 10;
//	private final static int KEYNUM = 10;
	public void sort(Integer[] array)
	{
//		Integer[] result = new Integer[array.length];
		for(int i = 0; i < 8;i++)
		    new sortThread(array,computeOffset(i, array.length, 8));

			try {
				
				
				while(id != 8)
					
					Thread.sleep(20);
				  
				
				new mergeThread(array,
							   computeOffset(0, array.length, 8),
							   computeOffset(1, array.length, 8)-1,
							   computeOffset(2, array.length, 8)-1);

				
				new mergeThread(array,
							  computeOffset(2, array.length, 8),
							  computeOffset(3, array.length, 8)-1,
							  computeOffset(4, array.length, 8)-1);

				
				new mergeThread(array,
						  computeOffset(4, array.length, 8),
						  computeOffset(5, array.length, 8)-1,
						  computeOffset(6, array.length, 8)-1);
				
				new mergeThread(array,
						  computeOffset(6, array.length, 8),
						  computeOffset(7, array.length, 8)-1,
						  computeOffset(8, array.length, 8)-1);
                while(id2 != 4)
					Thread.sleep(20);
                
                id2 = 0;
//				Integer[] copy1 = new Integer[computeOffset(2, array.length, 8)];
//				for(int i = 0;i < computeOffset(2, array.length, 8);i++)
//					copy1[i] = array[i+computeOffset(6, array.length, 8)];
//				System.out.println(testSort.testSort(copy1));
				
				//merge 2
//                long start = System.currentTimeMillis(); 
                new mergeThread(array,
						  computeOffset(0, array.length, 8),
						  computeOffset(2, array.length, 8)-1,
						  computeOffset(4, array.length, 8)-1);
				
                new mergeThread(array,
						  computeOffset(4, array.length, 8),
						  computeOffset(6, array.length, 8)-1,
						  computeOffset(8, array.length, 8)-1);
                while(id2 != 2)
					Thread.sleep(20);
                id2 = 0;
//                long end = System.currentTimeMillis();
//                System.out.println(end - start);
				//merge1
				merge(array,
						  computeOffset(0, array.length, 8),
						  computeOffset(4, array.length, 8)-1,
						  computeOffset(8, array.length, 8)-1);
//				mergeBlocks(array, array.length, 8, result);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
    /** 
     * ������������й鲢���鲢ǰ��2�����������򣬹鲢����Ȼ���� 
     *  
     * @param data 
     *            ������� 
     * @param left 
     *            ������ĵ�һ��Ԫ�ص����� 
     * @param center 
     *            ����������һ��Ԫ�ص�������center+1���������һ��Ԫ�ص����� 
     * @param right 
     *            ���������һ��Ԫ�ص����� 
     *            center + 1 =offset
     *            ���� offset = center -1;
     *            
     */  
    public static void merge(Integer[] data, int left, int center, int right) {  
        // ��ʱ����  
    	Integer[] tmpArr = new Integer[data.length];  
        // �������һ��Ԫ������  
    	int mid = center + 1;  
        // third ��¼��ʱ���������  
    	int third = left;  
        // �����������һ��Ԫ�ص�����  
        int tmp = left;  
        while (left <= center && mid <= right) {  
            // ������������ȡ����С�ķ�����ʱ����  
            if (data[left] <= data[mid]) {  
                tmpArr[third++] = data[left++];  
            } else {  
                tmpArr[third++] = data[mid++];  
            }  
        }  
        // ʣ�ಿ�����η�����ʱ���飨ʵ��������whileֻ��ִ������һ����  
        while (mid <= right) {  
            tmpArr[third++] = data[mid++];  
        }  
        while (left <= center) {  
            tmpArr[third++] = data[left++];  
        }  
        // ����ʱ�����е����ݿ�����ԭ������  
        // ��ԭleft-right��Χ�����ݱ����ƻ�ԭ���飩  
        while (tmp <= right) {  
            data[tmp] = tmpArr[tmp++];  
        }  
        id2++;
    }  
  
	
	private void mergeBlocks(Integer[] pDataArray, int arrayLen, int blockNum, Integer[] resultArray) {
		// TODO Auto-generated method stub
		int blockLen=arrayLen/blockNum;
	    Integer[] blockIndex = new Integer[blockNum];//��������Ԫ���������е��±꣬VC���ܲ�֧�ֱ�����Ϊ����ĳ��ȣ�����취��ʹ�ú궨��
	    for(int i=0;i<blockNum;++i)//��ʼ������Ԫ����ʼ�±�
	    {
	        blockIndex[i]=i*blockLen;
	    }
	    int smallest=0;
	    for(int i=0;i<arrayLen;++i)//ɨ�����п��ڵ�����Ԫ��
	    {  
	      for(int j=0;j<blockNum;++j)//�Ե�һ��δɨ����Ŀ���Ԫ����Ϊ��С��
	      {
	       if(blockIndex[j]<(j*blockLen+blockLen))
	       {
	        smallest=pDataArray[blockIndex[j]];
	        break;
	       }
	      }
	      for(int j=0;j<blockNum;++j)//ɨ������飬Ѱ����С��
	      {
	        if((blockIndex[j]<(j*blockLen+blockLen))&&(pDataArray[blockIndex[j]]<smallest))
	        {
	          smallest=pDataArray[blockIndex[j]];
	        }
	      }
	      for(int j=0;j<blockNum;++j)//ȷ���ĸ�����Ԫ���±��������
	      {
	        if((blockIndex[j]<(j*blockLen+blockLen))&&(pDataArray[blockIndex[j]]==smallest))
	        {
	          ++blockIndex[j];
	          break;
	        }
	      }
	      resultArray[i]=smallest;//����ѭ����С������������
	    }
	    
	    //��������
	    for(int i = 0; i < arrayLen;i++)
	    	pDataArray[i] = resultArray[i];
	}

	
	public void RadixSort2(Integer[] array,int offset,int dataNum)  
	{  
		Integer[] bucket= new Integer[dataNum];
		Integer[] count = new Integer[radix];
		int T=array.length,fornum=0;
		
		while(T >= radix)
		{
			T /= radix;
			fornum++;
		}
		fornum++;
//		if (radix * radix <= n && n < radix * radix *radix) T = 3; 
//		else if ( radix <= n && n < radix * radix )
//			T = 2;
//			
//		else T = 1;
		
	  
	    for(int i=0;i < fornum;++i)  
	    {  
//		    	System.out.println("array is "+ Arrays.toString(array));
	        for(int s = 0;s < radix;s++)
	        	count[s] = 0;
//	    	
//		    Arrays.fill(bucket, 0);
	        for(int j=0+offset;j<dataNum+offset;++j)  
	        {  
	            count[get_part(array[j],i)]++;  
	        }  
	  
	        for(int j=1;j<radix;++j)  
	        {  
	            count[j]+=count[j-1];  
	        }  
	  
	       
	        for(int j=dataNum+offset-1;j>=0+offset;--j)  
	        {  
	            int k =get_part(array[j],i);   
	            bucket[count[k]-1]=array[j]; 
	            count[k]--;  
	        }  
//		        System.out.println("bucket is "+ Arrays.toString(bucket));

	        for(int s = 0+offset;s  <dataNum+offset ;s++)
	            array[s] = bucket[s-offset];
	    }
	    id++;//��ɷֿ�����id++ ����id == 8 �ϲ�����
	}
//	public void RadixSort(Integer[] unorderArray,int offset,int dataNum)  
//	{  
//		Integer[][] radixArrays = new Integer[RADIX][];    //�ֱ�Ϊ0~9�����Ĵ�ſռ�  
//	    for (int i=0; i<10; i++)  
//	    {  
//	        radixArrays[i] = new Integer[dataNum + 1];  
//	        radixArrays[i][0] = 0;    //indexΪ0����¼�������ݵĸ���  
//	    }  
//	    for (int pos=1; pos<=KEYNUM; pos++)   //�Ӹ�λ��ʼ��Ͱ����Ͱ
//	    {   
//	        for (int i=0; i<dataNum; i++)    //�������  
//	        {  
//	            int num = GetDigitInPos(unorderArray[i+offset],pos);  
//	            int index = ++radixArrays[num][0];  
//	            radixArrays[num][index] = unorderArray[i+offset];  
//	        }  
//	        for (int i=0, j=0; i<RADIX; i++)//�ռ�  
//	        {  
//	            for (int k = 1; k <= radixArrays[i][0]; k++)  
//	                unorderArray[offset+j++] = radixArrays[i][k];  
//	            radixArrays[i][0] = 0;    //��Ͱ��ϣ���λ  
//	        }  
//	    }
//	    id++;//��ɷֿ�����id++ ����id == 8 �ϲ�����
//	}
	
	
	public String toString()
	{
		return new String("radixSortParellel");
	}
	private int GetDigitInPos(Integer num, int pos) {
		// TODO Auto-generated method stub
		int temp = 1;  
	    for (int i = 0; i < pos - 1; i++)  
	        temp *= 10;  
	    return (num / temp) % 10;  
	}

	public static void main(String[] args)
    {
		Integer[] test;
		
	
		Integer N = 100000000;
		try {
			FileWriter writer=new FileWriter("RadixSortParellel.txt",true);
			
			
			Random random = new Random(N);
			System.out.println("The scale of array is: "+N.toString());
//					writer.write("\n\n"+ "The scale of array is: "+N.toString() +"\n");
			test = new Integer[N];
			for (Integer i =0;i<N;i++)
			{
				test[i] = random.nextInt(N);
	//			System.out.print(test[i] + " ");
			}
//					System.out.print("before is "+Arrays.toString(test));
			writer.close();
			testSort.evaluate(test, new RadixSortParellel(10000));
//			testSort.evaluate(test, new QuickSort());
//			testSort.evaluate(test, new RadixSort1(10000));
//				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
    }
}
