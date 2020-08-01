import org.junit.Before;
import org.junit.Test;

import binarytree.SerializeAndDeserializeBinaryTree;
import binarytree.TreeNode;
import org.junit.Assert;

public class SerializeAndDeserializeBinaryTreeTest {
	
	
	private SerializeAndDeserializeBinaryTree unit;
	
	@Before
	public void setUp() {
		unit = new SerializeAndDeserializeBinaryTree();
	}
	
	@Test
	public void shouldTestSerializeAndDeserializeBinaryTreeCaseOne() {
		TreeNode root = new TreeNode(1);
		TreeNode childOne = new TreeNode(2);
		TreeNode childTwo = new TreeNode(3);
		
		TreeNode childThree = new TreeNode(4);
		TreeNode childFour = new TreeNode(5);
		
		root.setLeft(childOne);
		root.setRight(childTwo);
		
		childTwo.setLeft(childThree);
		childTwo.setRight(childFour);
		
		String expectedSerializedContent = "1,2,X,X,3,4,X,X,5,X,X,";
		Assert.assertEquals(expectedSerializedContent, unit.serialize(root));
		
		TreeNode node = unit.deserialize(expectedSerializedContent);
		
		Assert.assertEquals(expectedSerializedContent, unit.serialize(node));
	}

}
