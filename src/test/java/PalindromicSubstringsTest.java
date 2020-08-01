import org.junit.Before;
import org.junit.Test;

import dp.PalindromicSubstrings;
import org.junit.Assert;

public class PalindromicSubstringsTest {

	
	private PalindromicSubstrings unit;
	
	@Before
	public void setUp() {
		unit = new PalindromicSubstrings();
	}
	
	@Test
	public void shouldFetchNumberOfPalindromicSubsrtingsCaseOne() {
		Assert.assertEquals(3, unit.countSubstrings("abc"));
	}
	
	@Test
	public void shouldFetchNumberOfPalindromicSubsrtingsCaseTwo() {
		Assert.assertEquals(6, unit.countSubstrings("aaa"));
	}

}
