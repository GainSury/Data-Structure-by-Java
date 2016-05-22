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
		   start(); // 开始线程
	    }
	   // 线程入口
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
		  start(); // 开始线程
	   }
	 
	   // 线程入口
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
     * 将两个数组进行归并，归并前面2个数组已有序，归并后依然有序 
     *  
     * @param data 
     *            数组对象 
     * @param left 
     *            左数组的第一个元素的索引 
     * @param center 
     *            左数组的最后一个元素的索引，center+1是右数组第一个元素的索引 
     * @param right 
     *            右数组最后一个元素的索引 
     *            center + 1 =offset
     *            所以 offset = center -1;
     *            
     */  
    public static void merge(Integer[] data, int left, int center, int right) {  
        // 临时数组  
    	Integer[] tmpArr = new Integer[data.length];  
        // 右数组第一个元素索引  
    	int mid = center + 1;  
        // third 记录临时数组的索引  
    	int third = left;  
        // 缓存左数组第一个元素的索引  
        int tmp = left;  
        while (left <= center && mid <= right) {  
            // 从两个数组中取出最小的放入临时数组  
            if (data[left] <= data[mid]) {  
                tmpArr[third++] = data[left++];  
            } else {  
                tmpArr[third++] = data[mid++];  
            }  
        }  
        // 剩余部分依次放入临时数组（实际上两个while只会执行其中一个）  
        while (mid <= right) {  
            tmpArr[third++] = data[mid++];  
        }  
        while (left <= center) {  
            tmpArr[third++] = data[left++];  
        }  
        // 将临时数组中的内容拷贝回原数组中  
        // （原left-right范围的内容被复制回原数组）  
        while (tmp <= right) {  
            data[tmp] = tmpArr[tmp++];  
        }  
        id2++;
    }  
  
	
	private void mergeBlocks(Integer[] pDataArray, int arrayLen, int blockNum, Integer[] resultArray) {
		// TODO Auto-generated method stub
		int blockLen=arrayLen/blockNum;
	    Integer[] blockIndex = new Integer[blockNum];//各个块中元素在数组中的下标，VC可能不支持变量作为数组的长度，解决办法可使用宏定义
	    for(int i=0;i<blockNum;++i)//初始化块内元素起始下标
	    {
	        blockIndex[i]=i*blockLen;
	    }
	    int smallest=0;
	    for(int i=0;i<arrayLen;++i)//扫描所有块内的所有元素
	    {  
	      for(int j=0;j<blockNum;++j)//以第一个未扫描完的块内元素作为最小数
	      {
	       if(blockIndex[j]<(j*blockLen+blockLen))
	       {
	        smallest=pDataArray[blockIndex[j]];
	        break;
	       }
	      }
	      for(int j=0;j<blockNum;++j)//扫描各个块，寻找最小数
	      {
	        if((blockIndex[j]<(j*blockLen+blockLen))&&(pDataArray[blockIndex[j]]<smallest))
	        {
	          smallest=pDataArray[blockIndex[j]];
	        }
	      }
	      for(int j=0;j<blockNum;++j)//确定哪个块内元素下标进行自增
	      {
	        if((blockIndex[j]<(j*blockLen+blockLen))&&(pDataArray[blockIndex[j]]==smallest))
	        {
	          ++blockIndex[j];
	          break;
	        }
	      }
	      resultArray[i]=smallest;//本次循环最小数放入结果数组
	    }
	    
	    //复制数组
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
	    id++;//完成分块排序，id++ 满足id == 8 合并数组
	}
//	public void RadixSort(Integer[] unorderArray,int offset,int dataNum)  
//	{  
//		Integer[][] radixArrays = new Integer[RADIX][];    //分别为0~9基数的存放空间  
//	    for (int i=0; i<10; i++)  
//	    {  
//	        radixArrays[i] = new Integer[dataNum + 1];  
//	        radixArrays[i][0] = 0;    //index为0处记录这组数据的个数  
//	    }  
//	    for (int pos=1; pos<=KEYNUM; pos++)   //从个位开始入桶并出桶
//	    {   
//	        for (int i=0; i<dataNum; i++)    //分配过程  
//	        {  
//	            int num = GetDigitInPos(unorderArray[i+offset],pos);  
//	            int index = ++radixArrays[num][0];  
//	            radixArrays[num][index] = unorderArray[i+offset];  
//	        }  
//	        for (int i=0, j=0; i<RADIX; i++)//收集  
//	        {  
//	            for (int k = 1; k <= radixArrays[i][0]; k++)  
//	                unorderArray[offset+j++] = radixArrays[i][k];  
//	            radixArrays[i][0] = 0;    //出桶完毕，复位  
//	        }  
//	    }
//	    id++;//完成分块排序，id++ 满足id == 8 合并数组
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
