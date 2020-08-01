import org.junit.Before;
import org.junit.Test;

import dp.HouseRobber;
import org.junit.Assert;

public class HouseRobberTest {
	
	private HouseRobber unit;
	
	@Before
	public void setUp() {
		unit = new HouseRobber();
	}
	
	@Test
	public void shouldComputeMaxAmountOfMoneyWhileRobbingCaseOneLevelOne() {
		int nums[] = {1, 2, 3, 1};
		Assert.assertEquals(4, unit.rob(nums));
	}
	
	@Test
	public void shouldComputeMaxAmountOfMoneyWhileRobbingCaseTwoLevelOne() {
		int nums[] = {2, 3, 2};
		Assert.assertEquals(4, unit.rob(nums));
	}
	
	@Test
	public void shouldComputeMaxAmountOfMoneyWhileRobbingCaseThreeLevelOne() {
		int nums[] = {2, 7, 9, 3, 1};
		Assert.assertEquals(12, unit.rob(nums));
	}
	
	
	@Test
	public void shouldComputeMaxAmountOfMoneyWhileRobbingCaseFourLevelOne() {
		int nums[] = {2, 1, 1, 2};
		Assert.assertEquals(4, unit.rob(nums));
	}
	
	@Test
	public void shouldComputeMaxAmountOfMoneyWhileRobbingCaseOneLevelTwo() {
		int nums[] = {1, 2, 3, 1};
		Assert.assertEquals(4, unit.robTwo(nums));
	}
	
	@Test
	public void shouldComputeMaxAmountOfMoneyWhileRobbingCaseTwoLevelTwo() {
		int nums[] = {2, 3, 2};
		Assert.assertEquals(3, unit.robTwo(nums));
	}
	
	@Test
	public void shouldComputeMaxAmountOfMoneyWhileRobbingCaseThreeLevelTwo() {
		int nums[] = {2, 7, 9, 3, 1};
		Assert.assertEquals(11, unit.robTwo(nums));
	}

}
