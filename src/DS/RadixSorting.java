//package DS;
//
//public class RadixSorting {
//	
//	private int radix;
//    
//	RadixSorting(int radix_){
//		radix = radix_;
//	}
//	
//	public sort
//	
//	public  int get_part(int n,int i)  
//	{  
//	    int p=(int)Math.pow(radix,i);  
//	    return (int)(n/p)%radix;  
//	}  
//	void radix_sort(int[] a,int n)  
//	{  
//	    int[] bucket= new int[n];
//	    int[] count = new int[radix];
//	      
//	  
//	    for(int i=0;i<3;++i)  
//	    {  
//	          
//	  
//	        for(int j=0;j<n;++j)  
//	        {  
//	            count[get_part(a[j],i)]++;  
//	        }  
//	  
//	        for(int j=1;j<radix;++j)  
//	        {  
//	            count[j]+=count[j-1];  
//	        }  
//	  
//	        for(int j=n-1;j>=0;--j)  
//	        {  
//	            int k=get_part(a[j],i);  
//	            bucket[count[k]-1]=a[j];  
//	            count[k]--;  
//	        }  
//	  
//	        a = bucket;
//	    }
//	}
//	
//	
//}
