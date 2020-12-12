package beans;

public class TreeNode
{
    public int val;
    public String strVal;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(){};
    public TreeNode(int val){this.val = val;};
    public TreeNode(String strVal){this.strVal = strVal;};

    public TreeNode(TreeNode left, TreeNode right)
    {
        this.left = left;
        this.right = right;
    };
    public TreeNode(int val, TreeNode left, TreeNode right)
    {
        this.val = val;
        this.left = left;
        this.right = right;
    };

    public TreeNode getLeft() {
        return left;
    }
    public void setLeft(TreeNode left) {
        this.left = left;
    }
    public TreeNode getRight() {
        return right;
    }
    public void setRight(TreeNode right) {
        this.right = right;
    }

}
