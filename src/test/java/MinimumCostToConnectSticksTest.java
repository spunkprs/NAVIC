import org.junit.Before;
import org.junit.Test;

import greedy.MinimumCostToConnectSticks;
import org.junit.Assert;

public class MinimumCostToConnectSticksTest {
	
	private MinimumCostToConnectSticks unit;
	
	@Before
	public void setUp() {
		unit = new MinimumCostToConnectSticks();
	}
	
	@Test
	public void shouldFetchMinimumCostToConnectSticksCaseOne() {
		int input[] = {2, 4, 3};
		Assert.assertEquals(14, unit.connectSticks(input));
	}
	
	@Test
	public void shouldFetchMinimumCostToConnectSticksCaseTwo() {
		int input[] = {1, 8, 3, 5};
		Assert.assertEquals(30, unit.connectSticks(input));
	}

}
