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

Level : Medium


 Constraints:-

a.) The number of nodes in the tree is in the range [0, 6000].
b.) -100 <= Node.val <= 100
c.) You may only use constant extra space.
d.) The recursive approach is fine. You may assume implicit stack space
does not count as extra space for this problem

Time Complexity = O(n) --> Iterating every node in the tree
Space Complexity = O(1) --> With the constraint put in place didn't make use of explicit
Queue for doing BFS traversal, did solve the problem using iterative approach and
it was possible because post population of next pointer we can go to next sub tree

Solution is applicable for binary tree of any type {be it perfect or non perfect}

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

    /**
    Structuring the problem and breaking down it in parts made it easy to implement
     * */

    private void processToConnectNodes(Node node) {
        Node leftNode = node;

        while (leftNode != null) {
            processToSetPointersForEachParent(leftNode); // 1
            processToSetPointersOfNodesInDifferentSubTrees(leftNode); // 2
            leftNode = fetchNextLeftNode(leftNode); // 3
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
