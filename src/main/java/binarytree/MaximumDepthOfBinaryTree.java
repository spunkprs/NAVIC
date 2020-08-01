package binarytree;

public class MaximumDepthOfBinaryTree {
	
	public int maxDepth(TreeNode root) {
		return processToFindMaxDepth(root);
    }

	private int processToFindMaxDepth(TreeNode node) {
		if (node != null) {
			TreeNode leftNode = node.left;
			TreeNode rightNode = node.right;
			
			if (leftNode != null && rightNode != null) {
				return Math.max(processToFindMaxDepth(leftNode), processToFindMaxDepth(rightNode)) + 1;
			} else if (leftNode == null && rightNode != null) {
				return processToFindMaxDepth(rightNode) + 1;
			} else if (leftNode != null && rightNode == null) {
				return processToFindMaxDepth(leftNode) + 1;
			} else {
				return 1;
			}
		} else {
			return 0;
		}
	}

}
