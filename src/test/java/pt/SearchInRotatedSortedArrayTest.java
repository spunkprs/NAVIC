package pt;

import org.junit.Before;
import org.junit.Test;

import arrays.SearchInRotatedSortedArray;
import org.junit.Assert;

public class SearchInRotatedSortedArrayTest {
	
	private SearchInRotatedSortedArray unit;
	
	@Before
	public void setUp() {
		unit = new SearchInRotatedSortedArray();
	}
	
	@Test
	public void shouldSearchInRotatedSortedArrayCaseOne() {
		int arr[] = {1, 3};
		Assert.assertEquals(-1, unit.search(arr, 0));
		Assert.assertEquals(-1, unit.searchApproachTwo(arr, 0));
	}
	
	@Test
	public void shouldSearchInRotatedSortedArrayCaseTwo() {
		int arr[] = {4, 5, 6, 7, 0, 1, 2};
		Assert.assertEquals(4, unit.search(arr, 0));
		Assert.assertEquals(4, unit.searchApproachTwo(arr, 0));
	}
	
	@Test
	public void shouldSearchInRotatedSortedArrayCaseThree() {
		int arr[] = {5, 1, 2, 3, 4};
		Assert.assertEquals(1, unit.search(arr, 1));
		Assert.assertEquals(1, unit.searchApproachTwo(arr, 1));
	}
	
	@Test
	public void shouldSearchInRotatedSortedArrayCaseFour() {
		int arr[] = {5, 1, 2, 3, 4};
		Assert.assertEquals(1, unit.search(arr, 1));
	}
	
	@Test
	public void shouldSearchInRotatedSortedArrayCaseFive() {
		int arr[] = {57,58,59,62,63,66,68,72,73,74,75,76,77,78,80,81,86,95,96,97,98,100,101,102,103,110,119,120,121,123,125,126,127,132,136,144,145,
				148,149,151,152,160,161,163,166,168,169,170,
				173,174,175,178,182,188,189,192,193,196,198,199,200,201,202,212,218,219,220,224,225,229,231,232,234,237,
				238,242,248,249,250,252,253,254,255,257,260,266,268,270,273,276,280,281,
				283,288,290,291,292,294,295,298,299,4,10,13,15,16,17,18,20,22,25,26,27,30,31,34,38,39,40,47,53,54};
		Assert.assertEquals(113, unit.searchApproachTwo(arr, 30));
	}
	
	@Test
	public void shouldSearchInRotatedSortedArrayCaseSix() {
		int arr[] = {2, 2, 2, 0, 2, 2};
		Assert.assertTrue(unit.searchApproachThree(arr, 0));
	}
	
	@Test
	public void shouldSearchInRotatedSortedArrayCaseSeven() {
		int arr[] = {1, 2, 1};
		Assert.assertFalse(unit.searchApproachThree(arr, 0));
	}

}
