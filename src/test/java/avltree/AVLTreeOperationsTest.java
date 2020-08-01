package avltree;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

public class AVLTreeOperationsTest {
	
	private AVLTreeOperations unit;
	
	@Before
	public void setUp() {
		unit = new AVLTreeOperations();
	}
	
	@Test
	public void shouldPerformInsertionInAVLTreeCaseOne() {
		unit.insertNodeInTree(43);
		List<Integer> expectedResult = new ArrayList<Integer>();
		expectedResult.add(43);
		List<Integer> actualResult = unit.performInorderTravsersal();
		verifyResult(expectedResult, actualResult);
	}
	
	@Test
	public void shouldPerformInsertionInAVLTreeCaseTwo() {
		unit.insertNodeInTree(43);
		unit.insertNodeInTree(18);
		unit.insertNodeInTree(22);
		List<Integer> expectedResult = new ArrayList<Integer>();
		expectedResult.add(18);
		expectedResult.add(22);
		expectedResult.add(43);
		List<Integer> actualResult = unit.performInorderTravsersal();
		verifyResult(expectedResult, actualResult);
	}
	
	@Test
	public void shouldPerformInsertionInAVLTreeCaseThree() {
		unit.insertNodeInTree(43);
		unit.insertNodeInTree(18);
		unit.insertNodeInTree(22);
		unit.insertNodeInTree(9);
		unit.insertNodeInTree(21);
		unit.insertNodeInTree(6);
		unit.insertNodeInTree(8);
		List<Integer> expectedResult = new ArrayList<Integer>();
		expectedResult.add(6);
		expectedResult.add(8);
		expectedResult.add(9);
		expectedResult.add(18);
		expectedResult.add(21);
		expectedResult.add(22);
		expectedResult.add(43);
		List<Integer> actualResult = unit.performInorderTravsersal();
		verifyResult(expectedResult, actualResult);
	}
	
	@Test
	public void shouldPerformInsertionInAVLTreeCaseFour() {
		unit.insertNodeInTree(43);
		unit.insertNodeInTree(18);
		unit.insertNodeInTree(22);
		unit.insertNodeInTree(9);
		unit.insertNodeInTree(21);
		unit.insertNodeInTree(6);
		unit.insertNodeInTree(8);
		unit.insertNodeInTree(20);
		unit.insertNodeInTree(63);
		unit.insertNodeInTree(50);
		unit.insertNodeInTree(62);
		unit.insertNodeInTree(51);
		List<Integer> expectedResult = new ArrayList<Integer>();
		expectedResult.add(6);
		expectedResult.add(8);
		expectedResult.add(9);
		expectedResult.add(18);
		expectedResult.add(20);
		expectedResult.add(21);
		expectedResult.add(22);
		expectedResult.add(43);
		expectedResult.add(50);
		expectedResult.add(51);
		expectedResult.add(62);
		expectedResult.add(63);
		List<Integer> actualResult = unit.performInorderTravsersal();
		verifyResult(expectedResult, actualResult);
	}
	
	private void verifyResult(List<Integer> expectedResult, List<Integer> actualResult) {
		Assert.assertEquals(expectedResult.size(), actualResult.size());
		for (int i = 0; i < expectedResult.size(); i++) {
			Assert.assertEquals(expectedResult.get(i), actualResult.get(i));
		}
	}
}
