package microsoft.binarytree;

import java.util.Stack;

/**
Problem : 100
Given the roots of two binary trees p and q, write a function to check if they are the same or not.

Two binary trees are considered the same if they are structurally identical, and the nodes have the same value

Constraints:-

a.) The number of nodes in both trees is in the range [0, 100].
b.) -10^4 <= Node.val <= 10^4

Time Complexity = O(N), where N being number of nodes in the tree
Space Complexity = O(N), where N being number of nodes in the tree
 * */

public class SameTree {

    static class TreeNode {
        private int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null && q != null) {
            return false;
        } else if (p != null && q == null) {
            return false;
        } else {
            return processToCheckAreTreeSame(p, q);
        }
    }

    private boolean processToCheckAreTreeSame(TreeNode p, TreeNode q) {
        Node rootOne = new Node(p);
        Node rootTwo = new Node(q);

        Stack<Node> stackOne = new Stack<>();
        Stack<Node> stackTwo = new Stack<>();

        stackOne.push(rootOne);
        stackTwo.push(rootTwo);

        while (!stackOne.isEmpty() && !stackTwo.isEmpty()) {
            Node peekedNodeOne = stackOne.peek();
            Node peekedNodeTwo = stackTwo.peek();

            if (peekedNodeOne.treeNode != null && peekedNodeTwo.treeNode != null) {
                if (peekedNodeOne.treeNode.val != peekedNodeTwo.treeNode.val) {
                    break;
                } else {
                    checkStateAndPerformOperation(peekedNodeOne, peekedNodeTwo, stackOne, stackTwo);
                }
            } else if (peekedNodeOne.treeNode == null && peekedNodeTwo.treeNode != null) {
                break;
            } else if (peekedNodeOne.treeNode != null && peekedNodeTwo.treeNode == null) {
                break;
            } else {
                stackOne.pop();
                stackTwo.pop();
            }
        }

        return stackOne.isEmpty() && stackTwo.isEmpty();
    }

    private void checkStateAndPerformOperation(Node peekedNodeOne, Node peekedNodeTwo, Stack<Node> stackOne, Stack<Node> stackTwo) {
        if (!peekedNodeOne.isLeftChildExplored && !peekedNodeOne.isRightChildExplored && !peekedNodeTwo.isRightChildExplored && !peekedNodeTwo.isLeftChildExplored) {
            Node nodeOne = new Node(peekedNodeOne.treeNode.left);
            Node nodeTwo = new Node(peekedNodeTwo.treeNode.left);
            stackOne.push(nodeOne);
            stackTwo.push(nodeTwo);

            peekedNodeOne.isLeftChildExplored = true;
            peekedNodeTwo.isLeftChildExplored = true;
        } else if (peekedNodeOne.isLeftChildExplored && !peekedNodeOne.isRightChildExplored && !peekedNodeTwo.isRightChildExplored && peekedNodeTwo.isLeftChildExplored) {
            Node nodeOne = new Node(peekedNodeOne.treeNode.right);
            Node nodeTwo = new Node(peekedNodeTwo.treeNode.right);
            stackOne.push(nodeOne);
            stackTwo.push(nodeTwo);

            peekedNodeOne.isRightChildExplored = true;
            peekedNodeTwo.isRightChildExplored = true;
        } else if (peekedNodeOne.isLeftChildExplored && peekedNodeOne.isRightChildExplored && peekedNodeTwo.isRightChildExplored && peekedNodeTwo.isLeftChildExplored) {
            stackOne.pop();
            stackTwo.pop();
        }
    }

    static class Node {
        private TreeNode treeNode;
        boolean isLeftChildExplored;
        boolean isRightChildExplored;

        public Node(TreeNode treeNode) {
            this.treeNode = treeNode;
        }
    }
}
