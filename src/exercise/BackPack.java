package exercise;

public class BackPack {
    int n = 0,ct = 0;
    int T;
    int x[];
    int[] weight;
    BackPack(int n,int T,int[] weight)
    {
    	this.n = n;
    	this.T = T;
    	this.x = new int[n];
    	this.weight = weight;
    }
    
    public void calculate(int i)
    {
    	if(i >= n)
    	{
//    		for(Integer t:x)
//    			System.out.print(t +" ");
    		int asum =0;
    		for(int k = 0;k < n; k++)
        		if(x[k] == 1)
        			asum += weight[k];
    	    if (asum == T){
    		    ct ++;
    		    for(int k=0;k <n;k++)
    		    	if(x[k] == 1)
        			    System.out.print(weight[k] +" ");
    		    System.out.println();
    	    }
    		return;
    	}
    	x[i] = 0;
    	if(constraint(i) == true)
    		calculate(i+1);
    	
    	x[i] = 1;
    	if(constraint(i) == true)
    		calculate(i+1);
    	
    }
    
    boolean constraint(int i)
    {
    	int sum = 0;
    	for(int k = 0;k <= i; k++)
    		if(x[k] - 1 == 0)
    			sum += weight[k];
    	return sum <= T;
    }
    
    public static void main(String[] args)
    {
    	int[] weight = {1,8,4,3,5,2};
    	BackPack test = new BackPack(6, 10, weight);
    	test.calculate(0);
    	System.out.println(test.ct);
    }
}
