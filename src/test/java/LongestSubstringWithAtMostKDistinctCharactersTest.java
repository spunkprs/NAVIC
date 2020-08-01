import org.junit.Before;
import org.junit.Test;

import arrays.LongestSubstringWithAtMostKDistinctCharacters;
import org.junit.Assert;

public class LongestSubstringWithAtMostKDistinctCharactersTest {
	
	private LongestSubstringWithAtMostKDistinctCharacters unit;
	
	@Before
	public void setUp() {
		unit = new LongestSubstringWithAtMostKDistinctCharacters();
	}
	
	@Test
	public void shouldFetchLengthOfLongestSubStringCasOne() {
		Assert.assertEquals(3, unit.lengthOfLongestSubstringKDistinct("eceba", 2));
	}
	
	@Test
	public void shouldFetchLengthOfLongestSubStringCaseTwo() {
		Assert.assertEquals(2, unit.lengthOfLongestSubstringKDistinct("aa", 1));
	}
	
	@Test
	public void shouldFetchLengthOfLongestSubStringCaseThree() {
		Assert.assertEquals(5, unit.lengthOfLongestSubstringKDistinct("ebebeckbab", 2));
	}
	
	@Test
	public void shouldFetchLengthOfLongestSubStringCaseFour() {
		Assert.assertEquals(2, unit.lengthOfLongestSubstringKDistinct("aa", 2));
	}

}
