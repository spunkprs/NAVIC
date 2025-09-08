package binarytree;

/**
 In a sum tree, the value of each non-leaf node is equal to the sum of its left and right subtrees.
 A leaf node may have any value, while an empty child node has a value of 0

 Source : InterviewBit
 * */

public class BinaryTreeSumTree {

    private boolean shallContinue = true;

    public static void main(String ar[]) {
        BinaryTreeSumTree unit = new BinaryTreeSumTree();

        Node root = new Node(20);
        Node nodeOne = new Node(15);
        Node nodeTwo = new Node(5);
        Node nodeThree = new Node(9);
        Node nodeFour = new Node(6);
        Node nodeFive = new Node(5);

        root.leftNode = nodeOne;
        root.rightNode = nodeTwo;

        nodeOne.leftNode = nodeThree;
        nodeOne.rightNode = nodeFour;

        nodeTwo.rightNode = nodeFive;

        System.out.println("Is the provided tree a sum tree " + unit.isBinaryTreeSumTree(root));
    }

    public boolean isBinaryTreeSumTree(Node root) {
        if (root == null) {
            return false;
        } else {
            processToCheckWhetherBinaryTreeIsSumTree(root);
        }
        return shallContinue;
    }

    private int processToCheckWhetherBinaryTreeIsSumTree(Node node) {
        Node leftNode = node.leftNode;
        Node rightNode = node.rightNode;

        if (shallContinue) {
            if (leftNode == null && rightNode == null) {
                return node.value;
            } else {
                int leftValue = 0;
                int rightValue = 0;

                if (leftNode != null) {
                    leftValue = processToCheckWhetherBinaryTreeIsSumTree(leftNode);
                }

                if (rightNode != null) {
                    rightValue = processToCheckWhetherBinaryTreeIsSumTree(rightNode);
                }

                if (node.value != leftValue + rightValue) {
                    shallContinue = false;
                }
                return node.value;
            }
        }
        return 0;
    }

    static class Node {
        private int value;
        private Node leftNode;
        private Node rightNode;

        public Node(int value) {
            this.value = value;
        }
    }
}
