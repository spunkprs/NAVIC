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

    public static void main(String ar[]) {
        BinarySearchTreeToGreaterSumTree unit = new BinarySearchTreeToGreaterSumTree();
        /*TreeNode root = new TreeNode(4);

        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(6);

        TreeNode three = new TreeNode(0);
        TreeNode four = new TreeNode(2);
        TreeNode five = new TreeNode(5);
        TreeNode six = new TreeNode(7);

        TreeNode seven = new TreeNode(3);
        TreeNode eight = new TreeNode(8);

        root.left = one;
        root.right = two;

        one.left = three;
        one.right = four;

        two.left = five;
        two.right = six;

        four.right = seven;
        six.right = eight;*/

        TreeNode root = new TreeNode(3);

        TreeNode one = new TreeNode(2);
        TreeNode two = new TreeNode(4);

        TreeNode three = new TreeNode(1);

        root.left = one;
        root.right = two;

        one.left = three;

        TreeNode greaterSumTreeRoot = unit.bstToGst(root);
        System.out.print(greaterSumTreeRoot);
    }

    static class TreeNode {
        private int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

    public TreeNode bstToGst(TreeNode root) {
        if (root.left == null && root.right == null) {
            return root;
        }
        processToConvertBstToGst(root, 0);
        return root;
    }

    private int processToConvertBstToGst(TreeNode node, int sumPassed) {
        TreeNode rightNode = node.right;
        TreeNode leftNode = node.left;

        int sumRecorded = 0;

        if (rightNode != null) {
            sumRecorded += processToConvertBstToGst(rightNode, sumPassed) + node.val;
            node.val = sumRecorded;
        } else if (leftNode != null && rightNode == null) {
            sumRecorded += sumPassed + node.val;
            node.val = sumRecorded;
        }

        if (leftNode != null) {
            sumRecorded = processToConvertBstToGst(leftNode, sumRecorded);
        }

        if (leftNode == null && rightNode == null) {
            node.val += sumPassed;
            sumRecorded = node.val;
        }
        return sumRecorded;
    }

}
