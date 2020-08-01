import org.junit.Before;
import org.junit.Test;

import dp.LongestStringChain;
import org.junit.Assert;

public class LongestStringChainTest {
	
	private LongestStringChain unit;
	
	@Before
	public void setUp() {
		unit = new LongestStringChain();
	}
	
	@Test
	public void shouldFetchLengthOfLongestStringChainCaseOne() {
		String words[] = {"a","b","ba","bca","bda","bdca"};
		Assert.assertEquals(4, unit.longestStrChain(words));
	}
	
	
			
	@Test
	public void shouldFetchLengthOfLongestStringChainCaseTwo() {
		String words[] = {"sgtnz","sgtz","sgz","ikrcyoglz","ajelpkpx","ajelpkpxm","srqgtnz","srqgotnz","srgtnz","ijkrcyoglz"};
		Assert.assertEquals(6, unit.longestStrChain(words));
		}

}
