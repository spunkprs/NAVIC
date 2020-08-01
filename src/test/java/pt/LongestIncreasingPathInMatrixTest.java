package pt;

import org.junit.Before;
import org.junit.Test;

import arrays.LongestIncreasingPathInMatrix;
import org.junit.Assert;

public class LongestIncreasingPathInMatrixTest {

	private LongestIncreasingPathInMatrix unit;
	
	@Before
	public void setUp() {
		unit = new LongestIncreasingPathInMatrix();
	}
	
	@Test
	public void shouldComputeLongestIncreasingPathCaseOne() {
		int matrix[][] = {{9, 9, 4}, {6, 6, 8}, {2, 1, 1}};
		Assert.assertEquals(4, unit.longestIncreasingPath(matrix));
	}
	
	@Test
	public void shouldComputeLongestIncreasingPathCaseTwo() {
		int matrix[][] = {{3, 4, 5}, {3, 2, 6}, {2, 2, 1}};
		Assert.assertEquals(4, unit.longestIncreasingPath(matrix));
	}
}
