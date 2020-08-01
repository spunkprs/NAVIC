package avltree;

import java.util.ArrayList;
import java.util.List;

public class AVLTreeOperations {
	
	private AVLTreeNode root;
	private boolean isRotationDone = false;
	
	public AVLTreeNode insertNodeInTree(int num) {
		
		if (root == null) {
			root = new AVLTreeNode(num);
		} else {
			processToInsertNodeInTreeAndBalanceIt(num, null, root);
		}
		isRotationDone = false;
		return root;
	}
	
	public List<Integer> performInorderTravsersal() {
		List<Integer> inorderTravsersal = new ArrayList<Integer>();
		processToPerformInorderTraversal(inorderTravsersal, root);
		return inorderTravsersal;
	}

	private void processToPerformInorderTraversal(List<Integer> inorderTravsersal, AVLTreeNode node) {
		if (node != null) {
			AVLTreeNode leftNode = node.getLeft();
			
			AVLTreeNode rightNode = node.getRight();
			
			processToPerformInorderTraversal(inorderTravsersal, leftNode);
			inorderTravsersal.add(node.getNum());
			processToPerformInorderTraversal(inorderTravsersal, rightNode);
		}
	}
	
	private AVLTreeNode processToInsertNodeInTreeAndBalanceIt(int num, AVLTreeNode parentNode, AVLTreeNode childNode) {
		AVLTreeNode rotatedLeftSubTreeNode = null;
		AVLTreeNode rotatedRightSubTreeNode = null;
		if (childNode != null) {
			if (num < childNode.getNum()) {
				rotatedLeftSubTreeNode = processToInsertNodeInTreeAndBalanceIt(num, childNode, childNode.getLeft());
			} else {
				rotatedRightSubTreeNode = processToInsertNodeInTreeAndBalanceIt(num, childNode, childNode.getRight());
			}
			
			if (childNode != null && !isRotationDone) {
				int leftDepth = fetchLeftDepth(childNode);
				int rightDepth = fetchRightDepth(childNode);
				
				if (Math.abs(leftDepth - rightDepth) > 1) {
					if (leftDepth > rightDepth) {
						int furtherLeftDepth = fetchLeftDepth(childNode.getLeft());
						int furtherRightDepth = fetchRightDepth(childNode.getLeft());
						if (furtherRightDepth > furtherLeftDepth) {
							childNode = performLeftRightRotation(childNode);
						} else {
							childNode = performLeftLeftRotation(childNode);
						}
					} else {
						int furtherLeftDepth = fetchLeftDepth(childNode.getRight());
						int furtherRightDepth = fetchRightDepth(childNode.getRight());
						if (furtherRightDepth > furtherLeftDepth) {
							childNode = performRightRightRotation(childNode);
						} else {
							childNode = performRightLeftRotation(childNode);
						}
					}
					isRotationDone = true;
				} else {
					childNode.setDepth(Math.max(leftDepth, rightDepth));
				}
			}
			
			if (childNode != null && rotatedLeftSubTreeNode != null && childNode != rotatedLeftSubTreeNode && rotatedLeftSubTreeNode.getNum() < childNode.getNum()) {
				childNode.setLeft(rotatedLeftSubTreeNode);
			}
			
			if (childNode != null && rotatedRightSubTreeNode != null && childNode != rotatedRightSubTreeNode && childNode.getNum() < rotatedRightSubTreeNode.getNum()) {
				childNode.setRight(rotatedRightSubTreeNode);
			}
		} else {
			AVLTreeNode nodeToBeInserted = new AVLTreeNode(num);
			if (num < parentNode.getNum()) {
				parentNode.setLeft(nodeToBeInserted);
			} else {
				parentNode.setRight(nodeToBeInserted); 
			}
			int leftDepth = fetchLeftDepth(parentNode);
			int rightDepth = fetchRightDepth(parentNode);
			parentNode.setDepth(Math.max(leftDepth, rightDepth));
			return nodeToBeInserted;
		}
		return childNode;
	}

	private AVLTreeNode performRightLeftRotation(AVLTreeNode node) {
		AVLTreeNode rightNode = node.getRight();
		AVLTreeNode rightLeftNode = node.getRight().getLeft();
		rightNode.setLeft(null);
		rightNode.setDepth(rightNode.getDepth() - 1);
		rightLeftNode.setDepth(rightLeftNode.getDepth() + 1);
		node.setRight(rightLeftNode);
		rightLeftNode.setRight(rightNode);
		
		rightNode = node.getRight();
		rightNode.setLeft(node);
		node.setRight(null);
		node.setDepth(Math.max(fetchLeftDepth(node), fetchRightDepth(node)));
		
		if (node == root) {
			root = rightNode;
		}
		node = rightNode;
		int leftDepth = fetchLeftDepth(node);
		int rightDepth = fetchRightDepth(node);
		node.setDepth(Math.max(leftDepth, rightDepth));
		return node;
	}

	private AVLTreeNode performRightRightRotation(AVLTreeNode node) {
		AVLTreeNode rightNode = node.getRight();
		AVLTreeNode rightLeftNode = rightNode.getLeft();
		
		node.setRight(rightLeftNode);
		node.setDepth(Math.max(fetchLeftDepth(node), fetchRightDepth(node)));
		rightNode.setLeft(node);
		rightNode.setDepth(Math.max(fetchLeftDepth(rightNode), fetchRightDepth(rightNode)));
		if (node == root) {
			root = rightNode;
		}
		node = rightNode;
		return node;
	}

	private AVLTreeNode performLeftLeftRotation(AVLTreeNode node) {
		AVLTreeNode leftNode = node.getLeft();
		AVLTreeNode leftRightNode = leftNode.getRight();
		
		node.setLeft(leftRightNode);
		node.setDepth(Math.max(fetchLeftDepth(node), fetchRightDepth(node)));
		leftNode.setRight(node);
		leftNode.setDepth(Math.max(fetchLeftDepth(leftNode), fetchRightDepth(leftNode)));
		if (node == root) {
			root = leftNode;
		}
		node = leftNode;
		return node;
	}

	private AVLTreeNode performLeftRightRotation(AVLTreeNode node) {
		AVLTreeNode leftNode = node.getLeft();
		AVLTreeNode leftRightNode = node.getLeft().getRight();
		leftNode.setRight(null);
		leftNode.setDepth(leftNode.getDepth() - 1);
		leftRightNode.setDepth(leftRightNode.getDepth() + 1);
		node.setLeft(leftRightNode);
		leftRightNode.setLeft(leftNode);
		
		leftNode = node.getLeft();
		leftNode.setRight(node);
		node.setLeft(null);
		node.setDepth(Math.max(fetchLeftDepth(node), fetchRightDepth(node)));
		
		if (node == root) {
			root = leftNode;
		}
		node = leftNode;
		int leftDepth = fetchLeftDepth(node);
		int rightDepth = fetchRightDepth(node);
		node.setDepth(Math.max(leftDepth, rightDepth));
		return node;
	}

	private int fetchLeftDepth(AVLTreeNode node) {
		if (node.getLeft() == null) {
			return 0;
		} else {
			AVLTreeNode leftNode = node.getLeft();
			if (leftNode.getLeft() == null && leftNode.getRight() == null) {
				return 1;
			}
			return leftNode.getDepth() + 1;
		}
	}
	
	private int fetchRightDepth(AVLTreeNode node) {
		if (node.getRight() == null) {
			return 0;
		} else {
			AVLTreeNode rightNode = node.getRight();
			if (rightNode.getLeft() == null && rightNode.getRight() == null) {
				return 1;
			}
			return rightNode.getDepth() + 1;
		}
	}

}
