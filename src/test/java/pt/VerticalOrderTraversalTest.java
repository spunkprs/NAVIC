package pt;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import binarytree.TreeNode;
import binarytree.VerticalOrderTraversal;
import org.junit.Assert;

public class VerticalOrderTraversalTest {
	
	private VerticalOrderTraversal unit;
	
	@Before
	public void setUp() {
		unit = new VerticalOrderTraversal();
	}
	
	@Test
	public void shouldPerformverticalOrderTraversalCaseOne() {
		TreeNode root = new TreeNode(3);
		TreeNode nodeOne = new TreeNode(9);
		TreeNode nodeTwo = new TreeNode(20);
		TreeNode nodeThree = new TreeNode(15);
		TreeNode nodeFour = new TreeNode(7);
		
		root.setLeft(nodeOne);
		root.setRight(nodeTwo);
		
		nodeTwo.setLeft(nodeThree);
		nodeTwo.setRight(nodeFour);
		
		List<List<Integer>> expectedList = new ArrayList<List<Integer>>();
		List<Integer> listOne = new ArrayList<Integer>();
		List<Integer> listTwo = new ArrayList<Integer>();
		List<Integer> listThree = new ArrayList<Integer>();
		List<Integer> listFour = new ArrayList<Integer>();
		
		listOne.add(9);
		listTwo.add(3);
		listTwo.add(15);
		listThree.add(20);
		listFour.add(7);
		
		expectedList.add(listOne);
		expectedList.add(listTwo);
		expectedList.add(listThree);
		expectedList.add(listFour);
		
		List<List<Integer>> resultList = unit.verticalTraversal(root);
		verify(expectedList, resultList);
	}
	
	@Test
	public void shouldPerformverticalOrderTraversalCaseTwo() {
		TreeNode root = new TreeNode(1);
		TreeNode nodeOne = new TreeNode(2);
		TreeNode nodeTwo = new TreeNode(3);
		TreeNode nodeThree = new TreeNode(4);
		TreeNode nodeFour = new TreeNode(5);
		TreeNode nodeFive = new TreeNode(6);
		TreeNode nodeSix = new TreeNode(7);
		
		root.setLeft(nodeOne);
		root.setRight(nodeTwo);
		
		nodeOne.setLeft(nodeThree);
		nodeOne.setRight(nodeFour);
		
		nodeTwo.setLeft(nodeFive);
		nodeTwo.setRight(nodeSix);
		
		List<List<Integer>> expectedList = new ArrayList<List<Integer>>();
		List<Integer> listOne = new ArrayList<Integer>();
		List<Integer> listTwo = new ArrayList<Integer>();
		List<Integer> listThree = new ArrayList<Integer>();
		List<Integer> listFour = new ArrayList<Integer>();
		List<Integer> listFive = new ArrayList<Integer>();
		
		listOne.add(4);
		listTwo.add(2);
		
		listThree.add(1);
		listThree.add(5);
		listThree.add(6);
		
		listFour.add(3);
		listFive.add(7);
		
		expectedList.add(listOne);
		expectedList.add(listTwo);
		expectedList.add(listThree);
		expectedList.add(listFour);
		expectedList.add(listFive);
		
		List<List<Integer>> resultList = unit.verticalTraversal(root);
		verify(expectedList, resultList);
	}
	
	@Test
	@Ignore
	public void shouldPerformverticalOrderTraversalCaseThree() {
		TreeNode root = new TreeNode(0);
		TreeNode nodeOne = new TreeNode(2);
		TreeNode nodeTwo = new TreeNode(1);
		TreeNode nodeThree = new TreeNode(3);
		TreeNode nodeFour = new TreeNode(4);
		TreeNode nodeFive = new TreeNode(5);
		TreeNode nodeSix = new TreeNode(7);
		TreeNode nodeSeven = new TreeNode(6);
		
		root.setLeft(nodeOne);
		root.setRight(nodeTwo);
		
		nodeOne.setLeft(nodeThree);
		nodeOne.setRight(nodeFour);
		
		nodeTwo.setLeft(nodeFive);
		nodeTwo.setRight(nodeSix);
		
		List<List<Integer>> expectedList = new ArrayList<List<Integer>>();
		List<Integer> listOne = new ArrayList<Integer>();
		List<Integer> listTwo = new ArrayList<Integer>();
		List<Integer> listThree = new ArrayList<Integer>();
		List<Integer> listFour = new ArrayList<Integer>();
		List<Integer> listFive = new ArrayList<Integer>();
		
		listOne.add(4);
		listTwo.add(2);
		
		listThree.add(1);
		listThree.add(5);
		listThree.add(6);
		
		listFour.add(3);
		listFive.add(7);
		
		expectedList.add(listOne);
		expectedList.add(listTwo);
		expectedList.add(listThree);
		expectedList.add(listFour);
		expectedList.add(listFive);
		
		List<List<Integer>> resultList = unit.verticalTraversal(root);
		verify(expectedList, resultList);
	}

	private void verify(List<List<Integer>> expectedList, List<List<Integer>> resultList) {
		Assert.assertEquals(expectedList.size(), resultList.size());
		
		for (int i = 0; i < expectedList.size(); i++) {
			List<Integer> expected = expectedList.get(i);
			List<Integer> result = resultList.get(i);
			Assert.assertEquals(expected.size(), result.size());
			for (int j = 0; j < expected.size(); j++) {
				Assert.assertEquals(expected.get(j), result.get(j));
			}
		}
	}
}
