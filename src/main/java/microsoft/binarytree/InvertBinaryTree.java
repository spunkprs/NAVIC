package microsoft.binarytree;

/**
Problem : 226
Given the root of a binary tree, invert the tree, and return its root

Constraints:-

a.) The number of nodes in the tree is in the range [0, 100].
b.) -100 <= Node.val <= 100

Time Complexity = O(N), where N being number of nodes in the tree
Space Complexity = O(K), where K being depth of the tree [No additional memory is utilised but it's because of call stack due to recursive approach]
 * */

public class InvertBinaryTree {

    static class TreeNode {
        private int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        } else if (root != null && root.left == null && root.right == null) {
            return root;
        } else {
            processToInvertTree(root);
            return root;
        }
    }

    private void processToInvertTree(TreeNode node) {
        TreeNode leftNode = node.left;
        TreeNode rightNode = node.right;

        if (leftNode != null && rightNode != null) {
            processToInvertTree(leftNode);
            processToInvertTree(rightNode);
            node.left = rightNode;
            node.right = leftNode;
        } else if (leftNode != null && rightNode == null) {
            processToInvertTree(leftNode);
            node.right = leftNode;
            node.left = null;
        } else if (leftNode == null && rightNode != null) {
            processToInvertTree(rightNode);
            node.right = null;
            node.left = rightNode;
        }
    }
}
