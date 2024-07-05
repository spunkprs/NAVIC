package binarytree;

/*
This problem aims at finding the diameter of binary tree which basically means the longest distance between any two nodes,
path against covering such distance may or may not pass through root node
* */
public class DiameterOfBinaryTree {

    static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;
    }

    private int diamterOfTree = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            processToFindDiameterOfBinaryTree(root);
            return diamterOfTree;
        }
    }

    private int processToFindDiameterOfBinaryTree(TreeNode node) {
        if (node.left == null && node.right == null) {
            return 0;
        } else {
            TreeNode lNode = node.left;
            TreeNode rNode = node.right;

            int diameterFromLeftSubTree = lNode != null ? processToFindDiameterOfBinaryTree(lNode) : 0;
            int diameterFromRightSubTree = rNode != null ? processToFindDiameterOfBinaryTree(rNode) : 0;
            int extraVerticesCount = fetchExtraVerticesCount(lNode, rNode);
            updateDiameterOfBinaryTree(diameterFromLeftSubTree + diameterFromRightSubTree + extraVerticesCount);
            return Math.max(diameterFromLeftSubTree, diameterFromRightSubTree) + 1;
        }
    }

    private void updateDiameterOfBinaryTree(int diameter) {
        diamterOfTree = diameter > diamterOfTree ? diameter : diamterOfTree;
    }

    private int fetchExtraVerticesCount(TreeNode lNode, TreeNode rNode) {
        if (lNode != null && rNode != null) {
            return 2;
        } else if (lNode != null || rNode != null) {
            return 1;
        } else {
            return 0;
        }
    }

}
