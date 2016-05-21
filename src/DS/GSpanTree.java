package DS;

public class GSpanTree {
    private Graph graph;
    public GSpanTree(Graph graph)
    {
    	this.graph = graph;
    }
    
    private int minCost(double S[])
    {
    	int vertexNum = graph.vertexCount();
    	double mincost = Double.MAX_VALUE;
    	int min_v = -1;
    	for(int j = 0; j < vertexNum;j++)
    	{
    		if(S[j] < mincost && S[j] != 0)
    		{
    			mincost = S[j];
    			min_v = j;
    		}
    	}
    	return min_v;
    }
    
    public void prim(int v0,Graph tree)
    {
    	int vertexNum = graph.vertexCount();
    	int i,j,u = 0;
    	double[] S = new double[vertexNum];
    	int [] P = new int[vertexNum];
    	for (i = 0; i < vertexNum;i++)
    	{
    		S[i] = graph.getWeight(v0, i);
    		P[i] = v0;
    	}
    	S[v0] = 0;
    	P[v0] = -1;
    	
    	for(i = 1; i< vertexNum ;i++)
    	{
    		u = minCost(S);
    		if(u == -1)
    			break;
    		tree.addEdge(P[u], u, S[u]);
    		S[u] = 0;
    		for(j = 0; j< vertexNum;j++)
        	{
        		double w_uj = graph.getWeight(u, j);
        		if(w_uj < S[j])
        		{
        			S[j] = w_uj;
        			P[j] = u;
        		}
        	}
    	}
    	
//    	for(j = 0; j< vertexNum;j++)
//    	{
//    		double w_uj = graph.getWeight(u, j);
//    		if(w_uj < S[j])
//    		{
//    			S[j] = w_uj;
//    			P[j] = u;
//    		}
//    	}
    }
    
    public static void main(String[] args)
    {
    	String[] vex = {"A","B","C","D","E"};		
		GraphMatrix mg = new GraphMatrix(Graph.digraph, vex);
		GraphLink mg1 = new GraphLink(Graph.digraph, vex);
		mg.addEdge("A", "B", 5);
		mg.addEdge("A", "D", 2);
		mg.addEdge("B", "A", 5);
		mg.addEdge("B", "C", 4);
		mg.addEdge("C", "E", 9);
		mg.addEdge("E", "C", 7);
		mg.addEdge("D", "C", 7);
		mg.addEdge("D", "E", 9);
		mg.outputGraph();
		GSpanTree gst = new GSpanTree(mg);
		gst.prim(0,mg1);
		System.out.println(mg1.edge);
    }
}
