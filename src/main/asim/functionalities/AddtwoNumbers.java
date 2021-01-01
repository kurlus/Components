package asim.functionalities;

import java.util.Collections;
import java.util.LinkedList;

import java.util.stream.LongStream;
import java.util.function.LongBinaryOperator;

/*
You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order
and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.
Example:
        Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
        Output: 7 -> 0 -> 8
        Explanation: 342 + 465 = 807.
*/
public class AddtwoNumbers {
    public Node addTwoNumbers(Node l1, Node l2) {

        LongBinaryOperator bo = (x, y) -> Long.valueOf(String.valueOf(x).concat(String.valueOf(y)));

        LinkedList<Integer> elementsl1 = getNodeList(l1, new LinkedList<Integer>());
        Collections.reverse(elementsl1);
        LongStream longStreaml1 = elementsl1.stream().mapToLong(x -> x.longValue());

        LinkedList<Integer> elementsl2 = getNodeList(l2, new LinkedList<Integer>());
        Collections.reverse(elementsl2);
        LongStream longStreaml2 = elementsl2.stream().mapToLong(x -> x.longValue());

        long nodeOneInt = longStreaml1.reduce(0, bo);
        long nodeTwoInt = longStreaml2.reduce(0, bo);
        String sum = String.valueOf(nodeOneInt+nodeTwoInt);

        return getListNodeFromDtrValue(sum);
    }

    LinkedList<Integer> getNodeList(Node node, LinkedList<Integer> elements) {
        elements.add(node.val);
        if (node.next != null)
            getNodeList(node.next, elements);
        return elements;
    }

    Node getListNodeFromDtrValue(String sum){
        Node anode = null;
        Node node = new Node();

        anode = node;
        node.val = Integer.valueOf(String.valueOf(sum.charAt(sum.length()-1)));

        for (int i=sum.length()-2; i>=0; i--){
            Node newNode = new Node(Integer.valueOf(String.valueOf(sum.charAt(i))));
            anode.next = newNode;
            anode = newNode;

        }
        return node;
    }

}
