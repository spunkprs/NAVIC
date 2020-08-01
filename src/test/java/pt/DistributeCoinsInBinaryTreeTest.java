package pt;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import binarytree.DistributeCoinsInBinaryTree;
import binarytree.TreeNode;
import org.junit.Assert;

public class DistributeCoinsInBinaryTreeTest {
	
	private DistributeCoinsInBinaryTree unit; 
	
	@Before
	public void setUp() {
		unit = new DistributeCoinsInBinaryTree();
	}
	
	@Test
	@Ignore
	public void shouldDistributeCoinsInBinaryTreeCaseOne() {
		TreeNode root = new TreeNode(3);
		TreeNode leftNode = new TreeNode(0);
		TreeNode rightNode = new TreeNode(0);
		
		root.setLeft(leftNode);
		root.setRight(rightNode);
		
		Assert.assertEquals(2, unit.distributeCoins(root));
	}

}
