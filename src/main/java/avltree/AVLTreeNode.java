package avltree;

public class AVLTreeNode {
	
	private int num;
	private AVLTreeNode left;
	private AVLTreeNode right;
	
	private int depth;
	
	public AVLTreeNode(int num) {
		this.num = num;
	}

	public int getNum() {
		return num;
	}

	public AVLTreeNode getLeft() {
		return left;
	}

	public AVLTreeNode getRight() {
		return right;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public void setLeft(AVLTreeNode left) {
		this.left = left;
	}

	public void setRight(AVLTreeNode right) {
		this.right = right;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}
}
