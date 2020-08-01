package pt;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import arrays.SpiralMatrix;
import org.junit.Assert;

public class SpiralMatrixTest {
	
	private SpiralMatrix unit;
	
	@Before
	public void setUp() {
		unit = new SpiralMatrix();
	}
	
	@Test
	public void shouldPrepareElementsInSpiralOrderCaseOne() {
		int matrix[][] = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
		List<Integer> expectedResult = new ArrayList<Integer>();
		expectedResult.add(1);
		expectedResult.add(2);
		expectedResult.add(3);
		expectedResult.add(6);
		expectedResult.add(9);
		expectedResult.add(8);
		expectedResult.add(7);
		expectedResult.add(4);
		expectedResult.add(5);
		List<Integer> actualResult = unit.spiralOrder(matrix);
		verify(expectedResult, actualResult);
	}
	
	@Test
	public void shouldPrepareElementsInSpiralOrderCaseTwo() {
		int matrix[][] = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
		List<Integer> expectedResult = new ArrayList<Integer>();
		expectedResult.add(1);
		expectedResult.add(2);
		expectedResult.add(3);
		expectedResult.add(4);
		expectedResult.add(8);
		expectedResult.add(12);
		expectedResult.add(11);
		expectedResult.add(10);
		expectedResult.add(9);
		expectedResult.add(5);
		expectedResult.add(6);
		expectedResult.add(7);
		List<Integer> actualResult = unit.spiralOrder(matrix);
		verify(expectedResult, actualResult);
	}
	
	@Test
	public void shouldPrepareElementsInSpiralOrderCaseThree() {
		int matrix[][] = {{1, 2, 3, 4}, {5, 6, 7, 8}, {1, 2, 3, 4}, {5, 6, 7, 8}};
		List<Integer> expectedResult = new ArrayList<Integer>();
		expectedResult.add(1);
		expectedResult.add(2);
		expectedResult.add(3);
		expectedResult.add(4);
		expectedResult.add(8);
		expectedResult.add(4);
		expectedResult.add(8);
		expectedResult.add(7);
		expectedResult.add(6);
		expectedResult.add(5);
		expectedResult.add(1);
		expectedResult.add(5);
		expectedResult.add(6);
		expectedResult.add(7);
		expectedResult.add(3);
		expectedResult.add(2);
		List<Integer> actualResult = unit.spiralOrder(matrix);
		verify(expectedResult, actualResult);
	}

	private void verify(List<Integer> expectedResult, List<Integer> actualResult) {
		Assert.assertEquals(expectedResult.size(), actualResult.size());
		for (int i = 0; i < expectedResult.size(); i++) {
			Assert.assertEquals(expectedResult.get(i), actualResult.get(i));
		}
	}

}
