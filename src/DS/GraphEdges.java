package DS;

public class GraphEdges extends Graph{

	
	public SpaMatrixArray edge;
	
    GraphEdges(byte kind)
    {
    	super(kind);
		edge = new SpaMatrixArray(0,0);
    }
    
    GraphEdges(byte kind,String[] vex)
	{
		super(kind,vex);
		edge = new SpaMatrixArray(vex.length, vex.length);
	}
    
    
    public void addVertex(String e)
    {
    	
    	double temp[][] = new double[edge.length][edge.length];
    	vertex.add(e);
    	int vertexNum = vertexCount();
    	
    	if(vertexNum > edge.length())
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
	
	@Override
	public int inDeg(int v) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int outDeg(int v) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void addEdge(int i, int j, double w) {
		// TODO Auto-generated method stub
		edge.add(j, i, w);
		if(gkind == undigraph)
			edge.add(j, i, w);
	}

	@Override
	public double getWeight(int i, int j) {
		// TODO Auto-generated method stub
	     return edge.get(i, j);
	}

	@Override
	public void setWeight(int i, int j, double value) {
		// TODO Auto-generated method stub
		edge.set(j, i, value);
		if(gkind == undigraph)
			edge.set(j, i, value);
	}

	@Override
	public void removeEdge(int i, int j) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeVertex(int i) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int edgeCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int firstAdjVex(int v) {
		// TODO Auto-generated method stub
		edge.sort();
		int start = edge.startOfRow(v);
		if(start >= edge.length())
			return -1;
		Triple
	}

	@Override
	public int nextAdjVex(int v, int w) {
		// TODO Auto-generated method stub
		return 0;
	}

}
