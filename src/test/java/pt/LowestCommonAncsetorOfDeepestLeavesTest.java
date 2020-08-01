package pt;

import org.junit.Before;
import org.junit.Test;

import binarytree.LowestCommonAncsetorOfDeepestLeaves;
import binarytree.TreeNode;
import org.junit.Assert;

public class LowestCommonAncsetorOfDeepestLeavesTest {
	
	private LowestCommonAncsetorOfDeepestLeaves unit;
	
	@Before
	public void setUp() {
		unit = new LowestCommonAncsetorOfDeepestLeaves();
	}
	
	@Test
	public void shouldFindLowestCommonAncestorOfDeepestLeavesCaseOne() {
		TreeNode root = new TreeNode(1);
		TreeNode nodeOne = new TreeNode(2);
		TreeNode nodeTwo = new TreeNode(3);
		TreeNode nodeThree = new TreeNode(4);
		TreeNode nodeFour = new TreeNode(5);
		
		root.setLeft(nodeOne);
		root.setRight(nodeTwo);
		
		nodeOne.setLeft(nodeThree);
		nodeOne.setRight(nodeFour);
		
		TreeNode lowestCommonAncestor = unit.lcaDeepestLeaves(root);
		Assert.assertEquals(nodeOne.getVal(), lowestCommonAncestor.getVal());
	}

}
