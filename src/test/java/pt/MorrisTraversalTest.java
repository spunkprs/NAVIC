package pt;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import binarytree.MorrisTraversal;
import binarytree.TreeNode;
import org.junit.Assert;

public class MorrisTraversalTest {
	
	private MorrisTraversal unit;
	
	@Before
	public void setUp() {
		unit = new MorrisTraversal();
	}
	
	@Test
	public void shouldDoInOrderTraversalCaseOne() {
		TreeNode root = new TreeNode(1);
		TreeNode nodeOne = new TreeNode(2);
		TreeNode nodeTwo = new TreeNode(3);
		
		root.setRight(nodeOne);
		nodeOne.setLeft(nodeTwo);
		List<Integer> expectedList = new ArrayList<Integer>();
		
		expectedList.add(1);
		expectedList.add(3);
		expectedList.add(2);
		List<Integer> result = unit.inorderTraversal(root);
		verify(expectedList, result);
	}
	
	@Test
	public void shouldDoInOrderTraversalCaseTwo() {
		TreeNode root = new TreeNode(10);
		TreeNode nodeOne = new TreeNode(5);
		TreeNode nodeTwo = new TreeNode(30);
		TreeNode nodeThree = new TreeNode(-2);
		TreeNode nodeFour = new TreeNode(6);
		TreeNode nodeFive = new TreeNode(40);
		TreeNode nodeSix = new TreeNode(2);
		TreeNode nodeSeven = new TreeNode(8);
		TreeNode nodeEight = new TreeNode(-1);
		
		root.setLeft(nodeOne);
		root.setRight(nodeTwo);
		nodeOne.setLeft(nodeThree);
		nodeOne.setRight(nodeFour);
		nodeTwo.setRight(nodeFive);
		nodeThree.setRight(nodeSix);
		nodeFour.setRight(nodeSeven);
		nodeSix.setLeft(nodeEight);
		
		
		List<Integer> expectedList = new ArrayList<Integer>();
		
		expectedList.add(-2);
		expectedList.add(-1);
		expectedList.add(2);
		expectedList.add(5);
		expectedList.add(6);
		expectedList.add(8);
		expectedList.add(10);
		expectedList.add(30);
		expectedList.add(40);
		
		List<Integer> result = unit.inorderTraversal(root);
		verify(expectedList, result);
	}

	private void verify(List<Integer> expectedList, List<Integer> result) {
		Assert.assertEquals(expectedList.size(), result.size());
		for (int i = 0; i < expectedList.size(); i++) {
			Assert.assertEquals(expectedList.get(i), result.get(i));
		}
		
	}

}
