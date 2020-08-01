import org.junit.Before;
import org.junit.Test;

import dp.MaximumSumSubArray;
import org.junit.Assert;

public class MaximumSumSubArrayTest {
	
	private MaximumSumSubArray unit;
	
	@Before
	public void setUp() {
		unit = new MaximumSumSubArray();
	}
	
	@Test
	public void shouldFindMaximumSumCaseOne() {
		int nums[] = {-6, 4, -2, 3, -2};
		Assert.assertEquals(5, unit.maxSubArrayApproachOne(nums));
		Assert.assertEquals(5, unit.maxSubArrayApproachTwo(nums));
	}
	
	@Test
	public void shouldFindMaximumSumCaseTwo() {
		int nums[] = {-2,1,-3,4,-1,2,1,-5,4};
		Assert.assertEquals(6, unit.maxSubArrayApproachOne(nums));
		Assert.assertEquals(6, unit.maxSubArrayApproachTwo(nums));
	}
	
	@Test
	public void shouldFindMaximumSumCaseThree() {
		int nums[] = {-2,-1};
		Assert.assertEquals(-1, unit.maxSubArrayApproachOne(nums));
		Assert.assertEquals(-1, unit.maxSubArrayApproachTwo(nums));
	}
	
	@Test
	public void shouldFindMaximumSumCaseFour() {
		int nums[] = {-1,-2};
		Assert.assertEquals(-1, unit.maxSubArrayApproachTwo(nums));
	}

}
