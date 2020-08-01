package pt;

import org.junit.Before;
import org.junit.Test;

import dp.PaintFence;
import org.junit.Assert;

public class PairFenceTest {
	
	private PaintFence unit;
	
	@Before
	public void setUp() {
		unit = new PaintFence();
	}
	
	@Test
	public void shouldComputeNumberOfWaysTopaintFenceCaseOne() {
		Assert.assertEquals(6, unit.numWays(3, 2));
	}
	
	@Test
	public void shouldComputeNumberOfWaysTopaintFenceCaseTwo() {
		Assert.assertEquals(24, unit.numWays(3, 3));
	}

}
