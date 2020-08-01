package binarytree;

public class BinaryTreeUpsideDown {
	
	private TreeNode updatedRoot;
	private TreeNode tempNode;
	
	public TreeNode upsideDownBinaryTree(TreeNode root) {
		if (root != null) {
			processToDoUpsideDown(root);
			root.left = null;
			root.right = null;
			if (updatedRoot == null) {
				updatedRoot = root;
			}
	        return updatedRoot;
		}
		return updatedRoot;
    }

	private void processToDoUpsideDown(TreeNode node) {
		TreeNode leftChild = node.left;
		
		TreeNode rightChild = node.right;
		
		if (leftChild != null) {
			if (leftChild.left == null && leftChild.right == null) {
				if (updatedRoot == null) {
					updatedRoot = leftChild;
					tempNode = updatedRoot;
				}
				if (rightChild != null) {
					tempNode.left = rightChild;
				}
				tempNode.right = node;
				tempNode = tempNode.right;
			} else {
				processToDoUpsideDown(leftChild);
				if (rightChild != null) {
					tempNode.left = rightChild;
				}
				tempNode.right = node;
				tempNode = tempNode.right;
			}
		}
	}

}
