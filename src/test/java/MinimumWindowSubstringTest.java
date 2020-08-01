import org.junit.Before;
import org.junit.Test;

import org.junit.Assert;
import list.MinimumWindowSubstring;

public class MinimumWindowSubstringTest {
	
	private MinimumWindowSubstring unit;

	@Before
	public void setUp() {
		unit = new MinimumWindowSubstring();
	}
	
	@Test
	public void shouldFetchMinimumWindowSubStringCaseOne() {
		Assert.assertEquals("BANC", unit.minWindow("ADOBECODEBANC", "ABC"));
	}
	
	@Test
	public void shouldFetchMinimumWindowSubStringCaseTwo() {
		Assert.assertEquals("", unit.minWindow("a", "b"));
	}
	
	@Test
	public void shouldFetchMinimumWindowSubStringCaseThree() {
		Assert.assertEquals("aa", unit.minWindow("aa", "aa"));
	}
	
	@Test
	public void shouldFetchMinimumWindowSubStringCaseFour() {
		Assert.assertEquals("ab", unit.minWindow("bdab", "ab"));
	}
	
	@Test
	public void shouldFetchMinimumWindowSubStringCaseFive() {
		Assert.assertEquals("baca", unit.minWindow("acbbaca", "aba"));
	}
	
	@Test
	public void shouldFetchMinimumWindowSubStringCaseSix() {
		Assert.assertEquals("sk_not_what_your_c", unit.minWindow("ask_not_what_your_country_can_do_for_you_ask_what_you_can_do_for_your_country"
				,"ask_country"));
	}

}
