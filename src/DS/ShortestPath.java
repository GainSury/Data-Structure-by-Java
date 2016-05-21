package DS;

import java.util.Arrays;

public class ShortestPath {
    private Graph graph;
    double D[];
    int P[];
    public ShortestPath(Graph graph)
    {
    	this.graph = graph;
    	D = new double[graph.vertexCount()];
    	P = new int[graph.vertexCount()];
    }
    
    private int FindMin(double D[],int U[])
    {
    	int u = -1;
    	int vexNum  = graph.vertexCount();
    	double MinValue = Double.MAX_VALUE;
    	for (int w = 0; w < vexNum; w++) {
			if (U[w]  ==0 && (D[w]<MinValue))
			{
				u = w;
				MinValue = D[w];
			}
		}
    	return u;
    }
    
    public void dijstra(int v0)
    {
    	int vexNum = graph.vertexCount();
    	int U[] = new int[vexNum];
    	
    	for(int i = 0; i < vexNum ;i++)
    	{
    		U[i] = 0;
    		P[i] = -1;
    		D[i] = graph.getWeight(v0, i);
    		if(D[i] < Double.MAX_VALUE)
    			P[i] = v0;
    	}
    	
    	for(int i = 0;i < vexNum;i++)
    	{
    		if(i == v0)
    			continue;
    		int u = FindMin(D,U);
    		if( u == -1)
    			break;
    		U[u] = 1;
    		
    		for(int j =0; j< vexNum;j++)
    		{
    			if(U[j] ==0 && D[j] > D[u] + graph.getWeight(u, j)){
    				D[j] = D[u] + graph.getWeight(u, j);
    				P[j] = u;
    			}
    		}
    			
    	}
    }
    
    public static void main(String[] args)
    {
    	String[] vex = {"0","1","2","3","4","5","6"};		
		
		GraphLink mg = new GraphLink (Graph.digraph, vex);
		mg.addEdge("0","1",11);
		mg.addEdge("0","2",8);
		mg.addEdge("0","4",30);
		mg.addEdge("0","6",32);
		mg.addEdge("1","5",9);
		mg.addEdge("1","6",15);
		mg.addEdge("2","3",5);
		mg.addEdge("2","5",15);
		mg.addEdge("3","4",6);
		mg.addEdge("4","5",2);
		mg.addEdge("5","6",4);
		
		mg.outputGraph();
		
		ShortestPath gs = new ShortestPath(mg);
		gs.dijstra(0);
		
		System.out.println(Arrays.toString(gs.D));
		System.out.println(Arrays.toString(gs.P));
		
    }
}
