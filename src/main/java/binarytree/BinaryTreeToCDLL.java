package binarytree;

public class BinaryTreeToCDLL {


    private Node head;
    private Node tail;

    public Node bTreeToClist(Node root) {
        traverseAndUpdate(root);
        tail.right = head;
        head.left = tail;
        return head;
    }

    private void traverseAndUpdate(Node node) {
        Node leftNode = node.left;
        Node rightNode = node.right;

        if (leftNode != null) {
            traverseAndUpdate(leftNode);
        }

        if (head == null) {
            head = node;
            tail = node;
        } else {
            node.left = tail;
            tail.right = node;
            tail = tail.right;
        }

        if (rightNode != null) {
            traverseAndUpdate(rightNode);
        }
    }
}
