package binarytree;

import list.ListNode;

import java.util.HashSet;
import java.util.Set;

public class LinkedListInBinaryTree {

	public static void main(String ar[]) {
		LinkedListInBinaryTree unit = new LinkedListInBinaryTree();

		TreeNode root = new TreeNode(1);
		TreeNode nodeOne = new TreeNode(4);
		TreeNode nodeTwo = new TreeNode(4);
		TreeNode nodeThree = new TreeNode(2);
		TreeNode nodeFour = new TreeNode(5);
		TreeNode nodeFive = new TreeNode(2);
		TreeNode nodeSix = new TreeNode(8);
		TreeNode nodeSeven = new TreeNode(6);


		root.left = nodeOne;
		root.right = nodeTwo;
		nodeOne.left = nodeThree;
		nodeTwo.left = nodeFour;
		nodeTwo.right = nodeFive;
		nodeThree.right = nodeSix;
		nodeFive.left = nodeSeven;

		ListNode listNodeOne = new ListNode(2);
		ListNode listNodeTwo = new ListNode(4);
		ListNode listNodeThree = new ListNode(6);

		listNodeOne.setNext(listNodeTwo);
		listNodeTwo.setNext(listNodeThree);

		System.out.println("Is linked list present inside binary tree " + unit.isSubPath(listNodeOne, root));
	}

	public boolean isSubPath(ListNode head, TreeNode root) {
		String stringFromList = preparePathFromList(head);

		Set<String> resultPaths = new HashSet<>();
		StringBuilder sb = new StringBuilder();

		preparePathStrings(root, sb, resultPaths);
		for (String resultString : resultPaths) {
			if (resultString.contains(stringFromList)) {
				return true;
			}
		}
		return false;
	}

	private String preparePathFromList(ListNode node) {
		StringBuilder sb = new StringBuilder();

		while (node != null) {
			sb.append(node.getVal());
			node = node.getNext();
		}
		return sb.toString();
	}

	private void preparePathStrings(TreeNode node, StringBuilder sb, Set<String> resultPaths) {
		TreeNode leftNode = node.left;
		TreeNode rightNode = node.right;
		sb.append(node.val);

		if (leftNode != null) {
			preparePathStrings(leftNode, sb, resultPaths);
			String val = String.valueOf(leftNode.val);
			sb.delete(sb.length() - val.length() , sb.length());
		}

		if (rightNode != null) {
			preparePathStrings(rightNode, sb, resultPaths);
			String val = String.valueOf(rightNode.val);
			sb.delete(sb.length() - val.length() , sb.length());
		}

		if (leftNode == null && rightNode == null) {
			resultPaths.add(sb.toString());
		}
	}
}
