package pt;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import graph.DijkstraImplementation;
import org.junit.Assert;

public class DijkstraImplementationTest {
	
	private DijkstraImplementation unit;
	
	@Before
	public void setUp() {
		unit = new DijkstraImplementation();
	}
	
	@Test
	public void shouldPerformDijkstrasImplementationCaseOne() {
		Map<Integer, Integer> expectedMap = new HashMap<Integer, Integer>();
		expectedMap.put(0, 0);
		expectedMap.put(1, 5);
		expectedMap.put(2, 7);
		expectedMap.put(3, 7);
		expectedMap.put(4, 2);
		expectedMap.put(5, 5);
		
		int adjacencyMatrix[][] = {{0, 5, 0, 9, 2, 0}, {5, 0, 2, 0, 0, 0}, {0, 2, 0, 3, 0, 0}, {0, 0, 3, 0, 0, 2}, {2, 0, 0, 0, 0, 3}, {0, 0, 0, 2, 3, 0}};
		Map<Integer, Integer> resultMap = unit.findMinimumDistanceFromSourceNodeToAllOthers(adjacencyMatrix);
		verify(expectedMap, resultMap, adjacencyMatrix);
	}


	private void verify(Map<Integer, Integer> expectedMap, Map<Integer, Integer> resultMap, int adjacencyMatrix[][]) {
		Assert.assertEquals(expectedMap.keySet().size(), resultMap.keySet().size());
		for (int i = 0; i < adjacencyMatrix.length; i++) {
			Assert.assertEquals(expectedMap.get(i), resultMap.get(i));
		}
	}

}
