package leetcode75;

/**
Problem : 1586
 Implement the BSTIterator class that represents an iterator over the in-order traversal of a binary search tree (BST):

 BSTIterator(TreeNode root) Initializes an object of the BSTIterator class. The root of the BST is given as part of the constructor.
 The pointer should be initialized to a non-existent number smaller than any element in the BST.
 boolean hasNext() Returns true if there exists a number in the traversal to the right of the pointer,
 otherwise returns false.
 int next() Moves the pointer to the right, then returns the number at the pointer.
 boolean hasPrev() Returns true if there exists a number in the traversal to the left of the pointer,
 otherwise returns false.
 int prev() Moves the pointer to the left, then returns the number at the pointer.
 Notice that by initializing the pointer to a non-existent smallest number,
 the first call to next() will return the smallest element in the BST.

 You may assume that next() and prev() calls will always be valid. That is,
 there will be at least a next/previous number in the in-order traversal when next()/prev() is called

 Time Complexity : O(N)
 Space Complexity : O(N), where N being number of nodes in the tree

 * */

public class BinarySearchTreeIterator2 {

    private Node headNode = null;
    private Node tailNode = null;
    private Node currentPointer = null;
    private Node dummyNode = null;

    public boolean hasNext() {
        return currentPointer.next != null;
    }

    public int next() {
        currentPointer = currentPointer.next;
        return currentPointer.treeNode.val;
    }

    public boolean hasPrev() {
        return currentPointer.prev != null && currentPointer.prev != dummyNode;
    }

    public int prev() {
        currentPointer = currentPointer.prev;
        return currentPointer.treeNode.val;
    }

    public BinarySearchTreeIterator2(TreeNode root) {
        prepareDoublyLinkedList(root);
        Node node = new Node(new TreeNode(-1));
        node.next = headNode;
        headNode.prev = node;
        headNode = node;
        dummyNode = headNode;
        currentPointer = headNode;
    }

    private void prepareDoublyLinkedList(TreeNode node) {
        prepare(node);
    }

    private void prepare(TreeNode node) {
        TreeNode leftNode = node.left;
        TreeNode rightNode = node.right;

        if (leftNode != null) {
            prepare(leftNode);
        }

        addNodeToList(node);

        if (rightNode != null) {
            prepare(rightNode);
        }
    }

    private void addNodeToList(TreeNode treeNode) {
        Node node = new Node(treeNode);
        if (headNode == null) {
            headNode = node;
            tailNode = node;
        } else {
            tailNode.next = node;
            node.prev = tailNode;
            tailNode = tailNode.next;
        }
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

    static class Node {
        private TreeNode treeNode;
        private Node next;
        private Node prev;

        public Node(TreeNode treeNode) {
            this.treeNode = treeNode;
        }
    }
}
