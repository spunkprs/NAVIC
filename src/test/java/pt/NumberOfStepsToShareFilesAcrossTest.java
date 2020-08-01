package pt;

import org.junit.Before;
import org.junit.Test;

import amazon.NumberOfStepsToShareFilesAcross;
import org.junit.Assert;

public class NumberOfStepsToShareFilesAcrossTest {
	
	private NumberOfStepsToShareFilesAcross unit;
	
	@Before
	public void setUp() {
		unit = new NumberOfStepsToShareFilesAcross();
	}
	
	@Test
	public void shouldFetchNumberOfStepsToShareFilesAcrossCaseOne() {
		int matrix[][] = {{0, 1, 1, 0, 1}, {0, 1, 0, 1, 0}, {0, 0, 0, 0, 1}, {0, 1, 0, 0, 0}};
		Assert.assertEquals(3, unit.numberOfStepsToShareFilesAcross(4, 5, matrix));
	}

}
