package functionalities;

import beans.*;
import java.util.List;

public class BinaryTree
{
    /*
    A binary tree is a recursive data structure where each node can have
    2 children atmost.
    A  common type of binary tree is a binary serach tree in which every
    node has a value that is greater than or equal to node values in left
    sub-tree and less thanor equal to the node values in the right sub tree.

    Implemnting -> In order traversal ::
        1. visit the left tree
        2. visit the root or node
        3. visit the right tree
     */
    public List traverseInOrderBinaryTree(TreeNode treeNode, List data){
        if (treeNode == null) return data;

        if (treeNode.left != null)
            traverseInOrderBinaryTree(treeNode.left, data);
        if (treeNode != null)
            data.add(treeNode.val);
        if (treeNode.right != null)
            traverseInOrderBinaryTree(treeNode.right, data);

        return data;
    }

    public int diameterOfBinaryTree(TreeNode rootNode){
        longestPath(rootNode);
        return longestPath;
    }

    private int longestPath(TreeNode node){
        if (node == null) return 0;

        int leftHeight = longestPath(node.left);
        int rightHeight = longestPath(node.right);

        longestPath = Math.max(longestPath, leftHeight+rightHeight);
        return Math.max(leftHeight, rightHeight)+1;
    }
    /*
                    6
             4          8
          3     5    7      9
     */
    public TreeNode createDummyBinaryTree(){
        TreeNode rootNode = new TreeNode(6);
        TreeNode nodeValue4 = new TreeNode(4);
        TreeNode nodeValue3 = new TreeNode(3);
        TreeNode nodeValue5 = new TreeNode(5);
        TreeNode nodeValue8 = new TreeNode(8);
        TreeNode nodeValue7 = new TreeNode(7);
        TreeNode nodeValue9 = new TreeNode(9);

        rootNode.setLeft(nodeValue4);
        rootNode.setRight(nodeValue8);
        nodeValue4.setLeft(nodeValue3);
        nodeValue4.setRight(nodeValue5);
        nodeValue8.setLeft(nodeValue7);
        nodeValue8.setRight(nodeValue9);

        return rootNode;
    }

    private int longestPath = 0;
}
