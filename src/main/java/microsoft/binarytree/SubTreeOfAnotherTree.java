package microsoft.binarytree;

import java.util.Stack;

/**
Given the roots of two binary trees root and subRoot, return true if there is a subtree of root with the same structure and node values of subRoot and false otherwise.

A subtree of a binary tree tree is a tree that consists of a node in tree and all of this node's descendants. The tree tree could also be considered as a subtree of itself

Constraints:-

a.) The number of nodes in the root tree is in the range [1, 2000].
b.) The number of nodes in the subRoot tree is in the range [1, 1000].
c.) -10^4 <= root.val <= 10^4
d.) -10^4 <= subRoot.val <= 10^4
 * */

public class SubTreeOfAnotherTree {

    public static void main(String ar[]) {
        SubTreeOfAnotherTree unit = new SubTreeOfAnotherTree();
        /*TreeNode rootTreeOne = new TreeNode(3);
        TreeNode nodeOneTreeOne = new TreeNode(4);
        TreeNode nodeTwoTreeOne = new TreeNode(5);
        TreeNode nodeThreeTreeOne = new TreeNode(1);
        TreeNode nodeFourTreeOne = new TreeNode(2);
        TreeNode nodeFiveTreeOne = new TreeNode(2);

        rootTreeOne.left = nodeOneTreeOne;
        rootTreeOne.right = nodeTwoTreeOne;

        nodeOneTreeOne.left = nodeThreeTreeOne;
        nodeOneTreeOne.right = nodeFourTreeOne;
        nodeFourTreeOne.left = nodeFiveTreeOne;

        TreeNode rootTreeTwo = new TreeNode(4);
        TreeNode nodeOneTreeTwo = new TreeNode(1);
        TreeNode nodeTwoTreeTwo = new TreeNode(2);

        rootTreeTwo.left = nodeOneTreeTwo;
        rootTreeTwo.right = nodeTwoTreeTwo;*/

        TreeNode rootTreeOne = new TreeNode(1);
        TreeNode nodeOneTreeOne = new TreeNode(1);

        rootTreeOne.left = nodeOneTreeOne;

        TreeNode rootTreeTwo = new TreeNode(1);

        System.out.print("Is one tree subtree of another " + unit.isSubtree(rootTreeOne, rootTreeTwo));
    }

    static class TreeNode {
        private int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        Node nodeRoot = new Node(root);
        Node nodeSubRoot = new Node(subRoot);

        Stack<Node> stackOne = new Stack<>();
        Stack<Node> stackTwo = new Stack<>();

        stackOne.push(nodeRoot);
        stackTwo.push(nodeSubRoot);

        while (!stackOne.isEmpty()) {
            Node peekedNodeOne = stackOne.peek();
            Node peekedNodeTwo = null;

            if (!stackTwo.isEmpty()) {
                peekedNodeTwo = stackTwo.peek();
            } else {
                break;
            }

            if (nodesSame(peekedNodeOne, peekedNodeTwo)) {
                checkStateAndPerformOperationOne(peekedNodeOne, peekedNodeTwo, stackOne, stackTwo);
            } else {
                checkStateAndPerformOperationTwo(peekedNodeOne, stackOne);
                stackTwo = new Stack<>();
                nodeSubRoot = new Node(subRoot);
                stackTwo.push(nodeSubRoot);
            }
        }

        //processToCheckIsSubTree(root, subRoot);
        return stackTwo.isEmpty();
    }

    private void checkStateAndPerformOperationOne(Node peekedNodeOne, Node peekedNodeTwo, Stack<Node> stackOne, Stack<Node> stackTwo) {
        if (peekedNodeOne.treeNode == null && peekedNodeTwo.treeNode == null) {
            stackOne.pop();
            stackTwo.pop();
        } else {
            if (!peekedNodeOne.isLeftChildExplored && !peekedNodeOne.isRightChildExplored && !peekedNodeTwo.isLeftChildExplored && !peekedNodeTwo.isRightChildExplored) {
                TreeNode treeNodeOne = peekedNodeOne.treeNode;
                Node nodeOne = new Node(treeNodeOne.left);
                stackOne.push(nodeOne);
                peekedNodeOne.isLeftChildExplored = true;

                TreeNode treeNodeTwo = peekedNodeTwo.treeNode;
                Node nodeTwo = new Node(treeNodeTwo.left);
                stackTwo.push(nodeTwo);
                peekedNodeTwo.isLeftChildExplored = true;

            } else if (peekedNodeOne.isLeftChildExplored && !peekedNodeOne.isRightChildExplored && peekedNodeTwo.isLeftChildExplored && !peekedNodeTwo.isRightChildExplored) {
                TreeNode treeNodeOne = peekedNodeOne.treeNode;
                Node nodeOne = new Node(treeNodeOne.right);
                stackOne.push(nodeOne);
                peekedNodeOne.isRightChildExplored = true;

                TreeNode treeNodeTwo = peekedNodeTwo.treeNode;
                Node nodeTwo = new Node(treeNodeTwo.right);
                stackTwo.push(nodeTwo);
                peekedNodeTwo.isRightChildExplored = true;
            } else {
                stackOne.pop();
                stackTwo.pop();
            }
        }
    }

    private void checkStateAndPerformOperationTwo(Node peekedNodeOne, Stack<Node> stackOne) {
        if (peekedNodeOne.treeNode == null) {
            stackOne.pop();
        } else {
            if (!peekedNodeOne.isLeftChildExplored && !peekedNodeOne.isRightChildExplored) {
                TreeNode treeNodeOne = peekedNodeOne.treeNode;
                Node nodeOne = new Node(treeNodeOne.left);
                stackOne.push(nodeOne);
                peekedNodeOne.isLeftChildExplored = true;
            } else if (peekedNodeOne.isLeftChildExplored && !peekedNodeOne.isRightChildExplored) {
                TreeNode treeNodeOne = peekedNodeOne.treeNode;
                Node nodeOne = new Node(treeNodeOne.right);
                stackOne.push(nodeOne);
                peekedNodeOne.isRightChildExplored = true;
            } else {
                stackOne.pop();
            }
        }
    }

    private boolean nodesSame(Node peekedNodeOne, Node peekedNodeTwo) {
        if (peekedNodeOne.treeNode != null && peekedNodeTwo.treeNode != null) {
            if (peekedNodeOne.treeNode.val == peekedNodeTwo.treeNode.val && peekedNodeOne.isLeftChildExplored == peekedNodeTwo.isLeftChildExplored
                    && peekedNodeOne.isRightChildExplored == peekedNodeTwo.isRightChildExplored) {
                return true;
            }
        } else if (peekedNodeOne.treeNode == null && peekedNodeTwo.treeNode == null) {
                return true;
            }
        return false;
    }

    static class Node {
        TreeNode treeNode;
        boolean isLeftChildExplored;
        boolean isRightChildExplored;

        public Node(TreeNode treeNode) {
            this.treeNode = treeNode;
        }
    }
}
