package asim.visitor;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + fromEdge;
		result = prime * result + toEdge;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		if (fromEdge != other.fromEdge)
			return false;
		if (toEdge != other.toEdge)
			return false;
		return true;
	}


}
