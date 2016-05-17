package DS;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

interface CallBackInterface
{

	void exectueMethod();
}

class MethodB{
	public double getTime(CallBackInterface callBack)
	{
		 long start = System.currentTimeMillis();
		 callBack.exectueMethod();
	     long end = System.currentTimeMillis();
	     return end - start;
	}
}

interface Sort{
	void sort(Integer[] array);
}

//InsertSort
class InsertSort implements Sort
{
	public void sort(Integer[] array)
	{
		int n = array.length;
		int temp;
		for(int i = 1; i <= n - 1;i++)
		{
			int j;
			temp = array[i];
			for (j = i; j > 0 && array[j - 1] > temp; j--)
				array[j] = array[j-1];
			array[j] = temp;
		}
		
	}
	public String toString()
	{
		return "insertSort";
	}
}

//SelectSort
class SelectSort implements Sort
{
	public void sort(Integer[] a)
	{
		int n = a.length,jmin = 0,amin = 0,t,i,j;
		for (i = 0;i<n;i++)
		{
			amin = a[i];
			for(j = i +1;j < n;j++)
			{
				if(a[j] < amin)
				{
					amin = a[j];
					jmin = j;
				}
				if (amin <a[i] )
				{
				   t = a[i];
				   a[i] = a[jmin];
				   a[jmin] = t;
				}
			}
		}
	}
	
	public String toString()
	{
		return "selectSort";
	}
	
}

//BubbleSort
class BubbleSort implements Sort
{
	public void sort(Integer[] data)
	{
		 for (int i = 0; i < data.length - 1; i++) {// 控制趟数  
	            for (int j = 0; j < data.length - i - 1; j++) {  
	  
	                if (data[j] > data[j + 1]) {  
	                    int tmp = data[j];  
	                    data[j] = data[j + 1];  
	                    data[j + 1] = tmp;  
	                }  
	            }  
	        }  
	}
	
	public String toString()
	{
		return "bubbleSort";
	}
	
}
//QuickSort
class QuickSort implements Sort
{
	public void sort(Integer[] data)
	{
		qsort(data,0,data.length-1);
	}
	private static void qsort(Integer[] arr, int low, int high){
	    if (low < high){
	        int pivot=partition(arr, low, high);        //将数组分为两部分
	        qsort(arr, low, pivot-1);                   //递归排序左子数组
	        qsort(arr, pivot+1, high);                  //递归排序右子数组
	    }
	}
	private static int partition(Integer[] arr, int low, int high){
	    int pivot = arr[low];     //枢轴记录
	    while (low<high){
	        while (low<high && arr[high]>=pivot) --high;
	        arr[low]=arr[high];             //交换比枢轴小的记录到左端
	        while (low<high && arr[low]<=pivot) ++low;
	        arr[high] = arr[low];           //交换比枢轴小的记录到右端
	    }
	    //扫描完成，枢轴到位
	    arr[low] = pivot;
	    //返回的是枢轴的位置
	    return low;
	}
	   
	
    public String toString()
	{
		return "QuickSort";
	}
	
}

//ShellSort
class ShellSort implements Sort
{
	public void sort(Integer[] data)
	{
		shellSort(data);
	}
    public static void shellSort(Integer[] data) {  
        // 计算出最大的h值  
        int h = 1;  
        while (h <= data.length / 3) {  
            h = h * 3 + 1;  
        }  
        while (h > 0) {  
            for (int i = h; i < data.length; i += h) {  
                if (data[i] < data[i - h]) {  
                    int tmp = data[i];  
                    int j = i - h;  
                    while (j >= 0 && data[j] > tmp) {  
                        data[j + h] = data[j];  
                        j -= h;  
                    }  
                    data[j + h] = tmp;  
                    
                }  
            }  
            // 计算出下一个h值  
            h = (h - 1) / 3;  
        }  
    } 
	public String toString()
	{
		return "shellSort";
	}
	
}


//mergesort
class MergeSort implements Sort
{
	public void sort(Integer[] data){  
    	mergeSort(data, 0, data.length - 1);  
    }  
  
    public static void mergeSort(Integer[] data, int left, int right) {  
        if (left >= right)  
            return;  
        // 找出中间索引  
        int center = (left + right) / 2;  
        // 对左边数组进行递归  
        mergeSort(data, left, center);  
        // 对右边数组进行递归  
        mergeSort(data, center + 1, right);  
        // 合并  
        merge(data, left, center, right);  
       
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
    }  
  
	public String toString()
	{
		return "mergeSort";
	}
	
}


//HeapSort
class HeapSort implements Sort
{
	public void sort(Integer[] data){  
		heapSort(data);
    }  
  
	 private static void heapSort(Integer[] arr) {   
	        // 将待排序的序列构建成一个大顶堆  
	        for (int i = arr.length / 2; i >= 0; i--){   
	            heapAdjust(arr, i, arr.length);   
	        }  
	          
	        // 逐步将每个最大值的根节点与末尾元素交换，并且再调整二叉树，使其成为大顶堆  
	        for (int i = arr.length - 1; i > 0; i--) {   
	            swap(arr, 0, i); // 将堆顶记录和当前未经排序子序列的最后一个记录交换  
	            heapAdjust(arr, 0, i); // 交换之后，需要重新检查堆是否符合大顶堆，不符合则要调整  
	        }  
	    }  
	  
