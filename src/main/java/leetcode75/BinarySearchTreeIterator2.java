package leetcode75;

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
