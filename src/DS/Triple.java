package DS;

public class Triple implements Comparable<Triple>{

	public int i,j;
	public double value;
	public Triple(int i,int j,double e)
	{
		this.i = i;
		this.j = j;
		this.value = e;
	}
	
	public Triple(Triple o)
	{
		this(o.i,o.j,o.value);
	}
	
	public String toString()
	{
		return  "<"+ i +"," + j +"," +value+">";
	}
	
	public boolean equals(Triple e)
	{
		return (this.i == e.i && this .j == e.j);
	}
	
	@Override
	public int compareTo(Triple e) {
		// TODO Auto-generated method stub
		if (this.i == e.i && this .j == e.j)
			return 0;
		else if (i < e.i || (i == e.i && j < e.j))
			return -1;
		else 
			return 1;
	}

}
