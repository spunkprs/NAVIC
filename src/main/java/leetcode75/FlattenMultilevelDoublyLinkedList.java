package leetcode75;

public class FlattenMultilevelDoublyLinkedList {

    private Node headNode;
    private Node tailNode;

    public Node flatten(Node head) {
        if (head != null) {
            processToFlatten(head);
            return headNode;
        }
        return head;
    }

    private void processToFlatten(Node node) {

        while (node != null) {
            if (headNode == null) {
                headNode = createNode(node);
                tailNode = headNode;
            } else {
                Node newNode = createNode(node);
                tailNode.next = newNode;
                newNode.prev = tailNode;
                tailNode = newNode;
            }

            if (node.child != null) {
                processToFlatten(node.child);
            }

            node = node.next;
        }
    }

    private Node createNode(Node node) {
        Node newNode = new Node();
        newNode.val = node.val;
        return newNode;
    }

    static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    }
}
