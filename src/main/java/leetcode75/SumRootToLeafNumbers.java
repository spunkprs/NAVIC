package leetcode75;

import binarytree.TreeNode;

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
