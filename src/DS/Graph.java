package DS;

public abstract class Graph {
    public final static byte undigraph = 0;
    public final static byte digraph = 1;
    protected byte gkind;
    protected SeqList<String> vertex;
    
    public Graph(byte kind)
    {
    	vertex = new SeqList<String>();
    	gkind = kind;
    }
    
    Graph()
    {
    	this(digraph);
    }
    
    public Graph(byte kind, String[] vex)
    {
    	vertex = new SeqList<String>(vex);
    	gkind = kind;
    }
    
    public void setGraphKind(byte kind)
    {
    	gkind = kind;
    }
    
    //还有一些邻接点是懒惰删除的 
    public int vertexCount()
    {
    	int vertexNum = 0;
    	for(int i = 0; i < vertex.size();i++)
    		if(vertex.get(i) != null)
    			vertexNum++;
    	return vertexNum;
    }
    
    
    public void addVertex(String e)
    {
    	vertex.add(e);
    }
    
    public String getVertex(int i)
    {
    	return vertex.get(i);
    }
    
    void setVertex(int i,String data)
    {
    	vertex.set(i, data);
    }
    
    public int indexOfVertex(String data)
    {
    	return vertex.indexOf(data);
    }
    
    public void removeVertex(String vexValue)
    {
    	int i = indexOfVertex(vexValue);
    	if(i != -1)
    		vertex.remove(i); //出入
    }
    
    public abstract int inDeg(int v);//求顶点V的入度
    
    public int inDeg(String v)
    {
    	int i = indexOfVertex(v);
    	if(i != -1)
    		return inDeg(i);
    	else
    		return -1;
    }
    
    public int outDeg(String v)
    {
    	int i = indexOfVertex(v);
    	if(i != -1)
    		return outDeg(i);
    	else 
    		return -1;
    }
    
    public abstract int outDeg(int v);//求顶点V的出度
    
    public void addEdge(String start,String end,double value)
    {
    	int i = indexOfVertex(start);
    	int j = indexOfVertex(end);
    	if(i < 0 || j < 0)
    		throw new IndexOutOfBoundsException("顶点" + start + "或者"+ end+"不存在");
    	this.addEdge(i, j, value);
    }
    
    public abstract void addEdge(int i,int j,double w);
    
    public abstract double getWeight(int i,int j);
    
    public abstract void setWeight(int i,int j,double value);
    
    public abstract void removeEdge(int i,int j);
    
    public abstract void removeVertex(int i);
    
    //求边数
    public abstract int edgeCount();
    
    public abstract int firstAdjVex(int v);
    //下一个邻接点
    public abstract int nextAdjVex(int v,int w);
    
}
