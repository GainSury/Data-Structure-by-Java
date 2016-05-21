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
    
    
//    public void addVertex(String e)
//    {
//    	
//    	double temp[][] = new double[edge.length][edge.length];
//    	vertex.add(e);
//    	int vertexNum = vertexCount();
//    	
//    	if(vertexNum > edge.length())
//    	{
//    		temp = edge;
//    		edge = new double[temp.length+10][temp.length+10];
//    	}
//    	for(int i = 0;i < temp.length;i++)
//    	{
//    		for(int j = 0;j < temp.length;j++)
//    			edge[i][j] = temp[i][j];
//    		for(int j = temp.length;j < edge.length;j++)
//    			edge[i][j] = Double.MAX_VALUE;
//    	}
//    	for(int i = temp.length; i <edge.length;i++)
//    		for(int j = 0;j < edge.length;j++)
//    			edge[i][j] = Double.MAX_VALUE;
//    }
//	
	
    
    
    @Override
	public int outDeg(int v) {
		// TODO Auto-generated method stub
		int count =0;
		for(int k = 0; k < edgeCount();k++)
		{
			if(edge.get(k).i == v)
				count++;
		}
		return count;
	}

	@Override
	public void addEdge(int i, int j, double w) {
		// TODO Auto-generated method stub
        edge.add(i, j, w);
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
		edge.remove(i, j);
		if(gkind == undigraph)
			edge.remove(j, i);
	}

	@Override
	public void removeVertex(int i) {
		// TODO Auto-generated method stub
		vertex.set(i, null);
		for (int j = 0; j < edge.size(); j++) {
			edge.remove(j, i);
		}
		//É¾³ı³ö±ß ´ı¶¨
		for (int j = 0; j < edge.size(); j++) {
			edge.remove(i, j);
		}
		
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
		Triple to = edge.get(start);
		if(to == null)
			return -1;
		if(to.i == v)
			return to.j;
		else 
			return -1;
	}

	@Override
	public int nextAdjVex(int v, int w) {
		// TODO Auto-generated method stub
		int start = edge.indexOf(v, w);
		if(start >= edge.length())
			return -1;
		Triple to = edge.get(start+1);
		if(to == null)
			return -1;
		if(to.i == v)
			return to.j;
		else
			return -1;
		
	}
	
	public int inDeg(int v)
	{
		if(gkind == undigraph)
			return outDeg(v);
		else{
			int count = 0;
			for(int k =0; k < edgeCount();k++)
			{
				if(edge.get(k).j == v)
					count++;
			}
			return count;
		}
		
	}

}
