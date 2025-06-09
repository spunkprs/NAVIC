package binarytree.cousins;

public class CousinsInBinaryTree {

    public static void main(String ar[]) {
        CousinsInBinaryTree unit = new CousinsInBinaryTree();
    }

    private boolean isNodeFound = false;

    public boolean isCousins(TreeNode root, int x, int y) {

        int depthOfFirstNode = findDepthOfNode(root, x);
        isNodeFound = false;
        int depthOfSecondNode = findDepthOfNode(root, y);

        if (depthOfFirstNode == depthOfSecondNode) {
            TreeNode parentOne = findParentOfNode(root, x);
            TreeNode parentTwo = findParentOfNode(root, y);

            if (parentOne != parentTwo) {
                return true;
            }
        }
        return false;
    }

    private TreeNode findParentOfNode(TreeNode root, int x) {
        return processToFindParentOfNode(root, x);
    }

    private TreeNode processToFindParentOfNode(TreeNode node, int x) {
        TreeNode left = node.left;
        TreeNode right = node.right;

        TreeNode result = null;

        if (left != null) {
            if (left.val == x) {
                result = node;
            } else {
                processToFindParentOfNode(left, x);
            }
        }

        if (right != null) {
            if (right.val == x) {
                result = node;
            } else {
                processToFindParentOfNode(right, x);
            }
        }
        return result;
    }

    private int findDepthOfNode(TreeNode node, int x) {
        int depth = 0;
        TreeNode leftNode = node.left;
        TreeNode rightNode = node.right;

        if (node.val == x) {
            depth = 1;
            isNodeFound = true;
            return depth;
        } else {
            if (leftNode != null) {
                int increment = isNodeFound ? 1: 0;
                depth = findDepthOfNode(leftNode, x) + increment;
                return depth;
            }

            if (rightNode != null) {
                int increment = isNodeFound ? 1: 0;
                depth = findDepthOfNode(rightNode, x) + increment;
                return depth;
            }
        }
        return depth;
    }

    static class TreeNode {
       private  int val;
       private TreeNode left;
       private TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
