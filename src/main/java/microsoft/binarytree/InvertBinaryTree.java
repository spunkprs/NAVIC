package microsoft.binarytree;

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
