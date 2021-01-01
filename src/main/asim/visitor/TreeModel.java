package asim.visitor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class TreeModel 
{
	private String nodeValues;
	private String nodeColors;
	private Map<Integer, Integer> nodeColorsList;
	private Map<Integer, Integer> nodeValuesByIdx;
	private Map<Integer, Integer> indexofNodeValues;	
	private Map<Integer, List<NodeElement>> nodesByFromEdge;
	private Map<Integer, List<NodeElement>> nodesByToEdge;

	private List<Edge> edges = new Vector<Edge>();
	private List<String> nodeEdges = new Vector<String>();	
	private List<Edge> markedEdges = new Vector<Edge>();


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

	public List<Edge> getNodeEdges() 
	{
		edges.clear();				
		for (int i=0; i<nodeEdges.size(); i++)
		{
			String nodeEdge = nodeEdges.get(i);
			String[] tokens = nodeEdge.split(" ");

			int fromEdge = Integer.valueOf(tokens[0]);
			int toEdge = Integer.valueOf(tokens[1]);

			edges.add(new Edge(fromEdge, toEdge));
		}
		return edges;
	}

	public Map<Integer, Integer> getNodeColors() 
	{
		nodeColorsList = new HashMap<Integer, Integer>();
		String[] temp = nodeColors.split(" ");

		for (int i=0; i<temp.length; i++) {
			nodeColorsList.put(i+1, Integer.valueOf(temp[i]));
		}
		return nodeColorsList;
	}

	public void setNodeValues(String nodeValues) 
	{
		this.nodeValues = nodeValues;
	}

	public void setNodeColors(String nodeColors) 
	{
		this.nodeColors = nodeColors;
	}

	public void setNodeEdges(String edge) 
	{
		nodeEdges.add(edge);
	}

	void getMappedNodeValues()
	{
		nodeValuesByIdx = new HashMap<Integer, Integer>();
		indexofNodeValues = new HashMap<Integer, Integer>();

		List<Integer> nodeValues = getNodeValues(); 

		for (int i=0; i<nodeValues.size(); i++) {
			Integer nodeValue = nodeValues.get(i);

			nodeValuesByIdx.put(i+1, nodeValue);
			indexofNodeValues.put(nodeValue, i+1);
		}
	}

	Map<Integer, List<NodeElement>> getNodeByFromEdge()
	{
		NodeElement nodeElement = null;
		nodesByFromEdge = new HashMap<Integer, List<NodeElement>>();

		for (int i=0; i< edges.size(); i++) {
			Edge edge = edges.get(i);

			Integer fromVtx = nodeValuesByIdx.get(edge.getFromEdge());
			Integer toVtx = nodeValuesByIdx.get(edge.getToEdge());

			nodeElement = new NodeElement();
			nodeElement.setFromEdge(fromVtx);
			nodeElement.setToEdge(toVtx);

			List<NodeElement> nodeElements = nodesByFromEdge.get(fromVtx);
			if (nodeElements == null)
			{
				nodeElements = new Vector<NodeElement>();
				nodeElements.add(nodeElement);
				nodesByFromEdge.put(fromVtx, nodeElements);
			}
			else
			{
				nodeElements.add(nodeElement);
			}	
		}//-for
		return nodesByFromEdge;
	}

	Map<Integer, List<NodeElement>> getNodeByToEdge()
	{
		NodeElement nodeElement = null;
		nodesByToEdge = new HashMap<Integer, List<NodeElement>>();

		for (int i=0; i<edges.size(); i++) {
			Edge edge = edges.get(i);

			Integer fromVtx = nodeValuesByIdx.get(edge.getFromEdge());
			Integer toVtx = nodeValuesByIdx.get(edge.getToEdge());

			List<NodeElement> nodeElements = nodesByToEdge.get(toVtx);

			nodeElement = new NodeElement();
			nodeElement.setFromEdge(fromVtx);
			nodeElement.setToEdge(toVtx);

			if (nodeElements == null)
			{
				nodeElements = new Vector<NodeElement>();
				nodeElements.add(nodeElement);
				nodesByToEdge.put(toVtx, nodeElements);
			}
			else
			{
				nodeElements.add(nodeElement);
			}	
		}//-for
		return nodesByToEdge;
	}

	public Node getRootNode() {

		markedEdges.clear();
		Node root = nodeFactory(); 

		getMappedNodeValues();
		getNodeEdges();		
		getNodeColors();
		getNodeByFromEdge();
		getNodeByToEdge();

		getTreeNode(0, root);
		return root;
	}

	public void getTreeNode(int vertexNum, Node root)
	{
		if (vertexNum >= edges.size()) return;

		if (vertexNum == 0) {
			Edge edge = edges.get(vertexNum);

			Integer nodeValFromEdge = nodeValuesByIdx.get(edge.getFromEdge());
			int colorFromIdx =  nodeColorsList.get(edge.getFromEdge());
			Color nodeColorFromEdge = colorFromIdx == 0 ? Color.RED : Color.GREEN;

			Integer nodeValToEdge = nodeValuesByIdx.get(edge.getToEdge());
			int colorToIdx =  nodeColorsList.get(edge.getToEdge());
			Color nodeColorToEdge = colorToIdx == 0 ? Color.RED : Color.GREEN;

			if (nodeValFromEdge != null) {
				root.setValue(nodeValFromEdge);
				root.setColor(nodeColorFromEdge);
				root.setParent(true);
			}
			if (nodeValToEdge != null) {
				Node cnode = nodeFactory();
				cnode.setValue(nodeValToEdge);
				cnode.setColor(nodeColorToEdge);
				cnode.setParent(false);
				root.getConnectedVertices().add(cnode);
				getSiblingsConnectedNode(cnode, cnode.getValue());
			}
			markedEdges.add(edge);
			++vertexNum;
		}

		Edge edge = edges.get(vertexNum);
		
		if (markedEdges.contains(edge)) {
			++vertexNum;
		}	
		else if (!markedEdges.contains(edge)) {

			if (edge.getFromEdge() == 4 && edge.getToEdge() == 67) {
				System.out.println("*");
			}
			markedEdges.add(edge);			
			addNode(root, edge);			
			++vertexNum;
		}
		
		getTreeNode(vertexNum,  root);
		return;
	}

	void addNode(Node rootNode, Edge edge)
	{
		Integer nodeValFromEdge = nodeValuesByIdx.get(edge.getFromEdge());
		int colorFromIdx =  nodeColorsList.get(edge.getFromEdge());
		Color nodeColorFromEdge = colorFromIdx == 0 ? Color.RED : Color.GREEN;

		Integer nodeValToEdge = nodeValuesByIdx.get(edge.getToEdge());
		int colorToIdx =  nodeColorsList.get(edge.getToEdge());
		Color nodeColorToEdge = colorToIdx == 0 ? Color.RED : Color.GREEN;

		Node root = null;
		boolean fromEdgeVertxInRootNode = false;
		List<Node> nodePlaceHolder = new Vector<>();
		getNodeReferenceFromRoot(rootNode, nodeValFromEdge, nodePlaceHolder);

		if (nodePlaceHolder.size() > 0) {
			root = nodePlaceHolder.get(0);
			fromEdgeVertxInRootNode = true;
		}
		else if (nodePlaceHolder.size() == 0) {
			root = nodeFactory();	
		}

		if (nodeValFromEdge != null && fromEdgeVertxInRootNode == false) {

			root.setValue(nodeValFromEdge);
			root.setColor(nodeColorFromEdge);
			root.setParent(true);
			rootNode.getConnectedVertices().add(root);
		}

		if (nodeValToEdge != null) {

			Node cnode = nodeFactory();
			cnode.setValue(nodeValToEdge);
			cnode.setColor(nodeColorToEdge);
			cnode.setParent(false);
			root.getConnectedVertices().add(cnode);
			getSiblingsConnectedNode(cnode, cnode.getValue());
		}

	}

	void getSiblingsConnectedNode(Node root, Integer nodeValue)
	{
		if (nodeValue != null) {

			List<NodeElement> nodeElements = nodesByFromEdge.get(nodeValue);
			if (nodeElements == null) { 
				return;
			}	

			for (NodeElement element : nodeElements) {
				
				Integer fromEdge = indexofNodeValues.get(element.getFromEdge());
				Integer toEdge = indexofNodeValues.get(element.getToEdge());
				Edge edge = new Edge(fromEdge, toEdge);
				
				if (!isEdgeMarked(element)) {

					Integer idxToEdge = indexofNodeValues.get(element.getToEdge());
					Integer toNodeValue = nodeValuesByIdx.get(idxToEdge);

					Integer toNodeColorVal = nodeColorsList.get(idxToEdge);
					Color toNodeColor = toNodeColorVal == 0 ? Color.RED : Color.GREEN;

					root.setParent(true);
					Node temp = nodeFactory();
					temp.setValue(toNodeValue);
					temp.setColor(toNodeColor);
					temp.setParent(false);

					root.getConnectedVertices().add(temp);
					markedEdges.add(edge);

					if (toNodeValue != null)
						getSiblingsConnectedNode(temp, toNodeValue);
				}
			}//-for
		}//-if
	}

	boolean isEdgeMarked(NodeElement nodeElement) {		
		Integer fromEdge = indexofNodeValues.get(nodeElement.getFromEdge());
		Integer toEdge = indexofNodeValues.get(nodeElement.getToEdge());

		Edge edge = new Edge(fromEdge, toEdge);
		return markedEdges.contains(edge);
	}
	
	boolean isEdgeMarked(Integer fromEdge, Integer toEdge) {
		Edge edge = new Edge(fromEdge, toEdge);
		return markedEdges.contains(edge);
	}


	void getNodeReferenceFromRoot(Node rootNode, Integer nodeValFromEdge, List<Node> nodePlaceHolder) {

		if (rootNode.getValue() == nodeValFromEdge) {
			nodePlaceHolder.add(rootNode);
			return;
		}

		if (rootNode.getConnectedVertices().size() > 0) {
			List<Node> children = rootNode.getConnectedVertices();

			for(Node temp : children) {
				getNodeReferenceFromRoot(temp, nodeValFromEdge, nodePlaceHolder);
			} 
		}
	}

	Node nodeFactory() {
		return new Node();
	}

}
