package pt;

import org.junit.Before;
import org.junit.Test;

import greedy.ShortestPathInBinaryMaze;
import org.junit.Assert;

public class ShortestPathInBinaryMazeTest {
	
	private ShortestPathInBinaryMaze unit;
	
	@Before
	public void setUp() {
		unit = new ShortestPathInBinaryMaze();
	}
	
	@Test
	public void shouldFetchMiniCostPathInBinaryMazeCasOne() {
		int matrix[][] = {{1, 1, 1, 1, 1}, {1, 1, 0, 1, 1}, {0, 1, 0, 1, 1}, {1, 1, 1, 1, 1}};
		Assert.assertEquals(5, unit.fetchShortestPathInBinaryMaze(matrix, 2, 3));
	}
 
}
