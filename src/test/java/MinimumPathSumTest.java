import org.junit.Before;
import org.junit.Test;

import dp.MinimumPathSum;
import org.junit.Assert;

public class MinimumPathSumTest {
	
	private MinimumPathSum unit;
	
	@Before
	public void setUp() {
		unit = new MinimumPathSum();
	}
	
	@Test
	public void shouldFindOutMinimumPathSumCaseOne() {
		int grid[][] = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
		Assert.assertEquals(7, unit.minPathSum(grid));
	}

}
