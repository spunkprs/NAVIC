package microsoft.binarytree;

public class ConstructBinarySearchTreeFromPreOrderTraversal {

    static class TreeNode {
        private int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

    public TreeNode bstFromPreorder(int[] preorder) {
        TreeNode root = null;

        for (int i = 0; i < preorder.length; i++) {
            if (i == 0) {
                root = new TreeNode(preorder[i]);
            } else {
                findPositionForInsert(root, preorder[i]);
            }
        }
        return root;
    }

    /**
    Time Complexity = O(N^2), where N being number of nodes in the tree
     * */
    private void findPositionForInsert(TreeNode root, int num) {
        insertNode(root, num);
    }

    private void insertNode(TreeNode node, int num) {
        if (num < node.val) {
            if (node.left == null) {
                TreeNode newNode = new TreeNode(num);
                node.left = newNode;
            } else {
                insertNode(node.left, num);
            }
        } else if (num > node.val) {
            if (node.right == null) {
                TreeNode newNode = new TreeNode(num);
                node.right = newNode;
            } else {
                insertNode(node.right, num);
            }
        }
    }
}