	    /** 
	     * 构建堆的过程 
	     * @param arr 需要排序的数组 
	     * @param i 需要构建堆的根节点的序号 
	     * @param n 数组的长度 
	     */  
	    private static void heapAdjust(Integer[] arr, int i, int n) {  
	        int child;  
	        int father;   
	        for (father = arr[i]; leftChild(i) < n; i = child) {  
	            child = leftChild(i);  
	              
	            // 如果左子树小于右子树，则需要比较右子树和父节点  
	            if (child != n - 1 && arr[child] < arr[child + 1]) {  
	                child++; // 序号增1，指向右子树  
	            }  
	              
	            // 如果父节点小于孩子结点，则需要交换  
	            if (father < arr[child]) {  
	                arr[i] = arr[child];  
	            } else {  
	                break; // 大顶堆结构未被破坏，不需要调整  
	            }  
	        }  
	        arr[i] = father;  
	    }  
	  
	    // 获取到左孩子结点  
	    private static int leftChild(int i) {  
	        return 2 * i + 1;  
	    }  
	      
	    // 交换元素位置  
	    private static void swap(Integer[] arr, int index1, int index2) {  
	        int tmp = arr[index1];  
	        arr[index1] = arr[index2];  
	        arr[index2] = tmp;  
	    }  
	  
	public String toString()
	{
		return "heapSort";
	}
	
}



//RadixSort
class RadixSort implements Sort
{
	int d = 0;
	// d为数据长度
	RadixSort(int d_)
	{
		this.d = d_;
	}
	public void sort(Integer[] array) 
	{
	    int n=1;//代表位数对应的数：1,10,100...
	    int k=0;//保存每一位排序后的结果用于下一位的排序输入
	    int length=array.length;
	    int[][] bucket=new int[10][length];//排序桶用于保存每次排序后的结果，这一位上排序结果相同的数字放在同一个桶里
	    int[] order=new int[length];//用于保存每个桶里有多少个数字
	 
	    while(n<d)
	    {
	        for(int num:array) //将数组array里的每个数字放在相应的桶里
	        {
	            int digit=(num/n)%10;
	            bucket[digit][order[digit]]=num;
	            order[digit]++;
	        }
	        for(int i=0;i<length;i++)//将前一个循环生成的桶里的数据覆盖到原数组中用于保存这一位的排序结果
	        {
	            if(order[i]!=0)//这个桶里有数据，从上到下遍历这个桶并将数据保存到原数组中
	            {
	                for(int j=0;j<order[i];j++)
	                {
	                    array[k]=bucket[i][j];
	                    k++;
	                }
	            }
	            order[i]=0;//将桶里计数器置0，用于下一次位排序
	        }
	        n*=10;
	        k=0;//将k置0，用于下一轮保存位排序结果
	    }
	    
	}
	public String toString()
	{
		return "radixSort";
	}
}

public class testSort {
    //test if Accuracy of sort
	
	public static void evaluate(Integer[] array,Sort sb)
	{

		Integer[] clone = array.clone();
		MethodB b=new MethodB();
        //回调函数
        double d=b.getTime(new CallBackInterface() {
             
           
            public void exectueMethod() {
                sb.sort(clone);
            }
        }
                           );
//        System.out.println("\n\n"+sb.toString()+" using:"+ d/1000.0 + "s \nthe result is: "+ testSort(clone));
        
        try {
        	FileWriter writer=new FileWriter("Sortdata.txt",true);
			writer.write("\n\n"+sb.toString()+" using:"+ d/1000.0 + "s \nthe result is: "+ testSort(clone));
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static boolean testSort(Integer[] array)
	{
		for (int i = 0;i < array.length -1;i++)
			if(array[i] > array[i+1])
				return false;
		return true;
	}
	
	//insertSort
	
	
	public static void main(String[] args)
	{
		Integer N = 1000000;
		{
			try {
				FileWriter writer=new FileWriter("Sortdata.txt",true);
				
				
				Random random = new Random(N);
		//		System.out.println("The scale of array is: "+N.toString());
				writer.write("\n\n"+ "The scale of array is: "+N.toString() +"\n");
				Integer[] test = new Integer[N];
				for (Integer i =0;i<N;i++)
				{
					test[i] = random.nextInt(N);
		//			System.out.print(test[i] + " ");
				}
				writer.close();
				evaluate(test,new InsertSort());
				evaluate(test,new SelectSort());
				evaluate(test,new BubbleSort());
				evaluate(test,new QuickSort());
				evaluate(test,new ShellSort());
				evaluate(test,new MergeSort());
				evaluate(test,new HeapSort());
				evaluate(test,new RadixSort(N));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	
}
