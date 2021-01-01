package asim.hackertest.tree;

public class Edge {
	
	private int fromEdge;
	private int toEdge;

	public Edge()
	{
	}
	
	public Edge(int fromEdge, int toEdge) 
	{
		super();
		this.fromEdge = fromEdge;
		this.toEdge = toEdge;
	}
	
	public int getFromEdge() 
	{
		return fromEdge;
	}
	
	public void setFromEdge(int fromEdge) 
	{
		this.fromEdge = fromEdge;
	}
	
	public int getToEdge() 
	{
		return toEdge;
	}
	
	public void setToEdge(int toEdge) 
	{
		this.toEdge = toEdge;
	}

}
