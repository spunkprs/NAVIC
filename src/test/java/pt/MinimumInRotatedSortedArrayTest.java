package pt;

import org.junit.Before;
import org.junit.Test;

import arrays.MinimumInRotatedSortedArray;
import org.junit.Assert;

public class MinimumInRotatedSortedArrayTest {
	
	private MinimumInRotatedSortedArray unit;
	
	@Before
	public void setUp() {
		unit = new MinimumInRotatedSortedArray();
	}
	
	
	@Test
	public void shouldFindMinimumElementInRotatedSortedArrayCaseOne() {
		int nums[] = {2, 1};
		Assert.assertEquals(1, unit.findMin(nums));
	}
	
	@Test
	public void shouldFindMinimumElementInRotatedSortedArrayCaseTwo() {
		int nums[] = {2, 3, 1};
		Assert.assertEquals(1, unit.findMin(nums));
	}
	
	@Test
	public void shouldFindMinimumElementInRotatedSortedArrayCaseThree() {
		int nums[] = {3, 1, 2};
		Assert.assertEquals(1, unit.findMin(nums));
	}


}
