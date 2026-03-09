package microsoft.binarytree;

public class CountUnivalueSubTrees {

    private int result = 0;

    static class TreeNode {
        private int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            processToFetchUnivalueSubTrees(root);
        }
        return result;
    }

    private void processToFetchUnivalueSubTrees(TreeNode node) {
        process(node);
    }

    private boolean process(TreeNode node) {
        TreeNode leftNode = node.left;
        TreeNode rightNode = node.right;

        boolean intermediateResult = false;
        boolean intermediateResultLeft = false;
        boolean intermediateResultRight = false;

        if (leftNode != null) {
            intermediateResultLeft = process(leftNode);
        }

        if (rightNode != null) {
            intermediateResultRight = process(rightNode);
        }

        if (leftNode != null && rightNode != null) {
            if (intermediateResultLeft && intermediateResultRight && node.val == leftNode.val && node.val == rightNode.val) {
                result++;
                intermediateResult = true;
            }
        } else if (leftNode != null && rightNode == null) {
            if (intermediateResultLeft &&  node.val == leftNode.val) {
                result++;
                intermediateResult = true;
            }
        } else if (leftNode == null && rightNode != null) {
            if (intermediateResultRight &&  node.val == rightNode.val) {
                result++;
                intermediateResult = true;
            }
        } else {
            intermediateResult = true;
            result++;
        }
        return intermediateResult;
    }
}
