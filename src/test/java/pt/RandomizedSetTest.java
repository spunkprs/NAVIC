package pt;

import org.junit.Before;
import org.junit.Test;

import arrays.RandomizedSet;
import org.junit.Assert;

public class RandomizedSetTest {
	
	private RandomizedSet unit;
	
	@Before
	public void setUp() {
		unit = new RandomizedSet();
	}
	
	@Test
	public void shouldPerformOperationsCaseOne() {
		unit = new RandomizedSet();
		Assert.assertTrue(unit.insert(1));
		Assert.assertFalse(unit.remove(2));
		Assert.assertTrue(unit.insert(2));
		Assert.assertEquals(2, unit.getRandom());
		Assert.assertTrue(unit.remove(1));
		Assert.assertFalse(unit.insert(2));
	}

}
