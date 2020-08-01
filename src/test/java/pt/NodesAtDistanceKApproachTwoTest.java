package pt;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import binarytree.NodesAtDistanceKApproachTwo;
import binarytree.TreeNode;
import org.junit.Assert;

public class NodesAtDistanceKApproachTwoTest {
	
	private NodesAtDistanceKApproachTwo unit;
	
	@Before
	public void setUp() {
		unit = new NodesAtDistanceKApproachTwo();
	}
	
	@Test
	public void shouldFetchNodesAtDistanceKCaseOne() {
		TreeNode root = new TreeNode(3);
		TreeNode nodeOne = new TreeNode(5);
		TreeNode nodeTwo = new TreeNode(1);
		TreeNode nodeThree = new TreeNode(6);
		TreeNode nodeFour = new TreeNode(2);
		TreeNode nodeFive = new TreeNode(0);
		TreeNode nodeSix = new TreeNode(8);
		TreeNode nodeSeven = new TreeNode(7);
		TreeNode nodeEight = new TreeNode(4);
		
		root.setLeft(nodeOne);
		root.setRight(nodeTwo);
		
		nodeOne.setLeft(nodeThree);
		nodeOne.setRight(nodeFour);
		
		nodeTwo.setLeft(nodeFive);
		nodeTwo.setRight(nodeSix);
		
		nodeFour.setLeft(nodeSeven);
		nodeFour.setRight(nodeEight);
		
		List<Integer> expectedList = new ArrayList<Integer>();
		expectedList.add(7);
		expectedList.add(4);
		expectedList.add(1);
		
		List<Integer> resultantList = unit.distanceK(root, nodeOne, 2);
		Assert.assertEquals(expectedList.size(), resultantList.size());
		verify(expectedList, resultantList);
	}
	
	
	@Test
	public void shouldFetchNodesAtDistanceKCaseTwo() {
		TreeNode root = new TreeNode(0);
		TreeNode nodeOne = new TreeNode(1);
		TreeNode nodeTwo = new TreeNode(3);
		TreeNode nodeThree = new TreeNode(2);
		
		root.setLeft(nodeOne);
		nodeOne.setLeft(nodeTwo);
		nodeOne.setRight(nodeThree);
		
		List<Integer> expectedList = new ArrayList<Integer>();
		expectedList.add(1);
		
		List<Integer> resultantList = unit.distanceK(root, nodeThree, 1);
		Assert.assertEquals(expectedList.size(), resultantList.size());
		verify(expectedList, resultantList);
	}

	private void verify(List<Integer> expectedList, List<Integer> resultantList) {
		for (int i = 0; i < expectedList.size(); i++) {
			Assert.assertEquals(expectedList.get(i), resultantList.get(i));
		}
	}

}
