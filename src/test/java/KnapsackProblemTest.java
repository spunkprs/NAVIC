import org.junit.Before;
import org.junit.Test;

import dp.KnapsackProblem;
import org.junit.Assert;

public class KnapsackProblemTest {
	
	private KnapsackProblem unit;
	
	@Before
	public void setUp() {
		unit = new KnapsackProblem();
	}
	
	@Test
	public void shouldSolveKnapsackProblemCaseOne() {
		int weights[] = {1, 3, 4, 5};
		int values[] = {1, 4, 5, 7};
		
		Assert.assertEquals(9, unit.findMaxiumValueObtainedInKnapsack(weights, values, 7));
	}

}
