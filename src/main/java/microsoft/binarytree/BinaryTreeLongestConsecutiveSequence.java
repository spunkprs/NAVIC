package microsoft.binarytree;


/**
Problem : 298
Link : https://leetcode.com/problems/binary-tree-longest-consecutive-sequence/description/

Given the root of a binary tree, return the length of the longest consecutive sequence path.

A consecutive sequence path is a path where the values increase by one along the path.

Note that the path can start at any node in the tree, and you cannot go from a node to its parent in the path.

Constraints:-

a.) The number of nodes in the tree is in the range [1, 3 * 10^4].
b.) -3 * 10^4 <= Node.val <= 3 * 10^4

Time Complexity = O(N), where N being number of nodes in the tree
Space Complexity = O(N) [Because of keeping stack calls as I have made use of DFS approach]
 * */

public class BinaryTreeLongestConsecutiveSequence {

    private int result = 0;

    public int longestConsecutive(TreeNode root) {
        if (root != null) {
            processToFindLongestConsecutiveSequence(root, 1);
        }
        return result;
    }

    private void processToFindLongestConsecutiveSequence(TreeNode node, int depth) {
        updateResult(depth);

        TreeNode leftNode = node.left;
        TreeNode rightNode = node.right;

        if (leftNode != null) {
            if (leftNode.val - node.val == 1) {
                processToFindLongestConsecutiveSequence(leftNode, depth + 1);
            } else {
                processToFindLongestConsecutiveSequence(leftNode, 1);
            }
        }

        if (rightNode != null) {
            if (rightNode.val - node.val == 1) {
                processToFindLongestConsecutiveSequence(rightNode, depth + 1);
            } else {
                processToFindLongestConsecutiveSequence(rightNode, 1);
            }
        }
    }

    private void updateResult(int depth) {
        result = depth > result ? depth : result;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }
}
