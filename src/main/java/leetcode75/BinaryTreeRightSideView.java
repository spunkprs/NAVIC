package leetcode75;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
Problem : 199

Given the root of a binary tree, imagine yourself standing on the right side of it,
return the values of the nodes you can see ordered from top to bottom

Constraints:-

a.) The number of nodes in the tree is in the range [0, 100].
b.) -100 <= Node.val <= 100

Time Complexity = O(n)
Space Complexity = O(n)

Source : LeetCode
Level : Medium
 * */

public class BinaryTreeRightSideView {

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList();

        if (root != null) {
            Map<Integer, Integer> map = processToComputeRightSideView(root);

            prepareResult(map, result);
        }
        return result;
    }

    private void prepareResult(Map<Integer, Integer> map, List<Integer> result) {
        int size = map.keySet().size();
        int level = 1;

        while (level <= size) {
            result.add(map.get(level));
            level++;
        }
    }

    private Map<Integer, Integer> processToComputeRightSideView(TreeNode node) {
        Map<Integer, Integer> map = new HashMap();

        process(node, map, 1);
        return map;
    }

    private void process(TreeNode node, Map<Integer, Integer> map, int level) {
        TreeNode rightNode = node.right;
        TreeNode leftNode = node.left;

        if (!map.containsKey(level)) {
            map.put(level, node.val);
        }

        if (rightNode != null) {
            process(rightNode, map, level + 1);
        }

        if (leftNode != null) {
            process(leftNode, map, level + 1);
        }
    }

    static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;

        TreeNode(int val) { this.val = val; }
    }
}
