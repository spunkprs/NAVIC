import org.junit.Before;
import org.junit.Test;

import org.junit.Assert;

public class LongestPalindromicSubstringTest {
	
	private LongestPalindromicSubstring unit;
	
	@Before
	public void setUp() {
		unit = new LongestPalindromicSubstring();
	}
	
	@Test
	public void shouldFetchLPSCaseOne() {
		Assert.assertEquals("bab", unit.longestPalindrome("babad"));
	}
	
	@Test
	public void shouldFetchLPSCaseTwo() {
		Assert.assertEquals("anana", unit.longestPalindrome("banana"));
	}

}
