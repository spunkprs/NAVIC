import org.junit.Before;
import org.junit.Test;

import dp.PaintHouse;
import org.junit.Assert;

public class PaintHouseTest {
	
	private PaintHouse unit;
	
	@Before
	public void setUp() {
		unit = new PaintHouse();
	} 
	
	@Test
	public void shouldComputeMinimumCostToPaintHousesCaseOne() {
		int matrix[][] = {{17,2,17}, {16,16,5}, {14,3,19}};
		Assert.assertEquals(10, unit.minCost(matrix));
		Assert.assertEquals(10, unit.minCostApproachTwo(matrix));
	}

}
