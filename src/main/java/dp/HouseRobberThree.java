package dp;

public class HouseRobberThree {


	public static void main(String ar[]) {
		HouseRobberThree unit = new HouseRobberThree();

		TreeNode rootNode = new TreeNode(3);
		TreeNode nodeOne = new TreeNode(2);
		TreeNode nodeTwo = new TreeNode(6);
		TreeNode nodeThree = new TreeNode(5);
		TreeNode nodeFour = new TreeNode(1);
		TreeNode nodeFive = new TreeNode(1);

		rootNode.left = nodeOne;
		rootNode.right = nodeTwo;

		nodeOne.left = nodeThree;

		nodeTwo.left = nodeFour;
		nodeTwo.right = nodeFive;

		System.out.println("Maximum amount of money that can be robbed without informing police is " + unit.rob(rootNode));
	}

	public int rob(TreeNode root) {
		Pair result = processTree(root);
		return Math.max(result.leftNum, result.rightNum);
	}

	private Pair processTree(TreeNode node) {
		TreeNode leftNode = node.left;
		TreeNode rightNode = node.right;

		Pair leftPair = null;
		Pair rightPair = null;
		Pair resultPair = null;

		if (leftNode == null && rightNode == null) {
			resultPair = new Pair(node.val, 0);
		} else {
			if (leftNode != null) {
				leftPair = processTree(leftNode);
			}

			if (rightNode != null) {
				rightPair = processTree(rightNode);
			}

			if (leftPair != null && rightPair != null) {
				resultPair = new Pair(node.val + leftPair.rightNum + rightPair.rightNum,
						Math.max(leftPair.leftNum, leftPair.rightNum) + Math.max(rightPair.leftNum, rightPair.rightNum) );
			} else if (leftPair == null && rightPair != null) {
				resultPair = new Pair(node.val + rightPair.rightNum, Math.max(rightPair.leftNum, rightPair.rightNum));
			} else if (leftPair != null && rightPair == null) {
				resultPair = new Pair(node.val + leftPair.rightNum, Math.max(leftPair.leftNum, leftPair.rightNum));
			}
		}
		return resultPair;
	}

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(int value) {
			this.val = value;
		}
		
		public void setLeft(TreeNode left) {
			this.left = left;
		}
		public void setRight(TreeNode right) {
			this.right = right;
		}
	}

	static class Pair {
		private int leftNum;
		private int rightNum;

		public Pair(int leftNum, int rightNum) {
			this.leftNum = leftNum;
			this.rightNum = rightNum;
		}
	}
}
