package visitor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Solution 
{

	public static void main(String[] args) {

		Tree root = Solution.solve();

		 SumInLeavesVisitor vis1 = new SumInLeavesVisitor(); 
		 ProductOfRedNodesVisitor vis2 = new ProductOfRedNodesVisitor(); 
		 FancyVisitor vis3 = new FancyVisitor();

		  root.accept(vis1); 
		  root.accept(vis2); 
		  root.accept(vis3);

		  int res1 = vis1.getResult(); 
		  int res2 = vis2.getResult(); 
		  int res3 = vis3.getResult();
		  
		  System.out.println(res1); 
		  System.out.println(res2); 
		  System.out.println(res3);
	}

	public static Tree solve() 
	{
		//read the tree from STDIN and return its root as a return value of this function
		TreeNode treeNode = null;
		BufferedReader bufferedReader = null;

		TreeModel model = new TreeModel();

		try
		{
			int lineCount = 1;
			String line = null;

			/*
			 * bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			 */
			FileReader fileReader = new FileReader("D:\\temp\\tree-data.txt");
			bufferedReader = new BufferedReader(fileReader);

			while((line = bufferedReader.readLine()) != null) 
			{
				if (lineCount == 1)
					model.setNodeValues(line.trim());
				else if (lineCount == 2)
					model.setNodeColors(line);
				else
					model.setNodeEdges(line);

				++lineCount;
			}//-while

			List<Integer> nodeValues = model.getNodeValues();
			Map<Integer, Integer> nodeColors = model.getNodeColors();
			
			Node rootNode = model.getRootNode();
			//printNode (rootNode);			
			Color rootNodeColor = nodeColors.get(1).intValue() == 0 ? Color.RED : Color.GREEN;			
			treeNode = new TreeNode(nodeValues.get(0).intValue(), rootNodeColor, 0);

			buildTree(rootNode, treeNode, 0);
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

	static void buildTree(Node node, TreeNode treeNode, int treeLevel)
	{
		List<Node> childrenNodes = node.getConnectedVertices();

		if ((childrenNodes != null) && (childrenNodes.size() > 0)) {
			++treeLevel;
			for (Node cnode : childrenNodes){				
				buildTree(cnode, treeNode, treeLevel);
			}
		}
		if ((node == null) || (node.value == null)) return;		
		if (node.getConnectedVertices().size() == 0) {

			TreeLeaf leafNode = new TreeLeaf(node.value, node.getColor(), treeLevel);
			treeNode.addChild(leafNode); 
		} 
		else if (node.getConnectedVertices().size() > 0) {

			TreeNode pNode = new TreeNode(node.value, node.getColor(), treeLevel);
			treeNode.addChild(pNode); 
		}
		
		return;
	}

	static void printNode (Node rootNode) {
		System.out.println(rootNode.getValue());

		if (rootNode.getConnectedVertices().size() > 0) {
			List<Node> children = rootNode.getConnectedVertices();

			for(Node temp : children) {
				printNode(temp);
			} 
		}
	}

}
