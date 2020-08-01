import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import org.junit.Assert;
import list.ListNode;
import list.ReorderList;

public class ReorderListTest {
	
	private ReorderList unit;
	
	@Before
	public void setUp() {
		unit = new ReorderList();
	}
	
	@Test
	public void shouldReorderListCaseOne() {
		ListNode nodeOne = new ListNode(1);
		ListNode nodeTwo = new ListNode(2);
		ListNode nodeThree = new ListNode(3);
		ListNode nodeFour = new ListNode(4);
		
		nodeOne.setNext(nodeTwo);
		nodeTwo.setNext(nodeThree);
		nodeThree.setNext(nodeFour);
		
		List<Integer> expectedResult = new ArrayList<Integer>();
		expectedResult.add(1);
		expectedResult.add(4);
		expectedResult.add(2);
		expectedResult.add(3);
		
		unit.reorderList(nodeOne);
		
		verify(expectedResult, nodeOne);
	}
	
	@Test
	public void shouldReorderListCaseTwo() {
		ListNode nodeOne = new ListNode(1);
		ListNode nodeTwo = new ListNode(2);
		ListNode nodeThree = new ListNode(3);
		
		nodeOne.setNext(nodeTwo);
		nodeTwo.setNext(nodeThree);
		
		List<Integer> expectedResult = new ArrayList<Integer>();
		expectedResult.add(1);
		expectedResult.add(3);
		expectedResult.add(2);
		
		unit.reorderList(nodeOne);
		
		verify(expectedResult, nodeOne);
	}
	
	@Test
	public void shouldReorderListCaseThree() {
		ListNode nodeOne = new ListNode(1);
		ListNode nodeTwo = new ListNode(2);
		ListNode nodeThree = new ListNode(3);
		ListNode nodeFour = new ListNode(4);
		ListNode nodeFive = new ListNode(5);
		
		nodeOne.setNext(nodeTwo);
		nodeTwo.setNext(nodeThree);
		nodeThree.setNext(nodeFour);
		nodeFour.setNext(nodeFive);
		
		List<Integer> expectedResult = new ArrayList<Integer>();
		expectedResult.add(1);
		expectedResult.add(5);
		expectedResult.add(2);
		expectedResult.add(4);
		expectedResult.add(3);
		
		unit.reorderList(nodeOne);
		
		verify(expectedResult, nodeOne);
	}

	private void verify(List<Integer> expectedResult, ListNode nodeOne) {
		int index = 0;
		while (nodeOne != null) {
			Assert.assertEquals(expectedResult.get(index).intValue(), nodeOne.getVal());
			index++;
			nodeOne = nodeOne.getNext();
		}
	}

}
