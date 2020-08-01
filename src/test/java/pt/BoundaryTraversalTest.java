package pt;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import binarytree.BoundaryTraversal;
import binarytree.TreeNode;
import org.junit.Assert;

public class BoundaryTraversalTest {
	
	private BoundaryTraversal unit;
	
	@Before
	public void setUp() {
		unit = new BoundaryTraversal();
	}
	
	@Test
	public void shouldDoBoundaryTraversalCaseOne() {
		TreeNode root = new TreeNode(1);
		TreeNode leftNode = new TreeNode(2);
		TreeNode rightNode = new TreeNode(3);
		
		root.setLeft(leftNode);
		root.setRight(rightNode);
		
		List<Integer> expectedResult = new ArrayList<Integer>();
		expectedResult.add(1);
		expectedResult.add(2);
		expectedResult.add(3);
		
		List<Integer> actualResult = new ArrayList<Integer>();
		
		actualResult = unit.boundaryOfBinaryTree(root);
		verify(expectedResult, actualResult);
	}

	private void verify(List<Integer> expectedResult, List<Integer> actualResult) {
		Assert.assertEquals(expectedResult.size(), actualResult.size());
		for (int i = 0; i < expectedResult.size(); i++) {
			Assert.assertEquals(expectedResult.get(i), actualResult.get(i));
		}
		
	}

}
