package microsoft.binarytree;


/**
Problem : 1038
Given the root of a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus the sum of all keys greater than the original key in BST.

As a reminder, a binary search tree is a tree that satisfies these constraints:-
a.) The left subtree of a node contains only nodes with keys less than the node's key.
b.) The right subtree of a node contains only nodes with keys greater than the node's key.
c.) Both the left and right subtrees must also be binary search trees.


Constraints:-
a.) The number of nodes in the tree is in the range [1, 100].
b.) 0 <= Node.val <= 100
c.) All the values in the tree are unique.
 * */

public class BinarySearchTreeToGreaterSumTree {

    public class TreeNode {
        private int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
    }

    public TreeNode bstToGst(TreeNode root) {
        return null;
    }

}
