import org.junit.Before;
import org.junit.Test;

import arrays.ReverseWordsInString;
import org.junit.Assert;

public class ReverseWordsInStringTest {
	
	private ReverseWordsInString unit;
	
	@Before
	public void setUp() {
		unit = new ReverseWordsInString();
	}
	
	@Test
	public void shouldReverseWordsInAStringCaseOne() {
		Assert.assertEquals("example good a", unit.reverseWords("a good       example"));
	}

}
