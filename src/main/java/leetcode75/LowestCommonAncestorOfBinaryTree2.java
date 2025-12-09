package leetcode75;

import java.util.Stack;

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
