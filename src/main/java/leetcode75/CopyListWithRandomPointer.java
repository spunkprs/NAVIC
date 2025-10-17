package leetcode75;

import java.util.HashMap;
import java.util.Map;

public class CopyListWithRandomPointer {

    public Node copyRandomList(Node head) {

        if (head == null) {
            return null;
        } else {
            Map<Node, Node> map = new HashMap();

            Node node = head;
            Node newListHead = null;
            Node newListTail = null;

            while (node != null) {
                if (newListHead == null) {
                    newListHead = new Node(node.val);
                    newListTail = newListHead;
                } else {
                    Node newListNode = new Node(node.val);
                    newListTail.next = newListNode;
                    newListTail = newListNode;
                }
                map.put(node, newListTail);
                node = node.next;
            }

            populateRandomPointers(head, newListHead, map);
            return newListHead;
        }
    }

    private void populateRandomPointers(Node headOne, Node headTwo, Map<Node, Node> map) {
        Node nodeOne = headOne;
        Node nodeTwo = headTwo;

        while (nodeOne != null) {
            Node randomNodeOne = nodeOne.random;
            if (randomNodeOne != null) {
                Node randomNodeTwo = map.get(randomNodeOne);
                nodeTwo.random = randomNodeTwo;
            }
            nodeOne = nodeOne.next;
            nodeTwo = nodeTwo.next;
        }
    }

    static class Node {
        private int val;
        private Node next;
        private Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
