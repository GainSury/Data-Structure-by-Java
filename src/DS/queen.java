package DS;

public final class queen {
    int n,ct = 0;
    int x[];
    public queen(int n)
    {
    	this.n = n;
    	this.ct =0;
    	x = new int[n];
    }
    
    public void calculate(int i)
    {
        if(i >= n){
//        	output;
        	ct++;
        	return;
        }
        for(int j = 0;j < n;j++)
        {
        	x[i] = j;
        	if (constraint(i) == true)
        		calculate(i+1);
        	
        }
    }
    
    
    boolean constraint(int i)
    {
    	int k;
    	for(k =0;k <i;k++)
    		if(
    		   (x[k] == x[i]) || (Math.abs(x[k] - x[i])) == Math.abs(k-i))
    			return false;
    	return true;
    		     
    }
    
    public static void main(String[] args)
    {
    	Integer[] num = new Integer[]{2,4,5,6,8,9,10,11,12,13};
    	for(Integer i:num){
	    	queen qn = new queen(i);
	    	qn.calculate(0);
	    	System.out.println("the number of solution of " + i +" Queen question is "+ qn.ct);
    	}
    }
}
