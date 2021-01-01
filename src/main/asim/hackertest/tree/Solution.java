package asim.hackertest.tree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Vector;


public class Solution {


	public static void main(String[] args)
	{
		Solution.solve();
	}

	public static Tree solve()
	{
		Tree treeNode = null;
		BufferedReader bufferedReader = null;

		TreeModel model = new TreeModel();
		List<Tree> treeNodesList = new Vector<Tree>();

		try
		{
			int lineCount = 1;
			String line = null;        	
			// read the tree from STDIN and return its root as a return value of this function    	
			FileReader fileReader = new FileReader("D:\\temp\\tree-data.txt");
			bufferedReader = new BufferedReader(fileReader);

			while((line = bufferedReader.readLine()) != null) 
			{
				if (lineCount == 1)
					model.setNodeValues(line);
				else if (lineCount == 2)
					model.setNodeColors(line);
				else
					model.setNodeEdges(line);

				++lineCount;
			}//-while

			treeNodesList.clear();
			setTreeElementValues(treeNodesList, model);
			setTreeElementColors(treeNodesList, model);
			setTreeElementEdges(treeNodesList, model);

			Tree leftRootNode = treeNodesList.get(0).getLeft();
			if (leftRootNode != null) leftRootNode.setDepth(1); 
			setTreeElementsDepth(treeNodesList.get(0).getLeft(), 1, 1);

			Tree rightRootNode = treeNodesList.get(0).getRight();
			if (rightRootNode != null) rightRootNode.setDepth(1);

			setTreeElementsDepth(treeNodesList.get(0).getRight(), 1, 1);			
			setRootLeafNodesFlag(treeNodesList);			

			treeNode = cloneTreeNode(treeNodesList.get(0));
			cloneTree(treeNodesList.get(0));
			System.out.println(treeNode+ " . Tree completed. ");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		finally
		{
			if (bufferedReader != null)
				try 
			{
					bufferedReader.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		return treeNode;
	}

	static void setTreeElementValues(List<Tree> treeNodesList, TreeModel model)
	{
		List<Integer> nodeValues = model.getNodeValues();

		for (int i=0; i<nodeValues.size(); i++)
		{
			TreeElement element = new TreeElement();
			element.setValue(nodeValues.get(i));

			if (i == 0) element.setRootNode(true);
			treeNodesList.add(element);
		}	
	}

	static void setTreeElementColors(List<Tree> treeNodesList, TreeModel model)
	{
		List<Integer> colors = model.getNodeColors();

		for (int i=0; i<colors.size(); i++)
		{
			Integer colorNumber = colors.get(i);
			Tree element = treeNodesList.get(i);

			if (colorNumber.intValue() == 0)
			{
				element.setColor(Color.RED);
			}	
			if (colorNumber.intValue() == 1)
			{
				element.setColor(Color.GREEN);
			}	
		}	

	}

	static void setTreeElementEdges(List<Tree> treeNodesList, TreeModel model)
	{
		List<Edge> edges = model.getNodeEdges();

		for (int i=0; i<edges.size(); i++)
		{
			Edge edge = edges.get(i);
			int fromEdge = edge.getFromEdge();
			int toEdge = edge.getToEdge();

			Tree element = treeNodesList.get(fromEdge-1);

			if (element.getLeft() == null)
				element.setLeft(treeNodesList.get(toEdge-1));
			else if (element.getRight() == null)
				element.setRight(treeNodesList.get(toEdge-1));

			element.setTreeEdges(edge);
		}	
	}

	static void setTreeElementsDepth(Tree node, int leftLevel, int rightLevel)
	{
		if (node == null) return;		
		if ((node.getLeft() == null) && (node.getRight() == null))
		{	
			if (node.getDepth() == 0) // either left or right tree
				node.setDepth(Math.max(leftLevel, rightLevel));
			return;
		}

		if (node.getLeft() != null)
		{
			node.getLeft().setDepth(++leftLevel);
			setTreeElementsDepth(node.getLeft(), leftLevel, rightLevel);
		}
		if (node.getRight() != null)
		{
			node.getRight().setDepth(++rightLevel);
			setTreeElementsDepth(node.getRight(), leftLevel, rightLevel);
		}	
	}

	static void setRootLeafNodesFlag(List<Tree> treeNodesList)
	{
		for(Tree element : treeNodesList)
		{
			if (element.isRootNode())
				element.setLeaf(false);
			else
			{
				element.setRootNode(false);
				List<Edge> edges = element.getTreeEdges();

				if (edges.size() == 0)
					element.setLeaf(true);
			}//-else	
		}//-for	
	}

	static Tree cloneTree(Tree elementNode)
	{
		Tree temp = elementNode;
		if (elementNode == null) return elementNode;		
		int edges = elementNode.getTreeEdges().size();

		if (edges == 0) {
			elementNode = cloneLeafNode(elementNode);
			elementNode.setLeft(temp.getLeft());
			elementNode.setRight(temp.getRight());
		}	
		else {
			elementNode = cloneTreeNode(elementNode);
			elementNode.setLeft(temp.getLeft());
			elementNode.setRight(temp.getRight());
		}

		List<Tree> parentRef = new Vector<>();
		parentRef.add(elementNode);

		cloneChildren(elementNode.getLeft(), elementNode, parentRef, true);
		cloneChildren(elementNode.getRight(), elementNode, parentRef, false);

		return elementNode;
	}

	static void cloneChildren(Tree treeNode, Tree prevNode, List<Tree> parent, boolean isLeft)
	{
		if ((treeNode.hasLeftNode() == false) && (treeNode.hasRightNode() == false)) { 
			Tree temp = null;
			int edges = treeNode.getTreeEdges().size();
			if (edges == 0)
				temp = cloneLeafNode(treeNode);
			else
				temp = cloneTreeNode(treeNode);

			if (isLeft) {
				temp.setLeft(treeNode.getLeft());
				temp.setRight(treeNode.getRight());
				prevNode.setLeft(temp);
			}	
			else {
				temp.setLeft(treeNode.getLeft());
				temp.setRight(treeNode.getRight());
				prevNode.setRight(temp);
			}	
			return;
		}

		if (treeNode!= null && isLeft) {
			Tree temp = null;
			int edges = treeNode.getTreeEdges().size();

			if (edges == 0)
				temp = cloneLeafNode(treeNode);
			else
				temp = cloneTreeNode(treeNode);

			temp.setLeft(treeNode.getLeft());
			temp.setRight(treeNode.getRight());
			prevNode.setLeft(temp);

			if (temp.getLeft() != null)
				cloneChildren(temp.getLeft(), temp, parent, true);
			if (temp.getRight() != null)
				cloneChildren(temp.getRight(), temp, parent, false);	
		}

		if (treeNode!= null && !isLeft) {
			Tree temp = null;
			int edges = treeNode.getTreeEdges().size();

			if (edges == 0)
				temp = cloneLeafNode(treeNode);
			else
				temp = cloneTreeNode(treeNode);

			temp.setLeft(treeNode.getLeft());
			temp.setRight(treeNode.getRight());
			prevNode.setRight(temp);

			if (temp.getRight() != null)
				cloneChildren(temp.getRight(), temp, parent, false);
			if (temp.getLeft() != null)
				cloneChildren(temp.getLeft(), temp, parent, true);
		}	

	}

	static Tree cloneTreeNode(Tree elementNode)
	{
		TreeNode treeNode = new TreeNode(elementNode.getValue(), elementNode.getColor(), elementNode.getDepth());
		treeNode.setLeaf(false);
		treeNode.setRootNode(elementNode.isRootNode());
		treeNode.setValue(elementNode.getValue());

		List<Edge> edges = treeNode.getTreeEdges();
		for (Edge edge: elementNode.getTreeEdges())
			edges.add(edge);

		return treeNode;
	}

	static Tree cloneLeafNode(Tree elementNode)
	{
		TreeLeaf treeLeaf = new TreeLeaf(elementNode.getValue(), elementNode.getColor(), elementNode.getDepth());
		treeLeaf.setLeaf(true);
		treeLeaf.setRootNode(elementNode.isRootNode());
		treeLeaf.setValue(elementNode.getValue());

		return treeLeaf;
	}


}
