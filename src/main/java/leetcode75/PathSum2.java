package leetcode75;

import java.util.ArrayList;
import java.util.List;

/**
Problem : 113
Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths where
the sum of the node values in the path equals targetSum. Each path should be returned as a
list of the node values, not node references.

A root-to-leaf path is a path starting from the root and ending at any leaf node.
A leaf is a node with no children

Constraints:

a.) The number of nodes in the tree is in the range [0, 5000].
b.) -1000 <= Node.val <= 1000
c.) -1000 <= targetSum <= 1000
 * */

public class PathSum2 {

    private Node headNode;
    private Node tailNode;
    private List<List<Integer>> result = new ArrayList<>();

    static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (root != null) {
            processToComputePathSum(root, targetSum, root.val);
        }
        return result;
    }

    private void processToComputePathSum(TreeNode node, int targetSum, int computedSum) {
        TreeNode leftNode = node.left;
        TreeNode rightNode = node.right;

        if (headNode == null) {
            headNode = new Node(node.val);
            tailNode = headNode;
        } else {
            Node listNode = new Node(node.val);
            tailNode.nextNode = listNode;
            listNode.previousNode = tailNode;
            tailNode = tailNode.nextNode;
        }

        if (leftNode != null) {
            computedSum = computedSum + leftNode.val;
            processToComputePathSum(leftNode, targetSum, computedSum);
            if (leftNode.val >= 0) {
                computedSum -= leftNode.val;
            } else {
                computedSum += Math.abs(leftNode.val);
            }
            tailNode = tailNode.previousNode;
            tailNode.nextNode = null;
        }

        if (rightNode != null) {
            computedSum = computedSum + rightNode.val;
            processToComputePathSum(rightNode, targetSum, computedSum);
            if (rightNode.val >= 0) {
                computedSum -= rightNode.val;
            } else {
                computedSum += Math.abs(rightNode.val);
            }
            tailNode = tailNode.previousNode;
            tailNode.nextNode = null;
        }

        if (leftNode == null && rightNode == null) {
            if (targetSum == computedSum) {
                updateFinalResult();
            }
        }
    }

    private void updateFinalResult() {
        List<Integer> intermittentResult = new ArrayList<>();
        Node tempNode = headNode;

        while (tempNode != null) {
            intermittentResult.add(tempNode.num);
            tempNode = tempNode.nextNode;
        }
        result.add(intermittentResult);
    }

    static class Node {
        private int num;
        private Node nextNode;
        private Node previousNode;

        public Node(int num) {
            this.num = num;
        }
    }

}
