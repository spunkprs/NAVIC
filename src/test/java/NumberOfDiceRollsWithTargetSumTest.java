import org.junit.Before;
import org.junit.Test;

import dp.NumberOfDiceRollsWithTargetSum;
import org.junit.Assert;

public class NumberOfDiceRollsWithTargetSumTest {
	
	private NumberOfDiceRollsWithTargetSum unit;
	
	@Before
	public void setUp() {
		unit = new NumberOfDiceRollsWithTargetSum();
	}
	
	@Test
	public void shouldReturnNumberOfDiceRollsWithTargetSumCaseOne() {
		Assert.assertEquals(6, unit.numRollsToTarget(2, 6, 7));
	}
	
	@Test
	public void shouldReturnNumberOfDiceRollsWithTargetSumCaseTwo() {
		Assert.assertEquals(1, unit.numRollsToTarget(2, 5, 10));
	}
	
	@Test
	public void shouldReturnNumberOfDiceRollsWithTargetSumCaseThree() {
		Assert.assertEquals(222616187, unit.numRollsToTarget(30, 30, 500));
	}
	
	@Test
	public void shouldReturnNumberOfDiceRollsWithTargetSumCaseFour() {
		Assert.assertEquals(0, unit.numRollsToTarget(1, 2, 3));
	}

}
