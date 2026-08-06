package microsoft.dfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
Problem : 366
Given the root of a binary tree, collect a tree's nodes as if you were doing this:

Collect all the leaf nodes.
Remove all the leaf nodes.
Repeat until the tree is empty.

Constraints:-
a.) The number of nodes in the tree is in the range [1, 100].
b.) -100 <= Node.val <= 100

Level : Medium
Link : https://leetcode.com/problems/find-leaves-of-binary-tree/description/?envType=problem-list-v2&envId=depth-first-search


 * */

public class LeavesOfBinaryTree {

    public static void main(String ar[]) {
        LeavesOfBinaryTree unit = new LeavesOfBinaryTree();

        TreeNode root = new TreeNode(1);
        TreeNode nodeOne = new TreeNode(2);
        TreeNode nodeTwo = new TreeNode(3);
        TreeNode nodeThree = new TreeNode(4);
        TreeNode nodeFour = new TreeNode(5);
        TreeNode nodeFive = new TreeNode(6);
        TreeNode nodeSix = new TreeNode(7);

        root.left = nodeOne;
        root.right = nodeTwo;

        nodeOne.left = nodeThree;
        nodeOne.right = nodeFour;

        nodeTwo.left = nodeFive;
        nodeTwo.right = nodeSix;

        List<List<Integer>> finalResult = unit.findLeaves(root);
        System.out.print(finalResult);

    }

    private List<List<Integer>> result = new ArrayList<>();
    private List<Integer> intermittentResult = new ArrayList<>();
    private Set<TreeNode> visitedNodes = new HashSet<>();

    private TreeNode rootNode = null;

    static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

    public List<List<Integer>> findLeaves(TreeNode root) {
        rootNode = root;

        while (rootNode != null) {
            processToFetchLeaves(rootNode);
            result.add(intermittentResult);
            intermittentResult = new ArrayList<>();
        }
        return result;
    }

    private void processToFetchLeaves(TreeNode node) {
        TreeNode leftNode = node.left;
        TreeNode rightNode = node.right;

        if (leftNode != null && !visitedNodes.contains(leftNode)) {
            processToFetchLeaves(leftNode);
        } else if (leftNode != null && visitedNodes.contains(leftNode)) {
            leftNode = null;
            node.left = null;
        }

        if (rightNode != null && !visitedNodes.contains(rightNode)) {
            processToFetchLeaves(rightNode);
        } else if (rightNode != null && visitedNodes.contains(rightNode)) {
            rightNode = null;
            node.right = null;
        }

        if (leftNode == null && rightNode == null && !visitedNodes.contains(node)) {
            intermittentResult.add(node.val);
            visitedNodes.add(node);
            if (node == rootNode) {
                rootNode = null;
            }
        }
    }
}
