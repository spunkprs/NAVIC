import org.junit.Before;
import org.junit.Test;

import heap.KClosestPointsFromOrigin;
import org.junit.Assert;

public class KClosestPointsFromOriginTest {
	
	private KClosestPointsFromOrigin unit;
	
	@Before
	public void setUp() {
		unit = new KClosestPointsFromOrigin();
	}
	
	@Test
	public void shouldFindKClosestPointsFromOriginCaseOne() {
		int points[][] = {{3, 3}, {5, -1}, {-2, 4}};
		
		int expectedResult[][] = {{-2, 4}, {3, 3}};
		int result[][] = unit.kClosest(points, 2);
		
		verify(expectedResult, result);
	}
	
	@Test
	public void shouldFindKClosestPointsFromOriginCaseTwo() {
		int points[][] = {{1, 3}, {-2, 2}};
		
		int expectedResult[][] = {{-2, 2}};
		int result[][] = unit.kClosest(points, 1);
		
		verify(expectedResult, result);
	}
	
	@Test
	public void shouldFindKClosestPointsFromOriginCaseThree() {
		int points[][] = {{2, 2}, {2, 2}, {2, 2}, {2, 2}, {2, 2}, {2, 2}, {1, 1}};
		
		int expectedResult[][] = {{1, 1}};
		int result[][] = unit.kClosest(points, 1);
		
		verify(expectedResult, result);
	}

	private void verify(int[][] expectedResult, int[][] result) {
		Assert.assertEquals(expectedResult.length, result.length);
		Assert.assertEquals(expectedResult[0].length, result[0].length);
		
		for (int i = 0; i < expectedResult.length; i++) {
			for (int j = 0; j < expectedResult[0].length; j++) {
				Assert.assertEquals(expectedResult[i][j], result[i][j]);
			}
		}
	}

}
