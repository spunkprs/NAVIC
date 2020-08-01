package pt;

import org.junit.Before;
import org.junit.Test;

import binarytree.BSTtoSortedDoublyLinkedList;
import binarytree.Node;
import org.junit.Assert;

public class BSTtoSortedDoublyLinkedListTest {
	
	private BSTtoSortedDoublyLinkedList unit;
	
	@Before
	public void setUp() {
		unit = new BSTtoSortedDoublyLinkedList();
	}
	
	
	@Test
	public void shouldConvertBSTIntoDoublyLinkedListCaseOne() {
		Node root = new Node(2);
		Node nodeOne = new Node(3);
		root.setRight(nodeOne);
		
		Assert.assertNotNull(unit.treeToDoublyList(root));
	}

}
