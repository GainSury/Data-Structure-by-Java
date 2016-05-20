package DS;

public class SpaMatrixLink implements Matrix{
    private int m,n; //矩阵的行数、列数和非零元素个数？ 这个非零元素个数在哪？
//    private int NotZeroNum;
    SeqList<LinkList<Triple>> rls;
    
    public SpaMatrixLink(int m_,int n_)
    {
    	m = m_;
    	n = n_;
    	rls = new SeqList<LinkList<Triple>>();
    	for(int i = 0; i<m; i++)
    		rls.add(new LinkList<Triple>());
    }
    
    public void add(int i,int j,double value)
    {
    	if(i < 0|| j < 0)
    		throw new IndexOutOfBoundsException("下标超出边界i="+i);
    	Triple key = new Triple(i,j,value);
    	if(j >= this.n) this.n = j+1;
    	if(i >=this.m)
    	{
    		for(int k = this.m;j <= i;k++)
    			rls.add(new LinkList<Triple>());
    		this.m = i+1;
    	}
    	int k = (rls.get(i)).indexOf(key);
    	if (k == -1)
    		rls.get(i).add(key);
    	else
    		rls.get(i).set(k, key);
    }
    
    public SpaMatrixLink()
    {
    	this(0,0);
    }
    
    public LinkList<Triple> get(int k)
    {
    	if( k < 0 || k >= m)
    		throw new IndexOutOfBoundsException("下标超出边界 k="+k);
    	return rls.get(k);
    	
    }
    
    public void set(int i,int j,double value)
    {
    	if(i < 0|| i >= m)
    		throw new IndexOutOfBoundsException("下标超出边界i="+i);
    	Triple key = new Triple(i,j,value);
    	int k = (rls.get(i)).indexOf(key);
    	if (k == -1) {
			throw new IndexOutOfBoundsException("不存在的值！");
		}
    	else
    		rls.get(i).set(i, key);
    }
    
	@Override
	public int rows() {
		// TODO Auto-generated method stub
		return m;
	}
	@Override
	public int cols() {
		// TODO Auto-generated method stub
		return n;
	}
	@Override
	public int size() {
		// TODO Auto-generated method stub
	    int s = 0;
	    for(int i=0;i < m;i++)
	    {
	    	s += rls.get(i).length();
	    }
	    return s;
	}

	@Override
	public double get(int i, int j) {
	    if (i < 0 || i>=m) {
	    	throw new IndexOutOfBoundsException("下标超出边界i="+i);
		}
	    Triple key = new Triple(i,j,0);
    	int k = (rls.get(i)).indexOf(key);
    	if (k == -1) {
			return Double.MAX_VALUE;
		}
    	else
    		return rls.get(i).get(k).value;
	    
	}

	
	@Override
	public Triple remove(int i, int j) {
		// TODO Auto-generated method stub
		Triple key = new Triple(i,j,0);
		if( i < 0|| i >= m)
			throw new IndexOutOfBoundsException("下标超出边界i="+i);
        int k = rls.get(i).indexOf(key);
        if (k != -1) {
			return rls.get(i).remove(k);
		}
    	else
    		return null;
	
	}
	
	@Override
	public SpaMatrixLink transpose() {
		// TODO Auto-generated method stub
		SpaMatrixLink B = new SpaMatrixLink(n,m);
		for(int i = 0;i < m;i++)
			for(Triple o:rls.get(i))
			{
				B.add(o.j, o.i, o.value);
			}
		return B;
	}
	
	public String toString()
	{
		StringBuilder strb = new StringBuilder();
		int len = 0;
		for (int k = 0; k < rls.length; k++) {
			len = len + rls.get(k).length();
		}
		strb = strb.append("m = " + rows()+ ","
				          + "n = " + cols()+ ","
				          +"e="+len);
		strb = strb.append("\n");
		for (int k = 0; k < rls.length; k++) {
			strb = strb.append(k +" |-> "+ rls.get(k) + "\n");
		}
		return new String(strb);
	}
	
	
	   public static void main(String[] args)
	    {
		   SpaMatrixLink test = new SpaMatrixLink(3,2);
	    	test.add(0, 0, 1);
	    	test.add(0, 1, 1);
	    	System.out.print(test);
	    	System.out.print(test.transpose());
	    }
}
