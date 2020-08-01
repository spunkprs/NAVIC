package binarytree;

public class LongestZigZagPathInBinaryTree {
	
	
	private int longestZigZagPath = 0;
	
	public int longestZigZag(TreeNode root) {
		processToComputeLongestZigZagPath(root, 1, "EMPTY");
		return longestZigZagPath - 1;
    }

	private void processToComputeLongestZigZagPath(TreeNode node, int sumTillNow, String parentOrientation) {
		if (node != null) {
			updateLongestZigZagPathSum(sumTillNow);
			TreeNode leftNode = node.left;
			TreeNode rightNode = node.right;
			
			if (parentOrientation.equals("EMPTY")) {
				if (leftNode != null) {
					processToComputeLongestZigZagPath(leftNode, sumTillNow + 1, "LEFT");
				}
				
				if (rightNode != null) {
					processToComputeLongestZigZagPath(rightNode, sumTillNow + 1, "RIGHT");
				}
			} else {
				if (leftNode != null && parentOrientation.equals("LEFT")) {
					int sumTillNowUpdated = 1;
					processToComputeLongestZigZagPath(leftNode, sumTillNowUpdated + 1, "LEFT");
				}
				
				if (leftNode != null && parentOrientation.equals("RIGHT")) {
					processToComputeLongestZigZagPath(leftNode, sumTillNow + 1, "LEFT");
				}
				
				if (rightNode != null && parentOrientation.equals("LEFT")) {
					processToComputeLongestZigZagPath(rightNode, sumTillNow + 1, "RIGHT");
				} 
				
				if (rightNode != null && parentOrientation.equals("RIGHT")) {
					int sumTillNowUpdated = 1;
					processToComputeLongestZigZagPath(rightNode, sumTillNowUpdated + 1, "RIGHT");
				}
			}
		}
	}

	private void updateLongestZigZagPathSum(int sumTillNow) {
		longestZigZagPath = sumTillNow > longestZigZagPath ? sumTillNow : longestZigZagPath;
	}


}
