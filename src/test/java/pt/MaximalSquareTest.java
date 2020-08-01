package pt;

import org.junit.Before;
import org.junit.Test;

import dp.MaximalSquare;
import org.junit.Assert;

public class MaximalSquareTest {
	
	private MaximalSquare unit;
	
	@Before
	public void setUp() {
		unit = new MaximalSquare();
	}
	
	@Test
	public void shouldFetchMaximalSquareCaseOne() {
		char matrix[][] = {{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}};
		Assert.assertEquals(4, unit.maximalSquare(matrix));
	}
	
	@Test
	public void shouldFetchMaximalSquareCaseTwo() {
		char matrix[][] = {{'1', '0', '1', '0', '0'}, {'1', '1', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '1', '1', '1', '1'}};
		Assert.assertEquals(9, unit.maximalSquare(matrix));
	}

}
