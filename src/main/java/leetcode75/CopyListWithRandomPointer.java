package leetcode75;

import java.util.HashMap;
import java.util.Map;

/**
 * A linked list of length n is given such that each node contains an additional random pointer,
 * which could point to any node in the list, or null.
 *
 * Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes,
 * where each new node has its value set to the value of its corresponding original node.
 * Both the next and random pointer of the new nodes should point to new nodes in the
 * copied list such that the pointers in the original list and copied list represent the
 * same list state. None of the pointers in the new list should point to nodes in the original list.
 *
 * For example, if there are two nodes X and Y in the original list, where X.random --> Y,
 * then for the corresponding two nodes x and y in the copied list, x.random --> y.
 *
 * Return the head of the copied linked list.
 *
 * The linked list is represented in the input/output as a list of n nodes.
 * Each node is represented as a pair of [val, random_index] where:

a.) val: an integer representing Node.val
b.) random_index: the index of the node (range from 0 to n-1) that the random pointer points to,
or null if it does not point to any node.
c.) Your code will only be given the head of the original linked list.

Constraints:-

a.) 0 <= n <= 1000
b.) -pow(10,4) <= Node.val <= pow(10,4)
c.) Node.random is null or is pointing to some node in the linked list.

Level : Medium


Time Complexity = O(n) --> Iterating every node in the list
Space Complexity = O(n) --> Maintaining mapping from oldNode address to newNode address
 * */

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
