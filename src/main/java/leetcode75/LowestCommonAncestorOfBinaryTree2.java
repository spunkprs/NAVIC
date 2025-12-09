package leetcode75;

import java.util.Stack;

/**
Problem : 1644
Given the root of a binary tree, return the lowest common ancestor (LCA) of two given nodes, p and q.
If either node p or q does not exist in the tree, return null. All values of the nodes in the tree are unique.

According to the definition of LCA on Wikipedia: "The lowest common ancestor of two nodes p and q in a
binary tree T is the lowest node that has both p and q as descendants (where we allow a node to be a descendant of itself)".
A descendant of a node x is a node y that is on the path from node x to some leaf node


Constraints:-
a.) The number of nodes in the tree is in the range [1, 10^4].
b.) -10^9 <= Node.val <= 10^9
c.) All Node.val are unique.
d.) p != q


Time Complexity : O(N)
Space Complexity : O(N), where N being number of nodes in the tree
 * */

public class LowestCommonAncestorOfBinaryTree2 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Stack<TreeNode> stackOne = new Stack<>();
        Stack<TreeNode> stackTwo = new Stack<>();

        performPreorderTraversal(root, p, stackOne);
        performPreorderTraversal(root, q, stackTwo);

        TreeNode result = null;

        if (stackOne.size() == 0 || stackTwo.size() == 0) {
            return null;
        } else {
            if (stackOne.size() == stackTwo.size()) {
                while (stackOne.peek() != stackTwo.peek()) {
                    stackOne.pop();
                    stackTwo.pop();
                }
                result = stackOne.peek();
            } else if (stackOne.size() < stackTwo.size()) {
                result = findResult(stackOne, stackTwo);
            } else {
                result = findResult(stackTwo, stackOne);
            }
        }
        return result;
    }

    private TreeNode findResult(Stack<TreeNode> stackOne, Stack<TreeNode> stackTwo) {
        while (stackTwo.peek() != stackOne.peek() && stackTwo.size() != stackOne.size()) {
            stackTwo.pop();
        }

        while (stackOne.peek() != stackTwo.peek()) {
            stackOne.pop();
            stackTwo.pop();
        }
        return stackOne.peek();
    }

    private void performPreorderTraversal(TreeNode node, TreeNode nodeToBeLookedFor, Stack<TreeNode> stack) {
            stack.push(node);

            TreeNode leftNode = node.left;
            TreeNode rightNode = node.right;

            if (leftNode != null && stack.peek() != null && stack.peek() != nodeToBeLookedFor) {
                performPreorderTraversal(leftNode, nodeToBeLookedFor, stack);
            }

            if (rightNode != null && stack.peek() != null && stack.peek() != nodeToBeLookedFor) {
                performPreorderTraversal(rightNode, nodeToBeLookedFor, stack);
            }

            if (stack.peek() != null && stack.peek() != nodeToBeLookedFor) {
                stack.pop();
            }
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}
