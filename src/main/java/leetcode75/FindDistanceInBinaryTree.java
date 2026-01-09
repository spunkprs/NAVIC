package leetcode75;

/**
Problem : 1740
Given the root of a binary tree and two integers p and q, return the distance between the nodes of value p and value q in the tree.

The distance between two nodes is the number of edges on the path from one to the other

Constraints:-

a.) The number of nodes in the tree is in the range [1, 10^4].
b.) 0 <= Node.val <= 10^9
c.) All Node.val are unique
d.) p and q are values in the tree
 * */

public class FindDistanceInBinaryTree {

    public static void main(String ar[]) {
        FindDistanceInBinaryTree unit = new FindDistanceInBinaryTree();
        TreeNode root = new TreeNode(3);
        TreeNode nodeOne = new TreeNode(5);
        TreeNode nodeTwo = new TreeNode(1);
        TreeNode nodeThree = new TreeNode(6);
        TreeNode nodeFour = new TreeNode(2);
        TreeNode nodeFive = new TreeNode(0);
        TreeNode nodeSix = new TreeNode(8);
        TreeNode nodeSeven = new TreeNode(7);
        TreeNode nodeEight = new TreeNode(4);

        root.left = nodeOne;
        root.right = nodeTwo;

        nodeOne.left = nodeThree;
        nodeOne.right = nodeFour;

        nodeTwo.left = nodeFive;
        nodeTwo.right = nodeSix;

        nodeFour.left = nodeSeven;
        nodeFour.right = nodeEight;

        int firstNode = 5;
        int secondNode = 0;

        System.out.print("Distance between node " + firstNode + " and second node " + secondNode + " is " + unit.findDistance(root, firstNode, secondNode));
    }

    private Node headOne;
    private Node tailOne;
    private Node headTwo;
    private Node tailTwo;
    private boolean shallContinue = true;

    static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

    public int findDistance(TreeNode root, int p, int q) {
        processToGeneratePathOne(root, p);
        shallContinue = true;
        processToGeneratePathTwo(root, q);
        return findDistancePostFindingLCA();
    }

    private int findDistancePostFindingLCA() {
        Node tempNodeOne = headOne;
        Node tempNodeTwo = headTwo;

        while (tempNodeOne != null && tempNodeTwo != null && tempNodeOne.num == tempNodeTwo.num) {
            tempNodeOne = tempNodeOne.nextNode;
            tempNodeTwo = tempNodeTwo.nextNode;
        }

        Node lcaOne = null;
        Node lcaTwo = null;

        if (tempNodeOne != null) {
            lcaOne = tempNodeOne.prevNode;
        }

        if (tempNodeTwo != null) {
            lcaTwo = tempNodeTwo.prevNode;
        }

        int distanceOne = 0;
        int distanceTwo = 0;

        while (lcaOne != null && lcaOne.num != tailOne.num) {
            distanceOne++;
            lcaOne = lcaOne.nextNode;
        }

        while (lcaTwo != null && lcaTwo.num != tailTwo.num) {
            distanceTwo++;
            lcaTwo = lcaTwo.nextNode;
        }

        return distanceOne + distanceTwo;
    }

    private void processToGeneratePathOne(TreeNode root, int num) {
        processOne(root, num);
    }

    private void processToGeneratePathTwo(TreeNode root, int num) {
        processTwo(root, num);
    }

    private void processOne(TreeNode node, int num) {
        if (headOne == null) {
            headOne = new Node(node.val);
            tailOne = headOne;
        } else {
            Node listNode = new Node(node.val);
            tailOne.nextNode = listNode;
            listNode.prevNode = tailOne;
            tailOne = tailOne.nextNode;
        }

        if (node.val != num) {
            TreeNode leftNode = node.left;
            TreeNode rightNode = node.right;

            if (leftNode != null) {
                processOne(leftNode, num);
                if (shallContinue) {
                    tailOne = tailOne.prevNode;
                    tailOne.nextNode = null;
                }
            }

            if (rightNode != null && shallContinue) {
                processOne(rightNode, num);
                if (shallContinue) {
                    tailOne = tailOne.prevNode;
                    tailOne.nextNode = null;
                }
            }
        } else {
            shallContinue = false;
        }
    }

    private void processTwo(TreeNode node, int num) {
        if (headTwo == null) {
            headTwo = new Node(node.val);
            tailTwo = headTwo;
        } else {
            Node listNode = new Node(node.val);
            tailTwo.nextNode = listNode;
            listNode.prevNode = tailTwo;
            tailTwo = tailTwo.nextNode;
        }

        if (node.val != num) {
            TreeNode leftNode = node.left;
            TreeNode rightNode = node.right;

            if (leftNode != null) {
                processTwo(leftNode, num);
                if (shallContinue) {
                    tailTwo = tailTwo.prevNode;
                    tailTwo.nextNode = null;
                }
            }

            if (rightNode != null && shallContinue) {
                processTwo(rightNode, num);
                if (shallContinue) {
                    tailTwo = tailTwo.prevNode;
                    tailTwo.nextNode = null;
                }
            }
        } else {
            shallContinue = false;
        }
    }

    static class Node {
        private int num;
        private Node nextNode;
        private Node prevNode;

        public Node(int num) {
            this.num = num;
        }
    }

}
