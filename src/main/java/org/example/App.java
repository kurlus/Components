package org.example;

import beans.*;
import functionalities.*;

import java.util.ArrayList;
import java.util.List;

public class App
{
    public static void main( String[] args )
    {
        App app = new App();
        //app.executePreOrderTraversal();
        app.findDiameterBinaryTree();
    }

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

}
