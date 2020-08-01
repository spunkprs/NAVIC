package pt;

import org.junit.Before;
import org.junit.Test;

import binarytree.MaximumPathSum;
import binarytree.TreeNode;
import org.junit.Assert;

public class MaximumPathSumTest {
	
	private MaximumPathSum unit;
	
	@Before
	public void setUp() {
		unit = new MaximumPathSum();
	}
	
	@Test
	public void shouldComputeMaximumPathSumCaseOne() {
		
		TreeNode root = new TreeNode(1);
		TreeNode childOne = new TreeNode(2);
		TreeNode childTwo = new TreeNode(3);
		
		root.setLeft(childOne);
		root.setRight(childTwo);
		
		Assert.assertEquals(6, unit.maxPathSum(root));
	}
	
	@Test
	public void shouldComputeMaximumPathSumCaseTwo() {
		
		TreeNode root = new TreeNode(-10);
		TreeNode childOne = new TreeNode(9);
		TreeNode childTwo = new TreeNode(20);
		
		TreeNode childThree = new TreeNode(15);
		TreeNode childFour = new TreeNode(7);
		
		root.setLeft(childOne);
		root.setRight(childTwo);
		
		childTwo.setLeft(childThree);
		childTwo.setRight(childFour);
		
		Assert.assertEquals(42, unit.maxPathSum(root));
	}
	
	
	@Test
	public void shouldComputeMaximumPathSumCaseThree() {
		
		TreeNode root = new TreeNode(-10);
		TreeNode childOne = new TreeNode(-20);
		TreeNode childTwo = new TreeNode(-30);
		
		TreeNode childThree = new TreeNode(-15);
		TreeNode childFour = new TreeNode(-18);
		
		root.setLeft(childOne);
		root.setRight(childTwo);
		
		childTwo.setLeft(childThree);
		childTwo.setRight(childFour);
		
		Assert.assertEquals(-10, unit.maxPathSum(root));
	}
	
	@Test
	public void shouldComputeMaximumPathSumCaseFour() {
		
		TreeNode root = new TreeNode(-10);
		TreeNode childOne = new TreeNode(20);
		TreeNode childTwo = new TreeNode(-30);
		
		TreeNode childThree = new TreeNode(5);
		TreeNode childFour = new TreeNode(-18);
		
		root.setLeft(childOne);
		root.setRight(childTwo);
		
		childOne.setLeft(childThree);
		childOne.setRight(childFour);
		
		Assert.assertEquals(25, unit.maxPathSum(root));
	}
	
	@Test
	public void shouldComputeMaximumPathSumCaseFive() {
		
		TreeNode root = new TreeNode(2);
		TreeNode childOne = new TreeNode(-1);
		
		root.setLeft(childOne);
		Assert.assertEquals(2, unit.maxPathSum(root));
	}
	
	@Test
	public void shouldComputeMaximumPathSumCaseSix() {
		
		TreeNode root = new TreeNode(-2);
		TreeNode childOne = new TreeNode(6);
		TreeNode childTwo = new TreeNode(0);
		TreeNode childThree = new TreeNode(-6);
		
		root.setLeft(childOne);
		childOne.setLeft(childTwo);
		childOne.setRight(childThree);
		
		Assert.assertEquals(6, unit.maxPathSum(root));
	}

}
