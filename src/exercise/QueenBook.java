package exercise;



public final class QueenBook {
    int n,m,ct = 0;
    int x[];
    int[][] like;
    public QueenBook(int m,int n,int[][] like_)
    {
    	this.n = n;
    	this.ct =0;
    	x = new int[m];
    	like = like_.clone();
    	this.m = m;
    }
    
    public void calculate(int i)
    {
        if(i >= m){
//        	output;
        	ct++;
        	return;
        }
        for(int j = 0;j < n;j++)
        {
        	x[i] = j;
        	if (constraint(i) == true && like[i][j] == 1)
        		calculate(i+1);
        	
        }
    }
    
    
    boolean constraint(int i)
    {
    	int k;
    	for(k =0;k <i;k++)
    		if(
    		   (x[k] == x[i]) )
    			return false;
    	return true;
    		     
    }
    
    public static void main(String[] args)
    {
    	int[][] test = {{0,0,1},{1,1,0},{1,1,1}};
    	QueenBook qn = new QueenBook(3,3,test);
    	qn.calculate(0);
    	System.out.println("the number of solution of " + "3" +" Queen question is "+ qn.ct);
    }
}
