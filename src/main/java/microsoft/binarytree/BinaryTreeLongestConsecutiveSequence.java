package microsoft.binarytree;

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
