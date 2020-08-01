import org.junit.Before;
import org.junit.Test;

import binarytree.LongestZigZagPathInBinaryTree;
import binarytree.TreeNode;
import org.junit.Assert;

public class LongestZigZagPathInBinaryTreeTest {
	
	private LongestZigZagPathInBinaryTree unit;
	
	@Before
	public void setUp() {
		unit = new LongestZigZagPathInBinaryTree();
	}
	
	
	@Test
	public void shouldComputeLongestZigZagPathSumCaseOne() {
		TreeNode root = new TreeNode(1);
		TreeNode childOne = new TreeNode(1);
		TreeNode childTwo = new TreeNode(1);
		TreeNode childThree = new TreeNode(1);
		TreeNode childFour = new TreeNode(1);
		TreeNode childFive = new TreeNode(1);
		TreeNode childSix = new TreeNode(1);
		TreeNode childSeven = new TreeNode(1);
		
		root.setRight(childOne);
		childOne.setLeft(childTwo);
		childOne.setRight(childThree);
		childThree.setLeft(childFour);
		childThree.setRight(childFive);
		childFour.setRight(childSix);
		childSix.setRight(childSeven);
		
		Assert.assertEquals(3, unit.longestZigZag(root));
	}
	
	@Test
	public void shouldComputeLongestZigZagPathSumCaseTwo() {
		TreeNode root = new TreeNode(1);
		TreeNode childOne = new TreeNode(1);
		TreeNode childTwo = new TreeNode(1);
		TreeNode childThree = new TreeNode(1);
		TreeNode childFour = new TreeNode(1);
		TreeNode childFive = new TreeNode(1);
		TreeNode childSix = new TreeNode(1);
		
		
		root.setLeft(childOne);
		childOne.setLeft(childTwo);
		childOne.setRight(childThree);
		childThree.setLeft(childFour);
		childThree.setRight(childFive);
		childFour.setLeft(childSix);
		
		Assert.assertEquals(3, unit.longestZigZag(root));
	}

}
