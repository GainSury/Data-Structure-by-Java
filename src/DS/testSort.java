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
		 for (int i = 0; i < data.length - 1; i++) {// ��������  
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
	        int pivot=partition(arr, low, high);        //�������Ϊ������
	        qsort(arr, low, pivot-1);                   //�ݹ�������������
	        qsort(arr, pivot+1, high);                  //�ݹ�������������
	    }
	}
	private static int partition(Integer[] arr, int low, int high){
	    int pivot = arr[low];     //�����¼
	    while (low<high){
	        while (low<high && arr[high]>=pivot) --high;
	        arr[low]=arr[high];             //����������С�ļ�¼�����
	        while (low<high && arr[low]<=pivot) ++low;
	        arr[high] = arr[low];           //����������С�ļ�¼���Ҷ�
	    }
	    //ɨ����ɣ����ᵽλ
	    arr[low] = pivot;
	    //���ص��������λ��
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
        // ���������hֵ  
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
            // �������һ��hֵ  
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
        // �ҳ��м�����  
        int center = (left + right) / 2;  
        // �����������еݹ�  
        mergeSort(data, left, center);  
        // ���ұ�������еݹ�  
        mergeSort(data, center + 1, right);  
        // �ϲ�  
        merge(data, left, center, right);  
       
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
	        // ������������й�����һ���󶥶�  
	        for (int i = arr.length / 2; i >= 0; i--){   
	            heapAdjust(arr, i, arr.length);   
	        }  
	          
	        // �𲽽�ÿ�����ֵ�ĸ��ڵ���ĩβԪ�ؽ����������ٵ�����������ʹ���Ϊ�󶥶�  
	        for (int i = arr.length - 1; i > 0; i--) {   
	            swap(arr, 0, i); // ���Ѷ���¼�͵�ǰδ�����������е����һ����¼����  
	            heapAdjust(arr, 0, i); // ����֮����Ҫ���¼����Ƿ���ϴ󶥶ѣ���������Ҫ����  
	        }  
	    }  
	  
	    /** 
	     * �����ѵĹ��� 
	     * @param arr ��Ҫ��������� 
	     * @param i ��Ҫ�����ѵĸ��ڵ����� 
	     * @param n ����ĳ��� 
	     */  
	    private static void heapAdjust(Integer[] arr, int i, int n) {  
	        int child;  
	        int father;   
	        for (father = arr[i]; leftChild(i) < n; i = child) {  
	            child = leftChild(i);  
	              
	            // ���������С��������������Ҫ�Ƚ��������͸��ڵ�  
	            if (child != n - 1 && arr[child] < arr[child + 1]) {  
	                child++; // �����1��ָ��������  
	            }  
	              
	            // ������ڵ�С�ں��ӽ�㣬����Ҫ����  
	            if (father < arr[child]) {  
	                arr[i] = arr[child];  
	            } else {  
	                break; // �󶥶ѽṹδ���ƻ�������Ҫ����  
	            }  
	        }  
	        arr[i] = father;  
	    }  
	  
	    // ��ȡ�����ӽ��  
	    private static int leftChild(int i) {  
	        return 2 * i + 1;  
	    }  
	      
	    // ����Ԫ��λ��  
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
	// dΪ���ݳ���
	RadixSort(int d_)
	{
		this.d = d_;
	}
	public void sort(Integer[] array) 
	{
	    int n=1;//����λ����Ӧ������1,10,100...
	    int k=0;//����ÿһλ�����Ľ��������һλ����������
	    int length=array.length;
	    int[][] bucket=new int[10][length];//����Ͱ���ڱ���ÿ�������Ľ������һλ����������ͬ�����ַ���ͬһ��Ͱ��
	    int[] order=new int[length];//���ڱ���ÿ��Ͱ���ж��ٸ�����
	 
	    while(n<d)
	    {
	        for(int num:array) //������array���ÿ�����ַ�����Ӧ��Ͱ��
	        {
	            int digit=(num/n)%10;
	            bucket[digit][order[digit]]=num;
	            order[digit]++;
	        }
	        for(int i=0;i<length;i++)//��ǰһ��ѭ�����ɵ�Ͱ������ݸ��ǵ�ԭ���������ڱ�����һλ��������
	        {
	            if(order[i]!=0)//���Ͱ�������ݣ����ϵ��±������Ͱ�������ݱ��浽ԭ������
	            {
	                for(int j=0;j<order[i];j++)
	                {
	                    array[k]=bucket[i][j];
	                    k++;
	                }
	            }
	            order[i]=0;//��Ͱ���������0��������һ��λ����
	        }
	        n*=10;
	        k=0;//��k��0��������һ�ֱ���λ������
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
        //�ص�����
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
