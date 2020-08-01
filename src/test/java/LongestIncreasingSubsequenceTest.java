import org.junit.Before;
import org.junit.Test;

import dp.LongestIncreasingSubsequence;
import org.junit.Assert;

public class LongestIncreasingSubsequenceTest {
	
	private LongestIncreasingSubsequence unit;
	
	@Before
	public void setUp() {
		unit = new LongestIncreasingSubsequence();
	}
	
	@Test
	public void shouldFetchLengthOfLISCaseOne() {
		int arr[] = {10,9,2,5,3,7,101,18};
		Assert.assertEquals(4, unit.lengthOfLIS(arr));
	}
	
	@Test
	public void shouldFetchLengthOfLISCaseTwo() {
		int arr[] = {1,3,6,7,9,4,10,5,6};
		Assert.assertEquals(6, unit.lengthOfLIS(arr));
	}
}
