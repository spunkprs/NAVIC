package pt;

import org.junit.Before;
import org.junit.Test;

import arrays.NumberOfIslands;
import org.junit.*;

public class NumberOfIslandsTest {
	
	private NumberOfIslands unit;
	
	@Before
	public void setUp() {
		unit = new NumberOfIslands();
	}
	
	@Test
	public void shouldFetchNumberOfIslandsCaseOne() {
		char grid[][] = {{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}};
		Assert.assertEquals(3, unit.numIslands(grid));
	}
	
	
	@Test
	public void shouldFetchNumberOfIslandsCaseTwo() {
		char grid[][] = {{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}};
		Assert.assertEquals(1, unit.numIslands(grid));
	}
	

}
