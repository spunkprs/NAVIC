package binarytree;

public class Node {

    int data;
    Node left,right;
    public Node(int d) {
        data=d;
        left=right=null;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
