import org.junit.Before;
import org.junit.Test;

import graph.ShortestSourceToDestinationLeetCode;
import org.junit.Assert;

public class ShortestSourceToDestinationLeetCodeTest {
	
	private ShortestSourceToDestinationLeetCode unit;
	
	@Before
	public void setUp() {
		unit = new ShortestSourceToDestinationLeetCode();
	}
	
	@Test
	public void shouldComputeShortestSourceToDestinationPathCaseOne() {
		int [][]matrix = {{1, 1, 1, 1}, {1, 0, 1, 1}, {0, 1, 1, 1}, {0, 1, 1, 1}};
		Assert.assertEquals(5, unit.shortestSourceToDestinationPathSum(matrix, 2, 1));
	}
	
	@Test
	public void shouldComputeShortestSourceToDestinationPathCaseTwo() {
		int [][]matrix = {{1, 1, 0, 0}, {0, 1, 0, 1}, {1, 1, 1, 0}, {1, 1, 1, 1}};
		Assert.assertEquals(6, unit.shortestSourceToDestinationPathSum(matrix, 3, 3));
	}

}
