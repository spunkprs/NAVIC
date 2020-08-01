
import org.junit.Before;
import org.junit.Test;

import org.junit.Assert;
import list.AddTwoNumbers;
import list.AddTwoNumbers.ListNode;

public class AddTwoNumbersTest {
	
	private AddTwoNumbers unit;
	
	@Before
	public void setUp() {
		unit = new AddTwoNumbers();
	}
	
	@Test
	public void shouldAddTwoNumbersCaseOne() {
		ListNode nodeOne = new ListNode(2);
		ListNode nodeTwo = new ListNode(4);
		ListNode nodeThree = new ListNode(3);
		
		nodeOne.setNext(nodeTwo);
		nodeTwo.setNext(nodeThree);
		
		ListNode nodeFour = new ListNode(5);
		ListNode nodeFive = new ListNode(6);
		ListNode nodeSix = new ListNode(4);
		
		nodeFour.setNext(nodeFive);
		nodeFive.setNext(nodeSix);
		
		ListNode result = unit.addTwoNumbers(nodeOne, nodeFour);
		Assert.assertEquals("708", unit.appendNumbers(result));
	}
	
	@Test
	public void shouldAddTwoNumbersCaseTwo() {
		ListNode nodeOne = new ListNode(8);
		ListNode nodeTwo = new ListNode(9);
		ListNode nodeThree = new ListNode(9);
		
		nodeOne.setNext(nodeTwo);
		nodeTwo.setNext(nodeThree);
		
		ListNode nodeFour = new ListNode(9);
		ListNode nodeFive = new ListNode(2);
		ListNode nodeSix = new ListNode(2);
		
		nodeFour.setNext(nodeFive);
		nodeFive.setNext(nodeSix);
		
		ListNode result = unit.addTwoNumbers(nodeOne, nodeFour);
		Assert.assertEquals("7221", unit.appendNumbers(result));
	}
	
	@Test
	public void shouldAddTwoNumbersCaseThree() {
		ListNode nodeOne = new ListNode(1);
		
		ListNode nodeFour = new ListNode(9);
		ListNode nodeFive = new ListNode(9);
		
		nodeFour.setNext(nodeFive);
		
		ListNode result = unit.addTwoNumbers(nodeOne, nodeFour);
		Assert.assertEquals("001", unit.appendNumbers(result));
	}
	
	@Test
	public void shouldAddTwoNumbersCaseFour() {
		ListNode nodeOne = new ListNode(0);
		
		ListNode nodeFour = new ListNode(9);
		ListNode nodeFive = new ListNode(1);
		ListNode nodeSix = new ListNode(6);
		
		nodeFour.setNext(nodeFive);
		nodeFive.setNext(nodeSix);
		
		ListNode result = unit.addTwoNumbers(nodeOne, nodeFour);
		Assert.assertEquals("916", unit.appendNumbers(result));
	}
	
	@Test
	public void shouldAddTwoNumbersQuestionTwoCaseOne() {
		ListNode nodeOne = new ListNode(7);
		ListNode nodeTwo = new ListNode(2);
		ListNode nodeThree = new ListNode(4);
		ListNode nodeFour = new ListNode(3);
		
		nodeOne.setNext(nodeTwo);
		nodeTwo.setNext(nodeThree);
		nodeThree.setNext(nodeFour);
		
		ListNode nodeFive = new ListNode(5);
		ListNode nodeSix = new ListNode(6);
		ListNode nodeSeven = new ListNode(4);
		
		nodeFive.setNext(nodeSix);
		nodeSix.setNext(nodeSeven);
		
		ListNode result = unit.addTwoNumbersCaseTwo(nodeOne, nodeFive);
		Assert.assertEquals("7807", unit.appendNumbers(result));
	}
	
	@Test
	public void shouldAddTwoNumbersQuestionTwoCaseTwo() {
		ListNode nodeOne = new ListNode(5);
		ListNode nodeTwo = new ListNode(5);
		
		ListNode result = unit.addTwoNumbersCaseTwo(nodeOne, nodeTwo);
		Assert.assertEquals("10", unit.appendNumbers(result));
	}

}
