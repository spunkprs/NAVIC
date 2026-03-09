package microsoft.binarytree;

/**
Problem : 1973
Given the root of a binary tree, return the number of nodes where the value of the node is equal to the sum of the values of its descendants.

A descendant of a node x is any node that is on the path from node x to some leaf node. The sum is considered to be 0 if the node has no descendants.


Constraints:-
a.) The number of nodes in the tree is in the range [1, 10^5].
b.) 0 <= Node.val <= 10^5

Time Complexity = O(N), where N being number of nodes in the tree
Space Complexity = O(N), no external space used but space occupied will be because of recursive in memory stack
 * */

public class CountNodesEqualToSumOfDescendants {

    static class TreeNode {
        private int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

    private int count = 0;

    public int equalToDescendants(TreeNode root) {
        if (root.left == null && root.right == null) {
            if (root.val == 0) {
                count++;
            }
        } else {
            processToComputeCount(root);
        }
        return count;
    }

    private void processToComputeCount(TreeNode node) {
        process(node);
    }

    private int process(TreeNode node) {
        TreeNode leftNode = node.left;
        TreeNode rightNode = node.right;

        int leftSum = 0;
        int rightSum = 0;

        if (leftNode == null && rightNode == null) {
            if (node.val == 0) {
                count++;
            }
            return node.val;
        }

        if (leftNode != null) {
            leftSum = process(leftNode);
        }

        if (rightNode != null) {
            rightSum = process(rightNode);
        }

        if (node.val == leftSum + rightSum) {
            count++;
        }
        return node.val + leftSum + rightSum;
    }
}
