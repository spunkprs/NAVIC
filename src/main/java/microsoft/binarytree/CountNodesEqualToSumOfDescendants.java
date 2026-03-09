package microsoft.binarytree;

public class CountNodesEqualToSumOfDescendants {

    static class TreeNode {
        private int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

    private int count = 0;

    public int equalToDescendants(TreeNode root) {
        if (root.left == null && root.right == null) {
            if (root.val == 0) {
                count++;
            }
        } else {
            processToComputeCount(root);
        }
        return count;
    }

    private void processToComputeCount(TreeNode node) {
        process(node);
    }

    private int process(TreeNode node) {
        TreeNode leftNode = node.left;
        TreeNode rightNode = node.right;

        int leftSum = 0;
        int rightSum = 0;

        if (leftNode == null && rightNode == null) {
            if (node.val == 0) {
                count++;
            }
            return node.val;
        }

        if (leftNode != null) {
            leftSum = process(leftNode);
        }

        if (rightNode != null) {
            rightSum = process(rightNode);
        }

        if (node.val == leftSum + rightSum) {
            count++;
        }
        return node.val + leftSum + rightSum;
    }
}
