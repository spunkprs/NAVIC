package pt;

import org.junit.Before;
import org.junit.Test;

import graph.PrimImplementation;
import org.junit.Assert;

public class PrimImplementationTest {
	
	private PrimImplementation unit;
	
	@Before
	public void setUp() {
		unit = new PrimImplementation();
	}
	
	@Test
	public void shouldPerformPrimImplementationCaseOne() {
		int adjacencyMatrix[][] = {{0, 2, 0, 8, 0, 0, 0}, {2, 0, 4, 0, 0, 0, 0}, {0, 4, 0, 1, 7, 0, 0}, {8, 0, 0, 0, 5, 5, 9}, {0, 0, 7, 5, 0, 0, 3}, {0, 0, 0, 5, 0, 0, 2}, {0, 0, 0, 9, 3, 2, 0}};
		Assert.assertEquals(17, unit.findMinimumSpanningTreeSum(adjacencyMatrix));
	}
	
	
	@Test
	public void shouldPerformPrimImplementationCaseTwo() {
		int adjacencyMatrix[][] = {{0, 10, 30, 15}, {10, 0, 40, 0}, {30, 40, 0, 50}, {15, 0, 50, 0}};
		Assert.assertEquals(55, unit.findMinimumSpanningTreeSum(adjacencyMatrix));
	}

}
