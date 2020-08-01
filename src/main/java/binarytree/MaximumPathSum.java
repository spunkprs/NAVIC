package binarytree;

public class MaximumPathSum {
	
	private int maxValue = Integer.MIN_VALUE;
	
	public int maxPathSum(TreeNode root) {
		int computedSum = computeMaxPathSum(root);
		if (computedSum <= root.val) {
			if (root.val > this.maxValue) {
				this.maxValue = root.val;
			}
		}
        return this.maxValue;
    }

	private int computeMaxPathSum(TreeNode node) {
		TreeNode leftNode = node.left;
		TreeNode rightNode = node.right;
		int leftSubTreeValue = 0;
		int rightSubTreeValue = 0;
		
		if (leftNode != null) {
			leftSubTreeValue = computeMaxPathSum(leftNode);
		}
		
		if (rightNode != null) {
			rightSubTreeValue = computeMaxPathSum(rightNode);
		}
		
		if (leftNode == null && rightNode == null) {
			updateMaxValue(node.val);
			return node.val;
		}
		
		if (leftSubTreeValue >= 0 && rightSubTreeValue >= 0) {
			updateMaxValue(leftSubTreeValue + rightSubTreeValue + node.val);
		} else if (leftSubTreeValue >= 0 && rightSubTreeValue < 0) {
			updateMaxValue(leftSubTreeValue + node.val); 
		} else if (rightSubTreeValue >= 0 && leftSubTreeValue < 0) {
			updateMaxValue(rightSubTreeValue + node.val);
		} else if (leftSubTreeValue < 0 && rightSubTreeValue < 0) {
			updateMaxValue(node.val);
		}
		
		if (leftSubTreeValue >= rightSubTreeValue) {
			return leftSubTreeValue + node.val;
		} else {
			return rightSubTreeValue + node.val;
		}
	}

	private void updateMaxValue(int val) {
		if (val > maxValue) {
			this.maxValue = val;
		}
	}
}
