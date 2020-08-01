import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import binarytree.MinimumTimeToCollectAllApples;
import org.junit.Assert;

public class MinimumTimeToCollectAllApplesTest {
	
	private MinimumTimeToCollectAllApples unit;
	
	@Before
	public void setUp() {
		unit = new MinimumTimeToCollectAllApples();
	}
	
	@Test
	public void shouldFetchMinimumTimeToCollectAllApplesCaseOne() {
		int edges[][] = {{0,1},{0,2},{1,4},{1,5},{2,3},{2,6}};
		List<Boolean> hasApples = new ArrayList<Boolean>();
		hasApples.add(false);
		hasApples.add(false);
		hasApples.add(true);
		hasApples.add(false);
		hasApples.add(true);
		hasApples.add(true);
		hasApples.add(false);
		Assert.assertEquals(8, unit.minTime(7, edges, hasApples));
	}
	
	@Test
	public void shouldFetchMinimumTimeToCollectAllApplesCaseTwo() {
		int edges[][] = {{0,1},{0,2},{1,4},{1,5},{2,3},{2,6}};
		List<Boolean> hasApples = new ArrayList<Boolean>();
		hasApples.add(false);
		hasApples.add(false);
		hasApples.add(true);
		hasApples.add(false);
		hasApples.add(false);
		hasApples.add(true);
		hasApples.add(false);
		Assert.assertEquals(6, unit.minTime(7, edges, hasApples));
	}
	
	@Test
	public void shouldFetchMinimumTimeToCollectAllApplesCaseThree() {
		int edges[][] = {{0,1},{0,2},{1,4},{1,5},{2,3},{2,6}};
		List<Boolean> hasApples = new ArrayList<Boolean>();
		hasApples.add(false);
		hasApples.add(false);
		hasApples.add(false);
		hasApples.add(false);
		hasApples.add(false);
		hasApples.add(false);
		hasApples.add(false);
		Assert.assertEquals(0, unit.minTime(7, edges, hasApples));
	}
	
	@Test
	public void shouldFetchMinimumTimeToCollectAllApplesCaseFour() {
		int edges[][] = {{0,2},{0,3},{1,2}};
		List<Boolean> hasApples = new ArrayList<Boolean>();
		hasApples.add(false);
		hasApples.add(true);
		hasApples.add(false);
		hasApples.add(false);
		
		Assert.assertEquals(4, unit.minTime(4, edges, hasApples));
	}

}
