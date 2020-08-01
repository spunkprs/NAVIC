package pt;

import org.junit.Before;
import org.junit.Test;

import arrays.SlidingWindowMaximum;
import org.junit.Assert;

public class SlidingWindowMaximumTest {
	
	private SlidingWindowMaximum unit;
	
	@Before
	public void setUp() {
		unit = new SlidingWindowMaximum();
	}
	
	@Test
	public void shouldFindSlidingWindowMaximumCaseOne() {
		int nums[] = {9, 7, 2, 4, 6, 8, 2, 1, 5};
		int expectedResult[] = {9, 7, 6, 8, 8, 8, 5};
		
		verify(expectedResult, unit.maxSlidingWindow(nums, 3));
	}
	
	@Test
	public void shouldFindSlidingWindowMaximumCaseTwo() {
		int nums[] = {1,3,-1,-3,5,3,6,7};
		int expectedResult[] = {3, 3, 5, 5, 6, 7};
		
		verify(expectedResult, unit.maxSlidingWindow(nums, 3));
	}

	private void verify(int[] expectedResult, int[] actualResult) {
		Assert.assertEquals(expectedResult.length, actualResult.length);
		for (int i = 0; i < expectedResult.length; i++) {
			Assert.assertEquals(expectedResult[i], actualResult[i]);
		}
	}

}
