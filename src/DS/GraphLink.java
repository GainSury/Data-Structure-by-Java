package DS;

public class GraphLink extends Graph{

	//
	
	public SpaMatrixLink edge;
	
	public GraphLink(byte kind)
	{
		super(kind);
		edge = new SpaMatrixLink(0,0);
	}
	
	public GraphLink(byte kind,String[] vex)
	{
		super(kind,vex);
		edge = new SpaMatrixLink(vex.length, vex.length);
	}
	
	@Override
	public int inDeg(int v) {
		// TODO Auto-generated method stub
		if (gkind == undigraph)
			return outDeg(v);
		else
		{
			int count = 0;
			for (int k = 0; k < vertexCount(); k++) {
				for (int j = 0; j < edge.get(k).size(); j++) {
					if(edge.get(k).get(j).j == v) count ++;
				}
			}
			return count;
		}
		
		
	}

	@Override
	public int outDeg(int v) {
		// TODO Auto-generated method stub
		return edge.get(v).size();
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
		if(i != j)
			return edge.get(i, j);
		else 
			return 0;
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
	
	public void removeEdge(String a1,String a2)
	{
		int index1 = vertex.indexOf(a1);
		int index2 = vertex.indexOf(a2);
		removeEdge(index1,index2);
	}

	@Override
	public void removeVertex(int i) {
		// TODO Auto-generated method stub
		vertex.set(i, null);
		for (int j = 0; j < edge.size(); j++) {
			edge.remove(j, i);
		}
		edge.get(i).clear();
	}

	@Override
	public int edgeCount() {
		// TODO Auto-generated method stub
		int edgeNum = 0;
		int edgeTotalSize = edge.rls.length;
		for(int i = 0; i < edgeTotalSize;i++)
		{
			edgeNum = edgeNum + edge.get(i).length();
		}
		if(gkind == digraph)
			return edgeNum;
		else 
			return edgeNum/2;
	}

	@Override
	public int firstAdjVex(int v) {
		// TODO Auto-generated method stub
		if (v < 0)
			return -1;
		LinkList<Triple> itr = edge.get(v);
		if(itr == null)
			return -1;
		Triple o = itr.get(0);
		if (o == null)
			return -1;
		else 
			return o.j;
	}

	
	@Override
	public int nextAdjVex(int v, int w) {
		// TODO Auto-generated method stub
	    if (v <0|| w <0)
	    	return -1;
	    LinkList<Triple> itr = edge.get(v);
	    if(itr == null)
	    	return -1;
	    int i = itr.indexOf(new Triple(v,w,0));
	    Triple o = itr.get(i + 1);
	    if (o ==null)
	    	return -1;
	    else 
	    	return o.j;
	}
	
	
	public void outputGraph()
	{
		System.out.printf("图类型 = %s,顶点数 = %d,边数  = %d\n", gkind ==0 ? "无向图":"有向图",vertexCount(),edgeCount());
		System.out.println("===============================");
		System.out.println(edge);
	}
	public static void main(String[] args)
	{
		String[] vex = {"A","B","C","D","E"};		
		GraphLink mg = new GraphLink(digraph, vex);
		mg.addEdge("A", "B", 5);
		mg.addEdge("B", "A", 5);
		mg.addEdge("C", "B", 5);
		mg.addEdge("D", "B", 5);
		mg.addEdge("D", "E", 5);
		mg.addEdge("D", "A", 5);
		mg.addEdge("D", "C", 5);
		mg.removeEdge("A", "B");
		mg.outputGraph();
		
		
	}

}
