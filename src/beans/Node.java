package beans;

public class Node {

    public int val;
    public Node next;
    public String strVal;

    public Node() {}
    public Node(int val) {
        this.val = val;
    }
    public Node(String strVal) {
        this.strVal = strVal;
    }
    public Node(int val, Node next) {
        this.val = val;
        this.next = next;
    }

    public int getVal() {
        return val;
    }
    public void setVal(int val) {
        this.val = val;
    }
    public Node getNext() { return next; }
    public void setNext(Node next) {
        this.next = next;
    }
    public String getStrVal() { return strVal; }
    public void setStrVal(String strVal) { this.strVal = strVal; }

}
