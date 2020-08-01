package binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ZigZagLevelOrderTraversal {
	
	private List<List<Integer>> result = new ArrayList<List<Integer>>();
	
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		if (root != null) {
			processToPerformZigZagLevelOrderTraversal(root);
		}
        return result;
    }

	private void processToPerformZigZagLevelOrderTraversal(TreeNode node) {
		Stack<TreeNode> stackOne = new Stack<TreeNode>();
		Stack<TreeNode> stackTwo = new Stack<TreeNode>();
		List<Integer> list = new ArrayList<Integer>();
		
		boolean stackOneTurn = true;
		stackOne.add(node);
		
		while (!stackOne.isEmpty() || !stackTwo.isEmpty()) {
			if (stackOneTurn) {
				TreeNode treeNode = stackOne.pop();
				
				if (treeNode.left != null) {
					stackTwo.add(treeNode.left);
				}
				
				if (treeNode.right != null) {
					stackTwo.add(treeNode.right);
				}
				list.add(treeNode.val);
				if (stackOne.isEmpty()) {
					stackOneTurn = false;
					result.add(list);
					list = new ArrayList<Integer>();
				}
			} else if (!stackOneTurn) {
				TreeNode treeNode = stackTwo.pop();
				
				if (treeNode.right != null) {
					stackOne.add(treeNode.right);
				}
				
				if (treeNode.left != null) {
					stackOne.add(treeNode.left);
				}
				list.add(treeNode.val);
				if (stackTwo.isEmpty()) {
					stackOneTurn = true;
					result.add(list);
					list = new ArrayList<Integer>();
				}
			}
		}
	}

}
