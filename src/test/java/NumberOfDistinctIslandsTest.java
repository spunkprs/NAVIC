import org.junit.Before;
import org.junit.Test;

import graph.NumberOfDistinctIslands;
import org.junit.Assert;

public class NumberOfDistinctIslandsTest {
	
	private NumberOfDistinctIslands unit;
	
	@Before
	public void setUp() {
		unit = new NumberOfDistinctIslands();
	}
	
	@Test
	public void shouldFetchNumberOfUniqueIslandsCaseOne() {
		int grid[][] = {{1, 1, 0, 0, 0}, {1, 1, 0, 0, 0}, {0, 0, 0, 1, 1}, {0, 0, 0, 1, 1}};
		Assert.assertEquals(1, unit.numDistinctIslands(grid));
	}
	
	@Test
	public void shouldFetchNumberOfUniqueIslandsCaseTwo() {
		int grid[][] = {{1, 1, 0, 1, 1}, {1, 0, 0, 0, 0}, {0, 0, 0, 0, 1}, {1, 1, 0, 1, 1}};
		Assert.assertEquals(3, unit.numDistinctIslands(grid));
	}

}
