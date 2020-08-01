import org.junit.Before;
import org.junit.Test;

import graph.PathWithMaximumSum;
import org.junit.Assert;

public class PathWithMaximumSumTest {
	
	private PathWithMaximumSum unit;
	
	@Before
	public void setUp() {
		unit = new PathWithMaximumSum();
	}
	
	@Test
	public void shouldFetchPathWithMaximumSumCaseOne() {
		int input[][] = {{0, 6, 0}, {5, 8, 7}, {0, 9, 0}};
		Assert.assertEquals(24, unit.getMaximumGold(input));
	}
	
	@Test
	public void shouldFetchPathWithMaximumSumCaseTwo() {
		int input[][] = {{1, 0, 7}, {2, 0, 6}, {3, 4, 5}, {0, 3, 0}, {9, 0, 20}};
		Assert.assertEquals(28, unit.getMaximumGold(input));
	}

}
