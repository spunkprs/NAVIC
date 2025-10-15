package leetcode75;

import binarytree.TreeNode;

/**
 You are given the root of a binary tree containing digits from 0 to 9 only.

 Each root-to-leaf path in the tree represents a number.

 For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.
 Return the total sum of all root-to-leaf numbers. Test cases are generated
 so that the answer will fit in a 32-bit integer.

 A leaf node is a node with no children.


Constraints:-
1.) The number of nodes in the tree is in the range [1, 1000].
2.) 0 <= Node.val <= 9
3.) The depth of the tree will not exceed 10.

Time Complexity = O(n) --> Iterating every node in the tree
Space Complexity = O(n) --> Method stack will take space in proportion to the depth of the tree which
could be n in the worst case

Source : LeetCode
Level : Medium
 * */

public class SumRootToLeafNumbers {

    private int result = 0;

    public static void main(String ar[]) {

    }

    public int sumNumbers(TreeNode root) {
        processToSumNumbers(root);
        return result;
    }

    private void processToSumNumbers(TreeNode node) {
        String appendedString = String.valueOf(node.getVal());
        process(node, appendedString);
    }

    private void process(TreeNode node, String appendedString) {
        if (node.getLeft() == null && node.getRight() == null) {
            result += Integer.parseInt(appendedString);
        }

        if (node.getLeft() != null) {
            process(node.getLeft(), appendedString + node.getLeft().getVal());
        }

        if (node.getRight() != null) {
            process(node.getRight(), appendedString + node.getRight().getVal());
        }
    }
}
