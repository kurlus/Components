package hackertest.practice.components;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class BreadFirstTraversal 
{

	static List<Vector<Integer>> lCount = new Vector<>();
	static List<Vector<Integer>> rCount = new Vector<>();

	public static void main(String[] args) throws Exception{
		BreadFirstTraversal bt = new BreadFirstTraversal();

		Node root = null;

		String strCurrentLine;
		BufferedReader objReader = new BufferedReader(new FileReader("D:\\temp\\nodes-data.txt"));
		while ((strCurrentLine = objReader.readLine()) != null) {
			int data = Integer.valueOf(strCurrentLine.trim());
			root=insert(root,data);
		}
		bt.levelOrder(root);
	}

	public static Node insert(Node root, int data) {
		if(root==null)
		{
			return new Node(data);
		}
		else
		{
			Node cur;
			if(data<=root.data)
			{
				cur=insert(root.left,data);
				root.left=cur;
			}
			else
			{
				cur=insert(root.right,data);
				root.right=cur;
			}
			return root;
		}
	}

	static void levelOrder(Node root)
	{
		int lcounter = 0; int rcounter = 0;
		lCount.clear(); rCount.clear();
		final Node rootNode = root;
		final Integer rootData = root.data;

		boolean lIteration = false;
		boolean rIteration = false;
		Vector<Integer> lnodes = null;
		Vector<Integer> rnodes = null;		
		List<Integer> mergedArray = new Vector<Integer>();

		mergedArray.add(new Integer(rootNode.data));
		nodesData(root.left, null, true);
		nodesData(root.right, null, false);
		System.out.println();
		
		do
		{
			
			if (lcounter < lCount.size())
			{	
				lnodes = lCount.get(lcounter);
				mergedArray.addAll(lnodes);
			}
			else
			{
				lIteration = true;
			}	
			if (rcounter < rCount.size()) {
				rnodes = rCount.get(rcounter);
				mergedArray.addAll(rnodes);
			}
			else
			{
				rIteration = true;
			}	
			
			lcounter = lcounter+1;
			rcounter = rcounter+1;
			
			if (lIteration && rIteration) break;
			
		}
		while (true);

		for (int i=0; i<mergedArray.size(); i++)
		  System.out.print(mergedArray.get(i)+" ");

	}

	static void nodesData(Node node, Vector<Integer> tempLCollection, boolean isLeftTree) {

		if (node == null) return;
		if (tempLCollection == null) tempLCollection = new Vector<Integer>();

		if (isLeftTree) {
			tempLCollection.add(node.data);
			lCount.add(tempLCollection);
		}
		if (!isLeftTree) {
			tempLCollection.add(node.data);
			rCount.add(tempLCollection);
		}


		List<Vector<Integer>> collection = new ArrayList<Vector<Integer>>();
		childrenNodesAtLevel(node.left, node.right, collection);
		Collections.reverse(collection);

		if ((collection.size() > 0) && (isLeftTree)) {
			for (Vector<Integer> lLevelBucket: collection)
			{
				if (lLevelBucket.size() > 0)
					lCount.add(lLevelBucket);
			}		
		}
		else if ((collection.size() > 0) && (!isLeftTree)) {
			for (Vector<Integer> lLevelBucket: collection)
			{
				if (lLevelBucket.size() > 0)
					rCount.add(lLevelBucket);
			}		
		}
	}

	static void childrenNodesAtLevel(Node lNode, Node rNode, List<Vector<Integer>> tempLCollection) {

		if ((lNode == null) && (rNode == null)) return;

		if (rNode != null) {
			childrenNodesAtLevel(rNode.left, rNode.right, tempLCollection);
		}	
		if (lNode != null) {
			childrenNodesAtLevel(lNode.left, lNode.right, tempLCollection);
		}	

		Vector<Integer> lCollection = new Vector<Integer>();

		Integer lNodeData = lNode != null ? lNode.data : null;
		Integer rNodeData = rNode != null ? rNode.data : null;

		if (lNodeData != null)
			lCollection.add(lNodeData);
		if (rNodeData != null)
			lCollection.add(rNodeData);

		if (lCollection.size() != 0)
			tempLCollection.add(lCollection);
	}


}
