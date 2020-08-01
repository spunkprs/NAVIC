package pt;

import org.junit.Before;
import org.junit.Test;

import dp.LargestRectangleInHistogram;
import org.junit.Assert;

public class LargestRectangleInHistogramTest {
	
	private LargestRectangleInHistogram unit;
	
	@Before
	public void setUp() {
		unit = new LargestRectangleInHistogram();
	}
	
	@Test
	public void shouldFetchMaxRectangleAreaWithInTheHistogramCaseOne() {
		int arr[] = {2, 3, 5, 6, 2, 3};
		Assert.assertEquals(12, unit.largestRectangleArea(arr));
	}
	
	@Test
	public void shouldFetchMaxRectangleAreaWithInTheHistogramCaseTwo() {
		int arr[] = {2, 1, 5, 6, 2, 3};
		Assert.assertEquals(10, unit.largestRectangleArea(arr));
	}

}
