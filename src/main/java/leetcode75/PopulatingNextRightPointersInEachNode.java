package leetcode75;


/**
 Given a binary tree

 struct Node {
 int val;
 Node *left;
 Node *right;
 Node *next;
 }
 Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

 Initially, all next pointers are set to NULL.


 * */

public class PopulatingNextRightPointersInEachNode {

    public static void main(String ar[]) {
        PopulatingNextRightPointersInEachNode unit = new PopulatingNextRightPointersInEachNode();

        Node one = new Node(1);
        Node two = new Node(2);
        Node three = new Node(3);
        Node four = new Node(4);
        Node five = new Node(5);
        Node six = new Node(7);

        one.left = two;
        one.right = three;

        two.left = four;
        two.right = five;

        three.right = six;

        unit.connect(one);
    }

    public Node connect(Node root) {

        if (root != null) {
            processToConnectNodes(root);
        }
        return root;
    }

    private void processToConnectNodes(Node node) {
        Node leftNode = node;

        while (leftNode != null) {
            processToSetPointersForEachParent(leftNode);
            processToSetPointersOfNodesInDifferentSubTrees(leftNode);
            leftNode = fetchNextLeftNode(leftNode);
        }
    }

    private void processToSetPointersForEachParent(Node node) {
        while (node != null) {
            Node leftNode = node.left;
            Node rightNode = node.right;

            if (leftNode != null && rightNode != null) {
                leftNode.next = rightNode;
            }
            node = node.next;
        }
    }

    private void processToSetPointersOfNodesInDifferentSubTrees(Node node) {
        Node tOne = node;
        Node tTwo = node.next;

        while (tOne != null && tTwo != null) {
            if ((tOne.left == null && tOne.right == null) && (tTwo.left != null || tTwo.right != null)) {
                tOne = tTwo;
                tTwo = tTwo.next;
            } else if ((tOne.left != null || tOne.right != null) && (tTwo.left == null && tTwo.right == null)) {
                tTwo = tTwo.next;
            } else if ((tOne.left == null && tOne.right == null) && (tTwo.left == null && tTwo.right == null)) {
                tOne = tTwo;
                tTwo = tTwo.next;
            } else if ((tOne.left != null || tOne.right != null) && (tTwo.left != null || tTwo.right != null)) {
                Node leftNode = tOne.right != null ? tOne.right : tOne.left;
                Node rightNode = tTwo.left != null ? tTwo.left : tTwo.right;
                leftNode.next = rightNode;
                tOne = tTwo;
                tTwo = tTwo.next;
            }
        }
    }

    private Node fetchNextLeftNode(Node node) {
        return node.left != null ? node.left : node.right != null ? node.right : fetchEligibleNextNode(node.next);
    }

    private Node fetchEligibleNextNode(Node node) {
        Node toBeReturnedNode = null;
        while (node != null) {
            if (node.left != null) {
                toBeReturnedNode = node.left;
                break;
            } else if (node.right != null) {
                toBeReturnedNode = node.right;
                break;
            }
            node = node.next;
        }
        return toBeReturnedNode;
    }


    static class Node {

        public Node(int val) {
            this.val = val;
        }

        private int val;
        private Node left;
        private Node right;
        private Node next;
    }
}
