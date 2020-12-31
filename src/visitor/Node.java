package visitor;

import java.util.List;
import java.util.Vector;

public class Node {

	Integer value;
	Integer depth;
	Color color;
	
	boolean isParent = false;
	List<Node> connectedVertices = new Vector<>();
	
	public Node(){};
	public Node(Integer value){
		this.value = value;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public Integer getDepth() {
		return depth;
	}
	public void setDepth(Integer depth) {
		this.depth = depth;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public boolean isParent() {
		return isParent;
	}
	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}
	public List<Node> getConnectedVertices() {
		return connectedVertices;
	}
	public void setConnectedVertices(List<Node> connectedVertices) {
		this.connectedVertices = connectedVertices;
	};

}
