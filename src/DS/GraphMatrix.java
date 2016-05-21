package DS;
//图不会变
public class GraphMatrix extends Graph{
    int edgeNum = 0;
    //邻接矩阵
    protected double edge[][];
    public GraphMatrix(byte kind)
    {
    	gkind = kind;
    }
	
    public GraphMatrix(byte kind ,String[] vex)
    {
    	super(kind,vex);
    	int vertexNum = vertexCount();
    	edge = new double[vertexNum][vertexNum];
    	for(int i = 0;i <vertexNum;i++)
    		for(int j = 0;j < vertexNum;j++)
    		    edge[i][j] = (j == i)?0:Double.MAX_VALUE;
    	gkind = kind;
    }
    
    //todo
    
    public void addVertex(String e)
    {
    	
    	double temp[][] = new double[edge.length][edge.length];
    	vertex.add(e);
    	int vertexNum = vertexCount();
    	
    	if(vertexNum > edge.length)
    	{
    		temp = edge;
    		edge = new double[temp.length+10][temp.length+10];
    	}
    	for(int i = 0;i < temp.length;i++)
    	{
    		for(int j = 0;j < temp.length;j++)
    			edge[i][j] = temp[i][j];
    		for(int j = temp.length;j < edge.length;j++)
    			edge[i][j] = Double.MAX_VALUE;
    	}
    	for(int i = temp.length; i <edge.length;i++)
    		for(int j = 0;j < edge.length;j++)
    			edge[i][j] = Double.MAX_VALUE;
    }
    
    public void removeVertex(int v)
    {
    	this.setVertex(v,null);//懒惰删除
    	
    	for(int j = 0;j <edge.length;j++)
    		if(edge[v][j] != 0 && edge[v][j] < Double.MAX_VALUE)
    			removeEdge(v,j);
    	for(int j = 0;j <edge.length;j++)
    		if(edge[j][v] != 0 && edge[j][v] < Double.MAX_VALUE)
    			removeEdge(v,j);
    }
    
    
    
    @Override
	public int inDeg(int v) {
		// TODO Auto-generated method stub
		int count = 0;
		for(int j = 0;j <this.vertexCount();j++)
			if(edge[j][v] != 0 && edge[j][v] != Double.MAX_VALUE)
				count ++;
		return count;
	}
	@Override
	public int outDeg(int v) {
		// TODO Auto-generated method stub
		int count = 0;
		for(int j = 0;j <this.vertexCount();j++)
			if(edge[v][j] != 0 && edge[v][j] != Double.MAX_VALUE)
				count ++;
		return count;
	}
	@Override
	public void addEdge(int i, int j, double value) {
		// TODO Auto-generated method stub
		edge[i][j] = value;
		edgeNum ++;
		if(gkind == undigraph)
			edge[j][i] = value;
	}
	@Override
	public double getWeight(int i, int j) {
		// TODO Auto-generated method stub
		return edge[i][j];
	}
	@Override
	public void setWeight(int i, int j, double value) {
		// TODO Auto-generated method stub
		edge[i][j] = value;
		if(gkind == undigraph)
			edge[j][i] = value;
	}
	@Override
	public void removeEdge(int i, int j) {
		// TODO Auto-generated method stub
		edge[i][j] = Double.MAX_VALUE;
		edgeNum--;
		if(gkind == undigraph)
			edge[j][i] = Double.MAX_VALUE;
	}
	
	
	@Override
	public int edgeCount() {
		// TODO Auto-generated method stub
		return edgeNum;
	}
	@Override
	public int firstAdjVex(int v) {
		// TODO Auto-generated method stub
		for(int j = 0; j< edge.length;j++)
		{
			if(edge[v][j] < Double.MAX_VALUE)
				return j;
		}
		return -1;
	}
	@Override
	public int nextAdjVex(int v, int w) {
		// TODO Auto-generated method stub
		for(int j = w + 1; j< edge.length;j++)
		{
			if(edge[v][j] < Double.MAX_VALUE)
				return j;
		}
		return -1;
	}
	
	public void outputGraph()
	{
		System.out.printf("vertexCount = %d, edgeCount = %d\n",vertexCount(),edgeCount());
		System.out.println("=====================");
		int m,n;
		m = edge.length; //行数
		n = edge[0].length; //列数
		for(int i= 0; i< m; i++){
			for (int j = 0;j < n;j++){
				if(edge[i][j] == Double.MAX_VALUE)
					System.out.printf("%-5c", '∞');
				else
					System.out.printf("%-5.0f", edge[i][j]);
			}
			System.out.println();
		}
		
		
	}
	public static void main(String[] args)
	{
		String[] vex = {"A","B","C","D","E"};		
		GraphMatrix mg = new GraphMatrix(digraph, vex);
		mg.addEdge("A", "B",5 );
		mg.addEdge("A", "D", 2);
		mg.addEdge("B", "A", 5);
		mg.addEdge("B", "C", 4);
		mg.addEdge("C", "E", 9);
		mg.addEdge("B", "C", 5);
		mg.addEdge("C", "E", 1);
		mg.addEdge("E", "C", 7);
		mg.addEdge("D", "C", 7);
		mg.addEdge("D", "E", 9);
		mg.outputGraph();
		System.out.println("bfs");
		for(int i = 0; i< 5;i++)
		{
			System.out.print("起始点 "+mg.getVertex(i) + ", 连通分量： ");
//			mg.dfs(mg, i);
			mg.bfs(mg, i);
		}
		System.out.println("dfs");
		for(int i = 0; i< 5;i++)
		{
			System.out.print("起始点 "+mg.getVertex(i) + ", 连通分量： ");
			mg.dfs(mg, i);
//			mg.bfs(mg, i);
		}
		
	}
}
