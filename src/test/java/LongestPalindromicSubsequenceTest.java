import org.junit.Before;
import org.junit.Test;

import dp.LongestPalindromicSubsequence;
import org.junit.Assert;

public class LongestPalindromicSubsequenceTest {
	
	private LongestPalindromicSubsequence unit;
	
	@Before
	public void setUp() {
		unit = new LongestPalindromicSubsequence();
	}
	
	@Test
	public void shouldFetchLengthOfLongestPalindromicSubsequenceCaseOne() {
		Assert.assertEquals(4, unit.longestPalindromeSubseq("bbbab"));
	}
	
	@Test
	public void shouldFetchLengthOfLongestPalindromicSubsequenceCaseTwo() {
		Assert.assertEquals(2, unit.longestPalindromeSubseq("cbbd"));
	}

}
