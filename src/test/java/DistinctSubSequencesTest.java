import org.junit.Before;
import org.junit.Test;

import dp.DistinctSubSequences;
import org.junit.Assert;

public class DistinctSubSequencesTest {
	
	private DistinctSubSequences unit;
	
	@Before
	public void setUp() {
		unit = new DistinctSubSequences();
	}
	
	@Test
	public void shouldComputeNumberOfDistinctSubsequencesCaseOne() {
		Assert.assertEquals(5, unit.numDistinct("babgbag", "bag"));
	}
	
	@Test
	public void shouldComputeNumberOfDistinctSubsequencesCaseTwo() {
		Assert.assertEquals(3, unit.numDistinct("rabbbit", "rabbit"));
	}
	
	@Test
	public void shouldComputeNumberOfDistinctSubsequencesCaseThree() {
		Assert.assertEquals(1, unit.numDistinct("eee", "eee"));
	}
	
	@Test
	public void shouldComputeNumberOfDistinctSubsequencesCaseFour() {
		Assert.assertEquals(2, unit.numDistinct("aabb", "abb"));
	}

}
