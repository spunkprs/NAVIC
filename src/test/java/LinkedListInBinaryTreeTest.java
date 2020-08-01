import org.junit.Before;
import org.junit.Test;

import binarytree.LinkedListInBinaryTree;
import binarytree.TreeNode;
import org.junit.Assert;
import list.ListNode;

public class LinkedListInBinaryTreeTest {
	
	private LinkedListInBinaryTree unit;
	
	@Before
	public void setUp() {
		unit = new LinkedListInBinaryTree();
	}
	
	@Test
	public void shouldCheckIfTreeContainsLinkedListCaseOne() {
		ListNode head = new ListNode(4);
		ListNode listNodeOne = new ListNode(2);
		ListNode listNodeTwo = new ListNode(8);
		
		head.setNext(listNodeOne);
		listNodeOne.setNext(listNodeTwo);
		
		TreeNode root = new TreeNode(1);
		TreeNode nodeOne = new TreeNode(4);
		TreeNode nodeTwo = new TreeNode(4);
		TreeNode nodeThree = new TreeNode(2);
		TreeNode nodeFour = new TreeNode(2);
		TreeNode nodeFive = new TreeNode(1);
		TreeNode nodeSix = new TreeNode(6);
		TreeNode nodeSeven = new TreeNode(8);
		TreeNode nodeEight = new TreeNode(1);
		TreeNode nodeNine = new TreeNode(3);
		
		root.setLeft(nodeOne);
		root.setRight(nodeTwo);
		
		nodeOne.setRight(nodeThree);
		nodeTwo.setLeft(nodeFour);
		
		nodeThree.setLeft(nodeFive);
		nodeFour.setLeft(nodeSix);
		nodeFour.setRight(nodeSeven);
		nodeSeven.setLeft(nodeEight);
		nodeSeven.setRight(nodeNine);
		
		Assert.assertTrue(unit.isSubPath(head, root));
	}

}
