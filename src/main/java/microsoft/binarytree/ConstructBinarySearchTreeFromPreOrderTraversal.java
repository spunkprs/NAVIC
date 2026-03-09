package microsoft.binarytree;

/**
Problem : 1008

Given an array of integers preorder, which represents the preorder traversal of a BST (i.e., binary search tree), construct the tree and return its root.

It is guaranteed that there is always possible to find a binary search tree with the given requirements for the given test cases.

A binary search tree is a binary tree where for every node, any descendant of Node.left has a value strictly less than Node.val, and any descendant of Node.right has a value strictly greater than Node.val.

A preorder traversal of a binary tree displays the value of the node first, then traverses Node.left, then traverses Node.right.


Constraints:-
a.) 1 <= preorder.length <= 100
b.) 1 <= preorder[i] <= 1000
c.) All the values of preorder are unique.
 * */

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
