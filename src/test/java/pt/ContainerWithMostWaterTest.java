package pt;

import org.junit.Before;
import org.junit.Test;

import arrays.ContainerWithMostWater;
import org.junit.Assert;

public class ContainerWithMostWaterTest {
	
	private ContainerWithMostWater unit;
	
	@Before
	public void setUp() {
		unit = new ContainerWithMostWater();
	}
	
	@Test
	public void shouldTestContainerWithMostWaterCaseOne() {
		int num[] = {1,8,6,2,5,4,8,3,7};
		Assert.assertEquals(49, unit.maxArea(num));		
	}

}
