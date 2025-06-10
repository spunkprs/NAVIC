package binarytree.cousins;

/**
 Given the root of a binary tree, replace the value of each node in the tree with the sum of all its cousins' values.

 Two nodes of a binary tree are cousins if they have the same depth with different parents.

 Return the root of the modified tree.

 Note that the depth of a node is the number of edges in the path from the root node to it.

 Credits --> LeetCode
 * */

public class CousinsInBinaryTree2 {

    public TreeNode replaceValueInTree(TreeNode root) {
        return null;
    }

    static class TreeNode {
        private  int val;
        private CousinsInBinaryTree.TreeNode left;
        private CousinsInBinaryTree.TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }


    static class Node {
        int initialValue;
        boolean isLeftChild;
        int level;
        TreeNode treeNode;

        public Node(int initialValue, boolean isLeftChild, int level, TreeNode treeNode) {
            this.initialValue = initialValue;
            this.isLeftChild = isLeftChild;
            this.level = level;
            this.treeNode = treeNode;
        }
    }
}
