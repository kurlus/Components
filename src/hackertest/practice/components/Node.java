package hackertest.practice.components;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class Node 
{

	int data;
	Node next; 			// for linked list
	Node root, left, right = null;  // for BST work
	
	static List<Integer> lCount = new Vector<>();
	static List<Integer> rCount = new Vector<>();	

	public static void main (String[] args) throws Exception
	{
		Node root = null;

		String strCurrentLine;
		BufferedReader objReader = new BufferedReader(new FileReader("D:\\temp\\nodes-data.txt"));
		while ((strCurrentLine = objReader.readLine()) != null) {
			int data = Integer.valueOf(strCurrentLine.trim());
			root=insert(root,data);
		}
		int height = Node.getHeight(root);
		System.out.println(height);
	}

	Node(){}

	Node(int d)
	{
		data=d;
		next=null;
	}

	// BST method
	static Node insert (Node root, int data)
	{
		if (root==null)
		{
			return new Node(data);
		}
		else
		{
			Node cur;
			if (data <= root.data)
			{
				cur = insert(root.left, data);
				root.left = cur;
			}
			else
			{
				cur = insert(root.right, data);
				root.right = cur;
			}
			return root;
		}
	}

	// BST method
	// implementing depth-first search
	static int getHeight (Node root)
	{
		if (root == null) return 1;
		int ledges = 0, redges = 0, temp = 0;
		List<Integer> nodeData = new Vector<>();

		lCount.add(root.left.data);
		if (root.left != null) nodesData(root.left);
		nodeData.addAll(rCount);
		
		for (Iterator<Integer> i = nodeData.iterator(); i.hasNext();)
		{
			ledges = determineMaxEdgeRoute(root.left, lCount, rCount, i.next());
			if (ledges > temp)
				temp = ledges;
		}
		
		
		ledges = temp; temp = 0; nodeData.clear();
		lCount.add(root.right.data);
		if (root.right != null) nodesData(root.right);
		nodeData.addAll(rCount);
		
		for (Iterator<Integer> i = nodeData.iterator(); i.hasNext();)
		{
			redges = determineMaxEdgeRoute(root.right, lCount, rCount, i.next());
			if (redges > temp)
				temp = redges;
		}
		
		redges = temp;		
		int cardinality = Math.max(ledges, redges);
		return cardinality;
	}

	static void nodesData(Node node) {

		Node lNode = node.left != null ? node.left : null;
		Node rNode = node.right != null ? node.right : null;

		if ((lNode == null) && (rNode == null)) return;
		
		if (lNode != null) {
			lCount.add(lNode.data);
			nodesData(lNode);
		} 

		if (rNode != null) {
			rCount.add(rNode.data);
			nodesData(rNode);
		}
	}
	
	static int determineMaxEdgeRoute(Node root, List<Integer> lCount, List<Integer> rCount, Integer leafNodeData)
	{
		int maxHeight = 0;
		final int rootData = root != null ? root.data : -1;
		
		lCount.addAll(rCount);
		if ((rootData == -1) || (rootData == leafNodeData.intValue()))
			return ++maxHeight;
		
		Node parentNode = root;
		List<Node> parentNodes = new Vector<>();
		
		do
		{
			getParentNode(parentNode, leafNodeData, parentNodes);
			
			for (Node pNode : parentNodes)
			{
				// int size = parentNodes.size();				
				Node pLeft = pNode.left != null ? pNode.left : null;
				Node pRght = pNode.right != null ? pNode.right : null;
				
				if ((pLeft != null) && (pLeft.data == leafNodeData))
				{
					leafNodeData = pNode.data;
					break;	
				}
				else if (((pRght!= null) && (pRght.data == leafNodeData)))
				{
					leafNodeData = pNode.data;
					break;	
				}	
			}//for

			if (leafNodeData == rootData) break;
		}	
		while(true);
		
		maxHeight = 1 + parentNodes.size();
		lCount.clear(); rCount.clear();
		return maxHeight;
	}

	static void getParentNode(Node node, int data, List<Node> parentNodes)
	{
		Node lNode = node.left != null ? node.left : null;
		Node rNode = node.right != null ? node.right : null;
		
		if (lNode != null)
			getParentNode(lNode, data, parentNodes);
		if (rNode != null) 
			getParentNode(rNode, data, parentNodes);
		
		if ((node.left != null) && (node.left.data == data)) parentNodes.add(node);
		if ((node.right != null) && (node.right.data == data)) parentNodes.add(node); 		
	}


}
