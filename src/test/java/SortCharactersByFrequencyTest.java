import org.junit.Before;
import org.junit.Test;

import org.junit.Assert;

public class SortCharactersByFrequencyTest {
	
	private SortCharactersByFrequency unit;
	
	@Before
	public void setUp() {
		unit = new SortCharactersByFrequency();
	}
	
	@Test
	public void shouldSortCharactersByFrequencyCaseOne() {
		Assert.assertEquals("eert", unit.frequencySort("tree"));
	}
	
	@Test
	public void shouldSortCharactersByFrequencyCaseTwo() {
		Assert.assertEquals("aaaccc", unit.frequencySort("cccaaa"));
	}
	
	@Test
	public void shouldSortCharactersByFrequencyCaseThree() {
		Assert.assertEquals("bbAa", unit.frequencySort("Aabb"));
	}

}
