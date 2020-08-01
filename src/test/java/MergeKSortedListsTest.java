import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import org.junit.Assert;
import list.ListNode;
import list.MergeKSortedLists;

public class MergeKSortedListsTest {
	
	private MergeKSortedLists unit;

	@Before
	public void setUp() {
		unit = new MergeKSortedLists();
	}
	
	
	@Test
	public void shouldSortKSortedListsCaseOne() {
		ListNode lists[] = new ListNode[3];
		
		ListNode headOneListOne = new ListNode(1);
		ListNode nodeTwoListOne = new ListNode(3);
		ListNode nodeThreeListOne = new ListNode(5);
		
		headOneListOne.setNext(nodeTwoListOne);
		nodeTwoListOne.setNext(nodeThreeListOne);
		
		ListNode headOneListTwo = new ListNode(0);
		ListNode nodeTwoListTwo = new ListNode(2);
		ListNode nodeThreeListTwo = new ListNode(7);
		
		headOneListTwo.setNext(nodeTwoListTwo);
		nodeTwoListTwo.setNext(nodeThreeListTwo);
		
		ListNode headOneListThree = new ListNode(1);
		ListNode nodeTwoListThree = new ListNode(9);
		ListNode nodeThreeListThree = new ListNode(11);
		
		headOneListThree.setNext(nodeTwoListThree);
		nodeTwoListThree.setNext(nodeThreeListThree);
		
		lists[0] = headOneListOne;
		lists[1] = headOneListTwo;
		lists[2] = headOneListThree;
		
		List<Integer> expectedResult = new ArrayList<Integer>();
		expectedResult.add(0);
		expectedResult.add(1);
		expectedResult.add(1);
		expectedResult.add(2);
		expectedResult.add(3);
		expectedResult.add(5);
		expectedResult.add(7);
		expectedResult.add(9);
		expectedResult.add(11);
		
		ListNode expectedHead = unit.mergeKLists(lists);
		verifyResult(expectedResult, expectedHead);
	}
	
	
	@Test
	public void shouldSortKSortedListsCaseThree() {
		ListNode lists[] = new ListNode[0];
		ListNode expectedHead = unit.mergeKLists(lists);
		verifyResult(null, expectedHead);
	}
	
	@Test
	public void shouldSortKSortedListsCaseFour() {
		ListNode lists[] = new ListNode[3];
		
		ListNode headOneListTwo = new ListNode(-2);
		
		ListNode headOneListThree = new ListNode(-3);
		ListNode nodeTwoListThree = new ListNode(-2);
		ListNode nodeThreeListThree = new ListNode(1);
		
		headOneListThree.setNext(nodeTwoListThree);
		nodeTwoListThree.setNext(nodeThreeListThree);
		
		lists[0] = null;
		lists[1] = headOneListTwo;
		lists[2] = headOneListThree;
		
		List<Integer> expectedResult = new ArrayList<Integer>();
		expectedResult.add(-3);
		expectedResult.add(-2);
		expectedResult.add(-2);
		expectedResult.add(1);
		
		
		ListNode expectedHead = unit.mergeKLists(lists);
		verifyResult(expectedResult, expectedHead);
	}
	
	@Test
	public void shouldSortKSortedListsCaseFive() {
		ListNode lists[] = new ListNode[2];
		
		ListNode headOneListOne = new ListNode(-2);
		ListNode nodeTwoListOne = new ListNode(-2);
		
		headOneListOne.setNext(nodeTwoListOne);
		
		ListNode headOneListTwo = new ListNode(-3);
		
		lists[0] = headOneListOne;
		lists[1] = headOneListTwo;
		
		
		List<Integer> expectedResult = new ArrayList<Integer>();
		expectedResult.add(-3);
		expectedResult.add(-2);
		expectedResult.add(-2);
		
		ListNode expectedHead = unit.mergeKLists(lists);
		verifyResult(expectedResult, expectedHead);
	}
	
	@Test
	public void shouldSortKSortedListsCaseTwo() {
		ListNode lists[] = new ListNode[6];
		
		ListNode headOneListOne = new ListNode(1);
		ListNode nodeTwoListOne = new ListNode(3);
		ListNode nodeThreeListOne = new ListNode(5);
		
		headOneListOne.setNext(nodeTwoListOne);
		nodeTwoListOne.setNext(nodeThreeListOne);
		
		ListNode headOneListTwo = new ListNode(0);
		ListNode nodeTwoListTwo = new ListNode(2);
		ListNode nodeThreeListTwo = new ListNode(7);
		
		headOneListTwo.setNext(nodeTwoListTwo);
		nodeTwoListTwo.setNext(nodeThreeListTwo);
		
		ListNode headOneListThree = new ListNode(1);
		ListNode nodeTwoListThree = new ListNode(9);
		ListNode nodeThreeListThree = new ListNode(11);
		
		headOneListThree.setNext(nodeTwoListThree);
		nodeTwoListThree.setNext(nodeThreeListThree);
		
		ListNode headOneListFour = new ListNode(-2);
		ListNode nodeTwoListFour = new ListNode(-1);
		ListNode nodeThreeListFour = new ListNode(8);
		
		headOneListFour.setNext(nodeTwoListFour);
		nodeTwoListFour.setNext(nodeThreeListFour);
		
		ListNode headOneListFive = new ListNode(8);
		ListNode nodeTwoListFive = new ListNode(10);
		ListNode nodeThreeListFive = new ListNode(21);
		
		headOneListFive.setNext(nodeTwoListFive);
		nodeTwoListFive.setNext(nodeThreeListFive);
		
		ListNode headOneListSix = new ListNode(2);
		ListNode nodeTwoListSix = new ListNode(22);
		ListNode nodeThreeListSix = new ListNode(122);
		
		headOneListSix.setNext(nodeTwoListSix);
		nodeTwoListSix.setNext(nodeThreeListSix);
		
		lists[0] = headOneListOne;
		lists[1] = headOneListTwo;
		lists[2] = headOneListThree;
		lists[3] = headOneListFour;
		lists[4] = headOneListFive;
		lists[5] = headOneListSix;
		
		List<Integer> expectedResult = new ArrayList<Integer>();
		expectedResult.add(-2);
		expectedResult.add(-1);
		expectedResult.add(0);
		expectedResult.add(1);
		expectedResult.add(1);
		expectedResult.add(2);
		expectedResult.add(2);
		expectedResult.add(3);
		expectedResult.add(5);
		expectedResult.add(7);
		expectedResult.add(8);
		expectedResult.add(8);
		expectedResult.add(9);
		expectedResult.add(10);
		expectedResult.add(11);
		expectedResult.add(21);
		expectedResult.add(22);
		expectedResult.add(122);
		
		ListNode expectedHead = unit.mergeKLists(lists);
		verifyResult(expectedResult, expectedHead);
	}


	private void verifyResult(List<Integer> expectedResult, ListNode expectedHead) {
		ListNode node = expectedHead;
		int num = 0;
		
		while (node != null && num < expectedResult.size()) {
			Assert.assertEquals(expectedResult.get(num).intValue(), node.getVal());
			node = node.getNext();
			num++;
		}
		
	}
}
