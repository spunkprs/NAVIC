import org.junit.Before;
import org.junit.Test;

import binarytree.PopulatingNextRightPointers;
import org.junit.Assert;

public class PopulatingNextRightPointersTest {
	
	private PopulatingNextRightPointers unit;
	
	@Before
	public void setUp() {
		unit = new PopulatingNextRightPointers();
	}
	
	@Test
	public void shouldPopulateNextRightPointerCaseOne() {
		PopulatingNextRightPointers.Node root = new PopulatingNextRightPointers.Node(1);
		
		PopulatingNextRightPointers.Node childOne = new PopulatingNextRightPointers.Node(2);
		PopulatingNextRightPointers.Node childTwo = new PopulatingNextRightPointers.Node(3);
		
		PopulatingNextRightPointers.Node childThree = new PopulatingNextRightPointers.Node(4);
		PopulatingNextRightPointers.Node childFour = new PopulatingNextRightPointers.Node(5);
		
		PopulatingNextRightPointers.Node childFive = new PopulatingNextRightPointers.Node(7);
		
		root.left = childOne;
		root.right = childTwo;
		
		childOne.left = childThree;
		childOne.right = childFour;
		
		childTwo.right = childFive;
		
		Assert.assertNotNull(unit.connect(root));
	}
	
	@Test
	public void shouldPopulateNextRightPointerCaseTwo() {
		PopulatingNextRightPointers.Node root = new PopulatingNextRightPointers.Node(1);
		
		PopulatingNextRightPointers.Node childOne = new PopulatingNextRightPointers.Node(2);
		PopulatingNextRightPointers.Node childTwo = new PopulatingNextRightPointers.Node(3);
		
		PopulatingNextRightPointers.Node childThree = new PopulatingNextRightPointers.Node(4);
		PopulatingNextRightPointers.Node childFour = new PopulatingNextRightPointers.Node(5);
		
		
		root.left = childOne;
		root.right = childTwo;
		
		childOne.left = childThree;
		childOne.right = childFour;
	
		Assert.assertNotNull(unit.connect(root));
	}
	
	@Test
	public void shouldPopulateNextRightPointerCaseThree() {
		PopulatingNextRightPointers.Node root = new PopulatingNextRightPointers.Node(1);
		
		PopulatingNextRightPointers.Node childOne = new PopulatingNextRightPointers.Node(2);
		PopulatingNextRightPointers.Node childTwo = new PopulatingNextRightPointers.Node(3);
		
		PopulatingNextRightPointers.Node childThree = new PopulatingNextRightPointers.Node(4);
		PopulatingNextRightPointers.Node childFour = new PopulatingNextRightPointers.Node(5);
		
		
		root.left = childOne;
		root.right = childTwo;
		
		childOne.left = childThree;
		childOne.right = childFour;
	
		Assert.assertNotNull(unit.connect(root));
	}
	
	@Test
	public void shouldPopulateNextRightPointerCaseFour() {
		PopulatingNextRightPointers.Node root = new PopulatingNextRightPointers.Node(2);
		
		PopulatingNextRightPointers.Node childOne = new PopulatingNextRightPointers.Node(1);
		PopulatingNextRightPointers.Node childTwo = new PopulatingNextRightPointers.Node(3);
		
		PopulatingNextRightPointers.Node childThree = new PopulatingNextRightPointers.Node(0);
		PopulatingNextRightPointers.Node childFour = new PopulatingNextRightPointers.Node(7);
		
		PopulatingNextRightPointers.Node childFive = new PopulatingNextRightPointers.Node(9);
		PopulatingNextRightPointers.Node childSix = new PopulatingNextRightPointers.Node(1);
		
		PopulatingNextRightPointers.Node childSeven = new PopulatingNextRightPointers.Node(2);
		PopulatingNextRightPointers.Node childEight = new PopulatingNextRightPointers.Node(1);
		
		PopulatingNextRightPointers.Node childNine = new PopulatingNextRightPointers.Node(0);
		PopulatingNextRightPointers.Node childTen = new PopulatingNextRightPointers.Node(8);
		
		PopulatingNextRightPointers.Node childEleven = new PopulatingNextRightPointers.Node(8);
		PopulatingNextRightPointers.Node childTwelve = new PopulatingNextRightPointers.Node(7);
		
		
		root.left = childOne;
		root.right = childTwo;
		
		childOne.left = childThree;
		childOne.right = childFour;
		
		childTwo.left = childFive;
		childTwo.right = childSix;
		
		childThree.left = childSeven;
		
		childFour.left = childEight;
		childFour.right = childNine;
		
		childSix.left = childTen;
		childSix.right = childEleven;
		
		childNine.left = childTwelve;
	
		Assert.assertNotNull(unit.connect(root));
	}

}
