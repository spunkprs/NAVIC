package leetcode75;

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
