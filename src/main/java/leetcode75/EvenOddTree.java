package leetcode75;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;


/**
Problem : 1609
A binary tree is named Even-Odd if it meets the following conditions:

The root of the binary tree is at level index 0, its children are at level index 1, their children are at level index 2, etc.
For every even-indexed level, all nodes at the level have odd integer values in strictly increasing order (from left to right).
For every odd-indexed level, all nodes at the level have even integer values in strictly decreasing order (from left to right).

Given the root of a binary tree, return true if the binary tree is Even-Odd, otherwise return false.

Constraints:

a.) The number of nodes in the tree is in the range [1, 10^5].
b.) 1 <= Node.val <= 10^6

Time Complexity : O(N)
Space Complexity : Max number of nodes at any level

Where N being number of nodes in the tree

 * */

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
