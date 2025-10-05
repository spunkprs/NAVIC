package multithreading.dag;

public class ListNode {

    private Node node;
    private ListNode next;
    private ListNode previous;

    public ListNode(Node node) {
        this.node = node;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

    public ListNode getNext() {
        return next;
    }

    public Node getNode() {
        return node;
    }

    public ListNode getPrevious() {
        return previous;
    }

    public void setPrevious(ListNode previous) {
        this.previous = previous;
    }
}
