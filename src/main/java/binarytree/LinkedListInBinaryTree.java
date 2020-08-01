package binarytree;

import list.ListNode;

public class LinkedListInBinaryTree {
	
	private TreeNode rootNode;
	private boolean result;
	
	public boolean isSubPath(ListNode head, TreeNode root) {
		rootNode = root;
		processToFindIfTreeContainsLinkesList(root, head, false);
        return result;
    }

	private void processToFindIfTreeContainsLinkesList(TreeNode treeNode, ListNode listNode, boolean proceed) {
		if (treeNode != null && !result) {
			TreeNode leftNode = treeNode.left;
			TreeNode rightNode = treeNode.right;
			if (rootNode == treeNode) {
				if (treeNode.val == listNode.getVal()) {
					processToFindIfTreeContainsLinkesList(leftNode, listNode.getNext(), true);
					processToFindIfTreeContainsLinkesList(rightNode, listNode.getNext(), true);
				} else {
					processToFindIfTreeContainsLinkesList(leftNode, listNode, false);
					processToFindIfTreeContainsLinkesList(rightNode, listNode, false);
				}
			} else {
				if (proceed && listNode == null) {
					result = true;
				} else {
					if (treeNode.val == listNode.getVal()) {
						processToFindIfTreeContainsLinkesList(leftNode, listNode.getNext(), true);
						processToFindIfTreeContainsLinkesList(rightNode, listNode.getNext(), true);
					} else {
						processToFindIfTreeContainsLinkesList(leftNode, listNode, false);
						processToFindIfTreeContainsLinkesList(rightNode, listNode, false);
					}
				}
			}
		} else {
			if (proceed && listNode == null) {
				result = true;
			}
		}
	}

}
