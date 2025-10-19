package leetcode75;

/**
Given the root of a binary tree, flatten the tree into a "linked list":

The "linked list" should use the same TreeNode class where the right child pointer
points to the next node in the list and the left child pointer is always null.
The "linked list" should be in the same order as a pre-order traversal of the binary tree.

Constraints:-

a.) The number of nodes in the tree is in the range [0, 2000].
b.) -100 <= Node.val <= 100
c.) flatten the tree in-place (with O(1) extra space)


Level : Medium


Time Complexity = O(n) --> Iterating every node in the tree
Space Complexity = O(1) --> Not making use of any other external data structure

 * */

public class FlattenBinaryTreeToLinkedList {

    public static void main(String ar[]) {
        FlattenBinaryTreeToLinkedList unit = new FlattenBinaryTreeToLinkedList();

        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(5);
        TreeNode four = new TreeNode(3);
        TreeNode five = new TreeNode(4);
        TreeNode six = new TreeNode(6);

        one.left = two;
        one.right = three;

        two.left = four;
        two.right = five;

        three.right = six;

        unit.flatten(one);
    }

    public void flatten(TreeNode root) {
        if (root != null) {
            processToFlatten(root);
        }
        System.out.print("A");
    }

    private TreeNode processToFlatten(TreeNode node) {
        TreeNode leftNode = node.left;
        TreeNode rightNode = node.right;

        TreeNode leftResult = null;
        TreeNode rightResult = null;

        if (leftNode == null && rightNode == null) {
            return node;
        } else {
            if (rightNode != null) {
                rightResult = processToFlatten(rightNode);
            }

            if (leftNode != null) {
                leftResult = processToFlatten(leftNode);
            }
        }

        if (leftResult != null && rightResult != null) {
            TreeNode lastNode = fetchLastNode(leftResult);
            lastNode.right = rightResult;
            node.right = leftResult;
            node.left = null;
        } else if (leftResult != null && rightResult == null) {
            node.right = leftResult;
            node.left = null;
        } else if (leftResult == null && rightResult != null) {
            node.right = rightResult;
        }
        return node;
    }

    private TreeNode fetchLastNode(TreeNode node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;

        TreeNode(int val) { this.val = val; }
    }
}
