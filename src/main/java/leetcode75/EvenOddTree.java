package leetcode75;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class EvenOddTree {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    static class Node {
        int level;
        TreeNode treeNode;

        public Node(int level, TreeNode treeNode) {
            this.level = level;
            this.treeNode = treeNode;
        }
    }

    public boolean isEvenOddTree(TreeNode root) {
        boolean result = true;
        Map<Integer, Integer> map = new HashMap<>();

        Node rootNode = new Node(0, root);
        Queue<Node> queue = new LinkedList<>();
        queue.add(rootNode);

        if (rootNode.treeNode.val % 2 == 0) {
            return false;
        }

        while (result && !queue.isEmpty()) {
            Node peekedNode = queue.peek();

            if (peekedNode.treeNode.left != null) {
                Node leftNode = new Node(peekedNode.level + 1, peekedNode.treeNode.left);
                result = updateMap(leftNode, map);
                if (result) {
                    queue.add(leftNode);
                }
            }

            if (result && peekedNode.treeNode.right != null) {
                Node rightNode = new Node(peekedNode.level + 1, peekedNode.treeNode.right);
                result = updateMap(rightNode, map);
                if (result) {
                    queue.add(rightNode);
                }
            }

            queue.poll();
        }
        return result;
    }

    private boolean updateMap(Node node, Map<Integer, Integer> map) {
        boolean result = true;
        if (!map.containsKey(node.level)) {
            if (node.level % 2 == 0) {
                if (node.treeNode.val % 2 != 0) {
                    map.put(node.level, node.treeNode.val);
                } else {
                    result = false;
                }
            } else {
                if (node.treeNode.val % 2 == 0) {
                    map.put(node.level, node.treeNode.val);
                } else {
                    result = false;
                }
            }
        } else {
            if (node.level % 2 == 0) {
                if (node.treeNode.val % 2 != 0 && node.treeNode.val > map.get(node.level)) {
                    map.put(node.level, node.treeNode.val);
                } else {
                    result = false;
                }
            } else {
                if (node.treeNode.val % 2 == 0 && node.treeNode.val < map.get(node.level)) {
                    map.put(node.level, node.treeNode.val);
                } else {
                    result = false;
                }
            }
        }
        return result;
    }
}
