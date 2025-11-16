package leetcode75;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
Problem : 437

Given the root of a binary tree and an integer targetSum, return the number of paths where the sum of the values along the path equals targetSum.

The path does not need to start or end at the root or a leaf, but it must go downwards (i.e., traveling only from parent nodes to child nodes)

Constraints:-

a.) The number of nodes in the tree is in the range [0, 1000].
b.) -10^9 <= Node.val <= 10^9
c.) -1000 <= targetSum <= 1000
 * */

public class PathSum3 {

    public static void main(String ar[]) {
        PathSum3 unit = new PathSum3();

        TreeNode root = new TreeNode(0);
        TreeNode childOne = new TreeNode(1);
        TreeNode childTwo = new TreeNode(1);

        root.left = childOne;
        root.right = childTwo;
        int pathSum = 1;

        unit.pathSum(root, pathSum);
    }

    private int count = 0;

    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        } else {
            Map<BigInteger, Integer> map = new HashMap();
            processToComputePathSum(root, new BigInteger(String.valueOf(targetSum)), map, new BigInteger(String.valueOf(root.val)));
            return count;
        }
    }

    private void processToComputePathSum(TreeNode node, BigInteger targetSum, Map<BigInteger, Integer> map, BigInteger sum) {
        if (sum.equals(targetSum)) {
            if (map.containsKey(sum.subtract(targetSum))) {
                count += map.get(sum.subtract(targetSum));
            }
            if (!map.containsKey(sum)) {
                map.put(sum, 1);
            } else {
                map.put(sum, map.get(sum) + 1);
            }
            count++;
        } else {
            if (map.containsKey(sum.subtract(targetSum))) {
                count += map.get(sum.subtract(targetSum));
            }

            if (!map.containsKey(sum)) {
                map.put(sum, 1);
            } else {
                map.put(sum, map.get(sum) + 1);
            }
        }
        TreeNode leftNode = node.left;
        TreeNode rightNode = node.right;

        if (leftNode != null) {
            processToComputePathSum(leftNode, targetSum, map, sum.add(new BigInteger(String.valueOf(leftNode.val))));
        }

        if (rightNode != null) {
            processToComputePathSum(rightNode, targetSum, map, sum.add(new BigInteger(String.valueOf(rightNode.val))));
        }

        if (map.containsKey(sum)) {
            int val = map.get(sum);
            if (val == 1) {
                map.remove(sum);
            } else {
                map.put(sum, map.get(sum) - 1);
            }
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
