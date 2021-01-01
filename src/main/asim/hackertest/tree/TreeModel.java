package asim.hackertest.tree;

import java.util.List;
import java.util.Vector;

public class TreeModel 
{
	private String nodeValues;
	private String nodeColors;
	private List<Edge> edges = new Vector<Edge>();
	private List<String> nodeEdges = new Vector<String>();

	public List<Integer> getNodeValues() 
	{
		List<Integer> values = null; 
		if (nodeValues != null)
		{
			String[] temp = nodeValues.split(" ");
			values = new Vector<>();

			for (int i=0; i<temp.length; i++)
				values.add(Integer.valueOf(temp[i]));
		}	
		return values;
	}

	public void setNodeValues(String nodeValues) 
	{
		this.nodeValues = nodeValues;
	}

	public List<Integer> getNodeColors() 
	{
		List<Integer> values = null; 
		if (nodeColors != null)
		{
			String[] temp = nodeColors.split(" ");
			values = new Vector<>();

			for (int i=0; i<temp.length; i++)
				values.add(Integer.valueOf(temp[i]));
		}	
		return values;
	}

	public void setNodeColors(String nodeColors) 
	{
		this.nodeColors = nodeColors;
	}

	public List<Edge> getNodeEdges() 
	{
		edges.clear();				
		for (int i=0; i<nodeEdges.size(); i++)
		{
			String nodeEdge = nodeEdges.get(i);
			String[] tokens = nodeEdge.split(" ");

			edges.add(new Edge(Integer.valueOf(tokens[0]), Integer.valueOf(tokens[1])));
		}
		return edges;
	}

	public void setNodeEdges(String edge) 
	{
		nodeEdges.add(edge);
	}


}
