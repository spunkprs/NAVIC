package pt;

import org.junit.Before;
import org.junit.Test;

import arrays.RandomizedSetWithDuplicates;
import org.junit.Assert;

public class RandomizedSetWithDuplicatesTest {
	
	private RandomizedSetWithDuplicates unit;
	
	@Before
	public void setUp() {
		unit = new RandomizedSetWithDuplicates();
	}
	
	@Test
	public void shouldPerformOperationsCaseOne() {
		Assert.assertTrue(unit.insert(0));
		Assert.assertTrue(unit.insert(1));
		Assert.assertTrue(unit.remove(0));
		Assert.assertTrue(unit.insert(2));
		Assert.assertTrue(unit.remove(1));
		Assert.assertEquals(2, unit.getRandom());
	}
	
	
	@Test
	public void shouldPerformOperationsCaseTwo() {
		Assert.assertTrue(unit.insert(1));
		Assert.assertFalse(unit.insert(1));
		Assert.assertTrue(unit.insert(2));
		Assert.assertFalse(unit.insert(2));
		Assert.assertFalse(unit.insert(2));
		
		Assert.assertTrue(unit.remove(1));
		Assert.assertTrue(unit.remove(1));
		Assert.assertTrue(unit.remove(2));
		
		Assert.assertTrue(unit.insert(1));
		Assert.assertTrue(unit.remove(2));
	}
	
	

}
