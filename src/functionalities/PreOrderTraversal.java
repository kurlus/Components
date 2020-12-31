package functionalities;

import java.util.List;
import java.util.ArrayList;

import beans.*;

public class PreOrderTraversal {

    public List preorderTraversal(TreeNode root, List values) {
        if (root == null) return values;

        if (root != null)
            values.add(root.strVal);
        if (root.left != null)
            preorderTraversal(root.left, values);
        if (root.right != null)
            preorderTraversal(root.right, values);

        return values;
    }

    /* what's pre order traversal :
   1. visit the root or node
   2. visit the left tree
   3. visit the right tree
    // tree to similuate pre order traversal [Ref: alphabetTree]
                        A
                      B     E
                    C   D       F
                               G
    */
    public TreeNode makeAlphabetTree() {
        TreeNode aRootNode = new TreeNode("A");
        TreeNode bNode = new TreeNode("B");
        TreeNode cNode = new TreeNode("C");
        TreeNode dNode = new TreeNode("D");
        TreeNode eNode = new TreeNode("E");
        TreeNode fNode = new TreeNode("F");
        TreeNode gNode = new TreeNode("G");

        aRootNode.setLeft(bNode);
        aRootNode.setRight(eNode);
        bNode.setLeft(cNode);
        bNode.setRight(dNode);
        eNode.setRight(fNode);
        fNode.setLeft(gNode);

        return aRootNode;
    }

}
