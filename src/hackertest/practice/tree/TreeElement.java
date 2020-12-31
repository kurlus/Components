package hackertest.practice.tree;

public class TreeElement extends Tree 
{
	
	public TreeElement()
	{
		super();
	}
	
	public TreeElement(int value, Color color, int depth) 
	{
		super(value, color, depth);
	}

	@Override
	protected void accept(TreeVis visitor) 
	{
	}

}
