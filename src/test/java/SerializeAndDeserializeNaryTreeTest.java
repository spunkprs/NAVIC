import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


import binarytree.SerializeAndDeserializeNaryTree;
import binarytree.SerializeAndDeserializeNaryTree.Node;
import org.junit.Assert;

public class SerializeAndDeserializeNaryTreeTest {
		
	private SerializeAndDeserializeNaryTree unit;
	
	@Before
	public void setUp() {
		unit = new SerializeAndDeserializeNaryTree();
	}
	
	@Test
	public void shouldSerializeAndDeserializeNaryTreeCaseOne() {
		Node root = new Node(1);
		
		Node nodeOne = new Node(3);
		Node nodeTwo = new Node(2);
		Node nodeThree = new Node(4);
		
		Node nodeFour = new Node(9);
		Node nodeFive = new Node(5);
		Node nodeSix = new Node(6);
		
		List<Node> rootChildren = new ArrayList<Node>();
		
		List<Node> chilrenOne = new ArrayList<Node>();
		chilrenOne.add(nodeFour);
		chilrenOne.add(nodeFive);
		chilrenOne.add(nodeSix);
		
		nodeOne.children = chilrenOne;
		
		rootChildren.add(nodeOne);
		rootChildren.add(nodeTwo);
		rootChildren.add(nodeThree);
		
		root.children = rootChildren;
		
		String expectedSerializedString = "1,D1,N,3,D2,1,2,D2,1,4,D2,1,9,D3,3,5,D3,3,6,D3,3,";
		String actualSerializedString = unit.serialize(root);
		
		Assert.assertEquals(expectedSerializedString, actualSerializedString);
		
		Node actualRoot = unit.deserialize(actualSerializedString);
		
		Assert.assertEquals(actualSerializedString, unit.serialize(actualRoot));
		
	}
	
	@Test
	public void shouldSerializeAndDeserializeNaryTreeCaseTwo() {
		Node root = new Node(1);
		
		Node nodeOne = new Node(3);
		Node nodeTwo = new Node(2);
		Node nodeThree = new Node(4);
		
		Node nodeFour = new Node(9);
		Node nodeFive = new Node(5);
		Node nodeSix = new Node(6);
		
		Node nodeSeven = new Node(8);
		Node nodeEight = new Node(16);
		
		List<Node> rootChildren = new ArrayList<Node>();
		
		List<Node> chilrenOne = new ArrayList<Node>();
		chilrenOne.add(nodeFour);
		chilrenOne.add(nodeFive);
		chilrenOne.add(nodeSix);
		
		nodeOne.children = chilrenOne;
		
		rootChildren.add(nodeOne);
		rootChildren.add(nodeTwo);
		rootChildren.add(nodeThree);
		
		root.children = rootChildren;
		
		List<Node> childrenThree = new ArrayList<Node>();
		childrenThree.add(nodeSeven);
		childrenThree.add(nodeEight);
		
		nodeThree.children = childrenThree;
		
		String expectedSerializedString = "1,D1,N,3,D2,1,2,D2,1,4,D2,1,9,D3,3,5,D3,3,6,D3,3,8,D3,4,16,D3,4,";
		String actualSerializedString = unit.serialize(root);
		
		Assert.assertEquals(expectedSerializedString, actualSerializedString);
		
		Node actualRoot = unit.deserialize(actualSerializedString);
		
		Assert.assertEquals(actualSerializedString, unit.serialize(actualRoot));
		
	}

}
