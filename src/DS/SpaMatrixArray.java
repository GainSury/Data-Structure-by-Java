package DS;

public class SpaMatrixArray {
    private int m,n; //行数、列数和非零元素综述
    private SeqList<Triple> list;
    private int[] colsnum;
    private int[] rpos;//每行第一个元素在顺序表中其实位置
    
    public SpaMatrixArray(int m_,int n_,int maxsize_)
    {
    	m = m_;
    	n = n_;
    	list = new SeqList<Triple>(maxsize_);
    	colsnum = new int[m];
    	rpos = new int[m];
    }
    

    public SpaMatrixArray(int m_,int n_,SpaMatrixArray o)
    {
    	m = m_;
    	n = n_;
    	list = o.list;
    	colsnum = new int[m];
    	rpos = new int[m];
    }
    
    public SpaMatrixArray(int m_,int n_)
    {
    	this(m_,n_,16);
    }
    
    public void sort()
    {
    	list.sort();
    }
    SpaMatrixArray()
    {
    	this(10,10);
    }
    
    public double get(int i,int j)
    {
    	if(i < 0|| i >= m| j< 0||j >=n)
    		throw new IndexOutOfBoundsException("下标溢出:" + i+","+j);
    	int k = indexOf(i ,j);
    	if(k == -1)
    		return Double.MAX_VALUE;
    	Triple key = get(k);
    	if(key != null)
    		return key.value;
    	else 
    		return Double.MAX_VALUE;
    		
    }
    
    public void set(int i,int j,double value)
    {
    	if(i < 0|| i >= m| j< 0||j >=n)
    		throw new IndexOutOfBoundsException("下标溢出:" + i+","+j);
    	int k = indexOf(i ,j);
    	if(k >= 0 && k <list.length)
    		list.set(k, new Triple(i,j,value));
    	else
    		throw new IndexOutOfBoundsException("元素不存在i,j="+i+","+j);
    		
    }
    
    public Triple get(int k){
    	if( k < 0 | k> list.length)
    		throw new IndexOutOfBoundsException("下标溢出:" + k);
    	return list.get(k);		
    }
    
    public Triple remove(int k){
    	if( k < 0 | k> list.length)
    		throw new IndexOutOfBoundsException("下标溢出:" + k);
    	return list.remove(k);		
    }
    
    public int startOfRow(int i)
    {
    	return rpos[i];
    }
    
    public int colsOfRow(int i)
    {
    	return colsnum[i];
    }
    
    public int length()
    {
    	return list.length();
    }
    
    public int size()
    {
    	return list.size();
    }
    
    
    public void add(int i,int j,double value)
    {
    	if(i < 0|| j< 0)
    		throw new IndexOutOfBoundsException("下标溢出 i,j="+i+","+j);
    	if(j >= this.n)
    		this.n = j+1;
    	list.addSort(new Triple(i,j,value));
    	colsnum[i]++;
    	for(int k = i +1;k<m;k++)
    		rpos[k] = rpos[k -1]+ colsnum[k-1];
    	
    		
    }
    
    public Triple remove(int i,int j){
    	if(i < 0|| i >= m| j< 0||j >=n)
    		throw new IndexOutOfBoundsException("下标溢出:" + i+","+j);
    	Triple elem = list.remove(new Triple(i,j,0.0));
    	colsnum[i]--;
    	for(int k = i +1;k<m;k++)
    		rpos[k] = rpos[k -1]+ colsnum[k-1];
    	return elem;
    }
    
    public int indexOf(int i,int j)
    {
    	int  endposition = rpos[i] + colsnum[i];
    	for(int k = rpos[i];k <endposition;k++)
    	{
    		if(
    			((Triple)list.get(k)).j == j )
    			return k;
    	}
    	return -1;
    }
    public SpaMatrixArray transpose()
    {
    	int tu = size();
    	SpaMatrixArray B = new SpaMatrixArray(n,m,this);
    	B.list.setCapacity(tu);
    	for(int k = 0; k <tu;k++)
    		B.colsnum[get(k).j]++;
    	B.rpos[0] = 0;
    	for (int c = 1; c < n; c++) {
    		B.rpos[c] = B.rpos[c -1] + B.colsnum[c-1];
		}
    	int[] tmp = B.rpos;
    	
    	for(int k = 0; k< tu;k++)
    	{
    		int col = get(k).j;
    		int q = tmp[col];
//    		int q = B.rpos[col];
    		Triple o =get(k);
    		
    		B.list.set(q, new Triple(o.j,o.i,o.value));
    	
//    		B.rpos[col]++;
    		tmp[col]++;
    	}
    	
    	return B;
    }
    
    public int rows()
    {
    	return m;
    }
    
    public int cols(){
    	return n;
    }
    
    public String toString()
    {
    	StringBuilder strb = new StringBuilder();
    	strb = strb.append("m="+m+","+"n="+n+","+"size="+size());
    	strb = strb.append("\n");
    	for(int k = 0; k < list.length;k++)
    	{
    		strb = strb.append(list.get(k) + "\n");
    	}
    	return  new String(strb);
    }
    
    public static void main(String[] args)
    {
    	SpaMatrixArray test = new SpaMatrixArray(3,2,300);
    	test.add(0, 0, 1);
    	test.add(0, 1, 1);
    	System.out.print(test);
    	System.out.print(test.transpose());
    }
    
    
    
}
