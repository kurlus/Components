import org.junit.Test;
import static org.junit.Assert.assertTrue;

import asim.functionalities.LongestSubStrWithoutRepeatingChars;

public class AppTest
{

    @Test
    public void shouldAnswerWithTrue()
    {
        AppTest app = new AppTest();
        //app.executePreOrderTraversal();
        // app.findDiameterBinaryTree();
        app.LongestSubStrWithoutRepeatingChars();
        //assertTrue( true );
    }

    /*
    void findDiameterBinaryTree(){
        BinaryTree binaryTree = new BinaryTree();
        TreeNode rootNode = binaryTree.createDummyBinaryTree();

        int treeDiameter = binaryTree.diameterOfBinaryTree(rootNode);
        System.out.println(String.valueOf(treeDiameter));
    }

    void executeBinaryTreeFunctionalities(){
        BinaryTree binaryTree = new BinaryTree();

        TreeNode rootNode = binaryTree.createDummyBinaryTree();
        List traversedList = binaryTree.traverseInOrderBinaryTree(rootNode, new ArrayList());
        System.out.println(traversedList);
    }

    void executePreOrderTraversal(){
        PreOrderTraversal poTraversal = new PreOrderTraversal();
        TreeNode rootNode = poTraversal.makeAlphabetTree();
        List traversedList = poTraversal.preorderTraversal(rootNode, new ArrayList());
        System.out.println(traversedList);
    }

    void executeZigZagPattern(){
        ZigZagPattern pattern = new ZigZagPattern();
        pattern.convert("PAYPALISHIRING", 3);
    }

    void executeAddtwoNumbers(){
        Node nodeOne = new Node(2);
        nodeOne.next = new Node(4);
        nodeOne.next.next = new Node(3);

        Node nodeTwo = new Node(5);
        nodeTwo.next = new Node(6);
        nodeTwo.next.next = new Node(4);

        AddtwoNumbers addNumbers = new AddtwoNumbers();
        addNumbers.addTwoNumbers(nodeOne, nodeTwo);

    }
    */
    void LongestSubStrWithoutRepeatingChars(){
        LongestSubStrWithoutRepeatingChars longestSubStrring = new LongestSubStrWithoutRepeatingChars();
        int ans = longestSubStrring.maxLngthSubStrWithoutRepChars("Its fancy world. Seems a fact");
        System.out.println(ans);
    }

}
