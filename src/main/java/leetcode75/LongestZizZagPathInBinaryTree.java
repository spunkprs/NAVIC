package leetcode75;

/**
You are given the root of a binary tree.

A ZigZag path for a binary tree is defined as follow:

Choose any node in the binary tree and a direction (right or left).
If the current direction is right, move to the right child of the current node;
otherwise, move to the left child.

Change the direction from right to left or from left to right.
Repeat the second and third steps until you can't move in the tree.
Zigzag length is defined as the number of nodes visited - 1. (A single node has a length of 0).

Return the longest ZigZag path contained in that tree.


Constraints:-

a.) The number of nodes in the tree is in the range [1, 5 * pow(10,4)].
b.) 1 <= Node.val <= 100


 * */

public class LongestZizZagPathInBinaryTree {

    private int resultSum = 0;

    public int longestZigZag(TreeNode root) {

        processToFindLongestZigZagSum(root, 0, 0);
        return resultSum;
    }

    private void processToFindLongestZigZagSum(TreeNode node, int sum, int direction) {
        TreeNode leftNode = node.left;
        TreeNode rightNode = node.right;

        resultSum = sum > resultSum ? sum : resultSum;


        if (leftNode != null) {
            if (direction == 0 || direction == 2) {
                processToFindLongestZigZagSum(leftNode, sum + 1, 1);
            } else if (direction == 1) {
                processToFindLongestZigZagSum(leftNode, 1, 1);
            }
        }

        if (rightNode != null) {
            if (direction == 0 || direction == 1) {
                processToFindLongestZigZagSum(rightNode, sum + 1, 2);
            } else if (direction == 2) {
                processToFindLongestZigZagSum(rightNode, 1, 2);
            }
        }
    }

    static class TreeNode {
        int val;
        private TreeNode left;
        private TreeNode right;

        TreeNode(int val) { this.val = val; }
    }
}
