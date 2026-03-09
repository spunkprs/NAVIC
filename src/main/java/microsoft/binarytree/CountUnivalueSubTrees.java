package microsoft.binarytree;

/**
Problem : 250

Given the root of a binary tree, return the number of uni-value subtrees.

A uni-value subtree means all nodes of the subtree have the same value.

Constraints:-

a.) The number of the node in the tree will be in the range [0, 1000].
b.) -1000 <= Node.val <= 1000

Time Complexity = O(N), where N being number of nodes in the tree
Space Complexity = O(N), no external space used but space occupied will be because of recursive in memory stack
 * */

public class CountUnivalueSubTrees {

    private int result = 0;

    static class TreeNode {
        private int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            processToFetchUnivalueSubTrees(root);
        }
        return result;
    }

    private void processToFetchUnivalueSubTrees(TreeNode node) {
        process(node);
    }

    private boolean process(TreeNode node) {
        TreeNode leftNode = node.left;
        TreeNode rightNode = node.right;

        boolean intermediateResult = false;
        boolean intermediateResultLeft = false;
        boolean intermediateResultRight = false;

        if (leftNode != null) {
            intermediateResultLeft = process(leftNode);
        }

        if (rightNode != null) {
            intermediateResultRight = process(rightNode);
        }

        if (leftNode != null && rightNode != null) {
            if (intermediateResultLeft && intermediateResultRight && node.val == leftNode.val && node.val == rightNode.val) {
                result++;
                intermediateResult = true;
            }
        } else if (leftNode != null && rightNode == null) {
            if (intermediateResultLeft &&  node.val == leftNode.val) {
                result++;
                intermediateResult = true;
            }
        } else if (leftNode == null && rightNode != null) {
            if (intermediateResultRight &&  node.val == rightNode.val) {
                result++;
                intermediateResult = true;
            }
        } else {
            intermediateResult = true;
            result++;
        }
        return intermediateResult;
    }
}
