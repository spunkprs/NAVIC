package leetcode75;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class MaximumLevelSumOfBinaryTree {

    public int maxLevelSum(TreeNode root) {

        Node startNode = new Node(root, 1);

        Queue<Node> queue = new LinkedList();

        queue.add(startNode);

        Map<Integer, Integer> map = new HashMap();

        while (!queue.isEmpty()) {
            Node peekedNode = queue.peek();

            if (peekedNode.treeNode.left != null) {
                queue.add(new Node(peekedNode.treeNode.left, peekedNode.level + 1));
            }

            if (peekedNode.treeNode.right != null) {
                queue.add(new Node(peekedNode.treeNode.right, peekedNode.level + 1));
            }

            peekedNode = queue.poll();
            updateMap(peekedNode, map);
        }

        return fetchResult(map);
    }

    private int fetchResult(Map<Integer, Integer> map) {
        int resultLevel = 0;
        int sum = Integer.MIN_VALUE;

        for (Integer level : map.keySet()) {
            int sumFetched = map.get(level);
            if (sumFetched > sum) {
                resultLevel = level;
                sum = sumFetched;
            }
        }
        return resultLevel;
    }

    private void updateMap(Node node, Map<Integer, Integer> map) {
        if (!map.containsKey(node.level)) {
            map.put(node.level, node.treeNode.val);
        } else {
            map.put(node.level, map.get(node.level) + node.treeNode.val);
        }
    }

    static class Node {
        private TreeNode treeNode;
        private int level;

        private Node(TreeNode treeNode, int level) {
            this.treeNode = treeNode;
            this.level = level;
        }
    }

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
}
