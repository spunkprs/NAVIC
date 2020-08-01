import org.junit.Before;
import org.junit.Test;

import dp.HouseRobberThree;
import dp.HouseRobberThree.TreeNode;
import org.junit.Assert;

public class HouseRobberThreeTest {

	private HouseRobberThree unit;
	
	@Before
	public void setUp() {
		unit = new HouseRobberThree();
	}
	
	@Test
	public void shouldDoHouseRobbingCaseOne() {
		TreeNode root = new TreeNode(3);
		TreeNode nodeOne = new TreeNode(2);
		TreeNode nodeTwo = new TreeNode(3);
		TreeNode nodeThree = new TreeNode(3);
		TreeNode nodeFour = new TreeNode(1);
		
		root.setLeft(nodeOne);
		root.setRight(nodeTwo);
		nodeOne.setRight(nodeThree);
		nodeTwo.setRight(nodeFour);
		
		Assert.assertEquals(7, unit.rob(root));
	}
	
	@Test
	public void shouldDoHouseRobbingCaseTwo() {
		TreeNode root = new TreeNode(3);
		TreeNode nodeOne = new TreeNode(4);
		TreeNode nodeTwo = new TreeNode(5);
		TreeNode nodeThree = new TreeNode(1);
		TreeNode nodeFour = new TreeNode(3);
		TreeNode nodeFive = new TreeNode(1);
		
		root.setLeft(nodeOne);
		root.setRight(nodeTwo);
		nodeOne.setLeft(nodeThree);
		nodeOne.setRight(nodeFour);
		nodeTwo.setRight(nodeFive);
		
		Assert.assertEquals(9, unit.rob(root));
	}
	
	@Test
	public void shouldDoHouseRobbingCaseThree() {
		TreeNode root = new TreeNode(1);
		TreeNode nodeOne = new TreeNode(2);
		TreeNode nodeTwo = new TreeNode(3);
		
		root.setLeft(nodeOne);
		root.setRight(nodeTwo);
		Assert.assertEquals(5, unit.rob(root));
	}
	
	@Test
	public void shouldDoHouseRobbingCaseFour() {
		Assert.assertEquals(0, unit.rob(null));
	}
	
	
	@Test
	public void shouldDoHouseRobbingCaseFive() {
		
		TreeNode root = new TreeNode(1);
		TreeNode nodeOne = new TreeNode(2);
		root.setLeft(nodeOne);
		
		Assert.assertEquals(2, unit.rob(root));
	}
	
	@Test
	public void shouldDoHouseRobbingCaseSix() {
		
		TreeNode root = new TreeNode(4);
		TreeNode nodeOne = new TreeNode(2);
		TreeNode nodeTwo = new TreeNode(1);
		TreeNode nodeThree = new TreeNode(3);
		
		root.setLeft(nodeOne);
		nodeOne.setLeft(nodeTwo);
		nodeOne.setRight(nodeThree);
		
		Assert.assertEquals(8, unit.rob(root));
	}
	
	@Test
	public void shouldDoHouseRobbingCaseSeven() {
		
		TreeNode root = new TreeNode(5);
		TreeNode nodeOne = new TreeNode(2);
		TreeNode nodeTwo = new TreeNode(4);
		TreeNode nodeThree = new TreeNode(3);
		TreeNode nodeFour = new TreeNode(1);
		
		root.setLeft(nodeOne);
		nodeOne.setLeft(nodeTwo);
		nodeOne.setRight(nodeThree);
		nodeTwo.setLeft(nodeFour);
		
		Assert.assertEquals(12, unit.rob(root));
	}
}
