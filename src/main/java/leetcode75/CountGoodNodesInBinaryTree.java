package leetcode75;

import java.util.TreeMap;

public class CountGoodNodesInBinaryTree {

    private int goodNodesCount = 0;

    public int goodNodes(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            processToFetchGoodNoes(root);
        }
        return goodNodesCount;
    }

    private void processToFetchGoodNoes(TreeNode node) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        process(node, treeMap);
    }

    private void process(TreeNode node, TreeMap<Integer, Integer> treeMap) {
        Integer higherKey = treeMap.higherKey(node.val);

        if (higherKey == null) {
            goodNodesCount++;
        }

        if (!treeMap.containsKey(node.val)) {
            treeMap.put(node.val, 1);
        } else {
            treeMap.put(node.val, treeMap.get(node.val) + 1);
        }

        TreeNode leftNode = node.left;
        TreeNode rightNode = node.right;

        if (leftNode != null) {
            process(leftNode, treeMap);
        }

        if (rightNode != null) {
            process(rightNode, treeMap);
        }

        if (treeMap.get(node.val) > 1) {
            treeMap.put(node.val, treeMap.get(node.val) - 1);
        } else {
            treeMap.remove(node.val);
        }

    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x) { val = x; }
    }
}
