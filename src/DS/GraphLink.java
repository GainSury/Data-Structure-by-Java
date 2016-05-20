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
		return 0;
	}

	@Override
	public int nextAdjVex(int v, int w) {
		// TODO Auto-generated method stub
		return 0;
	}

}
