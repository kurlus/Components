package asim.hackertest.tree;

import java.util.List;
import java.util.Vector;

enum Color {
RED, GREEN
}

public abstract class Tree
{
	private int value;
	private Color color;
	private int depth;
	
	private Tree left = null;
	private Tree right = null;	
	private boolean isLeaf = false;
	private boolean isRootNode = false;
	private boolean hasLeftNode = false;
	private boolean hasRightNode = false;
	private List<Edge> treeEdges = new Vector<Edge>();

	
	public Tree()
	{
	}
	
	public Tree(int value, Color color, int depth) 
	{
		this.value = value;
		this.color = color;
		this.depth = depth;
	}
	
	protected abstract void accept(TreeVis visitor);

	public int getValue() {
		return value;
	}

	public Color getColor() {
		return color;
	}

	public int getDepth() {
		return depth;
	}

	public Tree getLeft() {
		return left;
	}

	public void setLeft(Tree left) {
		this.left = left;
	}

	public Tree getRight() {
		return right;
	}

	public void setRight(Tree right) {
		this.right = right;
	}

	public boolean isLeaf() {
		return isLeaf;
	}

	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public boolean isRootNode() {
		return isRootNode;
	}

	public void setRootNode(boolean isRootNode) {
		this.isRootNode = isRootNode;
	}

	public List<Edge> getTreeEdges() {
		return treeEdges;
	}

	public void setTreeEdges(Edge treeEdge) {
		this.treeEdges.add(treeEdge);
	}
	
	public boolean hasLeftNode() {
		return left == null ? false : true;
	}

	public boolean hasRightNode() {
		return right == null ? false : true;
	}


}
