import org.junit.Before;
import org.junit.Test;

import arrays.SplitArrayIntoConsecutiveSubsequences;
import org.junit.Assert;

public class SplitArrayIntoConsecutiveSubsequencesTest {
	
	private SplitArrayIntoConsecutiveSubsequences unit;
	
	@Before
	public void setUp() {
		unit = new SplitArrayIntoConsecutiveSubsequences();
	}
	
	@Test
	public void shouldSplitArrayIntoConsecutiveSubsequencesCaseOne() {
		int nums[] = {1, 2, 3, 3, 4, 5};
		Assert.assertTrue(unit.isPossible(nums));
	} 
	
	@Test
	public void shouldSplitArrayIntoConsecutiveSubsequencesCaseTwo() {
		int nums[] = {1, 2, 3, 4, 4, 5};
		Assert.assertTrue(unit.isPossible(nums));
	}

}
