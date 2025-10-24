package leetcode75;

/**
Problem : 430

You are given a doubly linked list, which contains nodes that have a next pointer,
a previous pointer, and an additional child pointer. This child pointer may or
may not point to a separate doubly linked list, also containing these special nodes.
These child lists may have one or more children of their own, and so on,
to produce a multilevel data structure as shown in the example below.

Given the head of the first level of the list, flatten the list so that all the
nodes appear in a single-level, doubly linked list. Let curr be a node with a child list.
The nodes in the child list should appear after curr and before curr.next in the flattened list.

Return the head of the flattened list. The nodes in the list must have all of their
child pointers set to null


 * */

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
