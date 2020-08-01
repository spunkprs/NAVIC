package pt;

import org.junit.Before;
import org.junit.Test;

import arrays.DiagonalTraversalOfMatrix;
import org.junit.Assert;

public class DiagonalTraversalOfMatrixTest {
	
	private DiagonalTraversalOfMatrix unit;
	
	@Before
	public void setUp() {
		unit = new DiagonalTraversalOfMatrix();
	}
	
	@Test
	public void shouldDoDiagonalTraversalOfMatrixCaseOne() {
		int matrix[][] = {{1, 2, 3 ,4}, {6, 8, 5, 7}, {1, 1, 5, 6}};
		int resultArray[] = {1, 2, 6, 1, 8, 3, 4, 5, 1, 5, 7, 6};
		int actualArray[] = unit.findDiagonalOrder(matrix);
		verify(resultArray, actualArray);
	}
	
	@Test
	public void shouldDoDiagonalTraversalOfMatrixCaseTwo() {
		int matrix[][] = {{1, 2, 3 ,4}, {5, 6, 7, 8}, {1, 2, 3, 4}, {5, 6, 7, 8}};
		int resultArray[] = {1, 2, 5, 1, 6, 3, 4, 7, 2, 5, 6, 3, 8, 4, 7, 8};
		int actualArray[] = unit.findDiagonalOrder(matrix);
		verify(resultArray, actualArray);
	}
	
	@Test
	public void shouldDoDiagonalTraversalOfMatrixCaseThree() {
		int matrix[][] = {{1, 2}, {3, 4}, {5, 6}};
		int resultArray[] = {1, 2, 3, 5, 4, 6};
		int actualArray[] = unit.findDiagonalOrder(matrix);
		verify(resultArray, actualArray);
	}
	
	@Test
	public void shouldDoDiagonalTraversalOfMatrixCaseFour() {
		int matrix[][] = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
		int resultArray[] = {1, 2, 4, 7, 5, 3, 6, 8, 9};
		int actualArray[] = unit.findDiagonalOrder(matrix);
		verify(resultArray, actualArray);
	}
	
	@Test
	public void shouldDoDiagonalTraversalOfMatrixCaseFive() {
		int matrix[][] = {{1}, {2}, {3}};
		int resultArray[] = {1, 2, 3};
		int actualArray[] = unit.findDiagonalOrder(matrix);
		verify(resultArray, actualArray);
	}
	
	@Test
	public void shouldDoDiagonalTraversalOfMatrixCaseSix() {
		int matrix[][] = {{1, 2, 3}};
		int resultArray[] = {1, 2, 3};
		int actualArray[] = unit.findDiagonalOrder(matrix);
		verify(resultArray, actualArray);
	}

	private void verify(int[] resultArray, int[] actualArray) {
		Assert.assertEquals(resultArray.length, actualArray.length);
		for (int i = 0; i < resultArray.length; i++) {
			Assert.assertEquals(resultArray[i], actualArray[i]);
		}
	}

}
