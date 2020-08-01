package pt;

import org.junit.Before;
import org.junit.Test;

import dp.PaintHouseTwo;
import org.junit.Assert;

public class PaintHouseTwoTest {
	
	private PaintHouseTwo unit;
	
	@Before
	public void setUp() {
		unit = new PaintHouseTwo();
	}
	
	@Test
	public void shouldComputeMinimumCostToPaintHouseCaseOne() {
		int costs[][] = {{1, 5, 3}, {2, 9, 4}};
		Assert.assertEquals(5, unit.minCostII(costs));
	}
	
	@Test
	public void shouldComputeMinimumCostToPaintHouseCaseTwo() {
		int costs[][] = {{1, 5, 2, 3}, {2, 9, 4, 7}};
		Assert.assertEquals(4, unit.minCostII(costs));
	}
	
	@Test
	public void shouldComputeMinimumCostToPaintHouseCaseThree() {
		int costs[][] = {{1, 5, 3}, {2, 9, 6}, {1, 7, 4}};
		Assert.assertEquals(8, unit.minCostII(costs));
	}
	
	@Test
	public void shouldComputeMinimumCostToPaintHouseCaseFour() {
		int costs[][] = {{8,16,12,18,9}, {19,18,8,2,8}, {8,5,5,13,4}, {15,9,3,19,2}, {8,7,1,8,17}, {8,2,8,15,5}, {8,17,1,15,3}, {8,8,5,5,16}, {2,2,18,2,9}};
		Assert.assertEquals(28, unit.minCostII(costs));
	}

}
