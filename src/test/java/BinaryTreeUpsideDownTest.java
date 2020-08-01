import org.junit.Before;
import org.junit.Test;

import binarytree.BinaryTreeUpsideDown;
import binarytree.TreeNode;
import org.junit.Assert;

public class BinaryTreeUpsideDownTest {
	
	private BinaryTreeUpsideDown unit;
	
	@Before
	public void setUp() {
		unit = new BinaryTreeUpsideDown();
	}
	
	@Test
	public void shouldModifyTreeUpsideDownCaseOne() {
		TreeNode root = new TreeNode(1);
		TreeNode childOne = new TreeNode(2);
		TreeNode childTwo = new TreeNode(3);
		TreeNode childThree = new TreeNode(4);
		TreeNode childFour = new TreeNode(5);
		
		root.setLeft(childOne);
		root.setRight(childTwo);
		
		childOne.setLeft(childThree);
		childOne.setRight(childFour);
		
		TreeNode result = unit.upsideDownBinaryTree(root);
		Assert.assertNotNull(result);
	}
	
	@Test
	public void shouldModifyTreeUpsideDownCaseTwo() {
		TreeNode root = new TreeNode(1);
		
		TreeNode result = unit.upsideDownBinaryTree(root);
		Assert.assertNotNull(result);
	}
	
	@Test
	public void shouldModifyTreeUpsideDownCaseThree() {
		TreeNode root = new TreeNode(1);
		TreeNode childOne = new TreeNode(2);
		TreeNode childTwo = new TreeNode(3);
		
		root.setLeft(childOne);
		childOne.setLeft(childTwo);
		
		TreeNode result = unit.upsideDownBinaryTree(root);
		Assert.assertNotNull(result);
	}

}
