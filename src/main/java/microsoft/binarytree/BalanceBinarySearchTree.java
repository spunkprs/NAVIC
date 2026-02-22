package microsoft.binarytree;

import java.util.ArrayList;
import java.util.List;

/**
Given the root of a binary search tree, return a balanced binary search tree with the same node values. If there is more than one answer, return any of them.

A binary search tree is balanced if the depth of the two subtrees of every node never differs by more than 1

 Constraints:-

a.) The number of nodes in the tree is in the range [1, 10^4].
b.) 1 <= Node.val <= 10^5
 * */

public class BalanceBinarySearchTree {


    public static void main(String ar[]) {
        BalanceBinarySearchTree unit = new BalanceBinarySearchTree();

        TreeNode root = new TreeNode(1);

        TreeNode one = new TreeNode(2);
        TreeNode two = new TreeNode(3);

        TreeNode three = new TreeNode(4);

        root.right = one;
        one.right = two;

        two.right = three;

        TreeNode resultRoot = unit.balanceBST(root);
        System.out.println(resultRoot);
    }

    static class TreeNode {
        private int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

    public TreeNode balanceBST(TreeNode root) {
        if (root.left == null && root.right == null) {
            return root;
        } else {
            List<Integer> inorderTraversal = new ArrayList<>();
            prepareInorderTraversal(root, inorderTraversal);
            return prepareBalanceBstUsingTraversalData(inorderTraversal);
        }
    }

    private TreeNode prepareBalanceBstUsingTraversalData(List<Integer> inorderTraversal) {
        int size = inorderTraversal.size();
        TreeNode node = null;
        return processToPrepareBalancedBst(inorderTraversal, 0, size - 1, node);
    }

    private TreeNode processToPrepareBalancedBst(List<Integer> inorderTraversal, int startIndex, int endIndex, TreeNode parentNode) {
        int midIndex = (startIndex + endIndex) / 2;

        TreeNode node = new TreeNode(inorderTraversal.get(midIndex));

        if (parentNode != null) {
            if (node.val <= parentNode.val) {
                parentNode.left = node;
            } else if (node.val > parentNode.val) {
                parentNode.right = node;
            }
        }

        if (midIndex - 1 >= 0 && startIndex <= midIndex - 1) {
            processToPrepareBalancedBst(inorderTraversal, startIndex, midIndex - 1, node);
        }

        if (midIndex + 1 <= endIndex) {
            processToPrepareBalancedBst(inorderTraversal, midIndex + 1, endIndex, node);
        }

        return node;
    }

    private void prepareInorderTraversal(TreeNode node, List<Integer> inorderTraversal) {
        TreeNode leftNode = node.left;
        TreeNode rightNode = node.right;

        if (leftNode != null) {
            prepareInorderTraversal(leftNode, inorderTraversal);
        }

        inorderTraversal.add(node.val);

        if (rightNode != null) {
            prepareInorderTraversal(rightNode, inorderTraversal);
        }
    }
}
