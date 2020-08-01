import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import org.junit.Assert;

public class AllAnagramsInStringTest {
	
	private AllAnagramsInString unit;
	
	@Before
	public void setUp() {
		unit = new AllAnagramsInString();
	}
	
	@Test
	public void shouldFetchAllAnagramsInStringCaseOne() {
		Set<Integer> expectedResult = new HashSet<Integer>();
		expectedResult.add(0);
		expectedResult.add(6);
		List<Integer> actualResult = unit.findAnagramsApproachTwo("cbaebabacd", "abc");
		verify(expectedResult, actualResult);
	}
	
	@Test
	public void shouldFetchAllAnagramsInStringCaseTwo() {
		Set<Integer> expectedResult = new HashSet<Integer>();
		expectedResult.add(0);
		expectedResult.add(1);
		expectedResult.add(2);
		List<Integer> actualResult = unit.findAnagramsApproachTwo("abab", "ab");
		verify(expectedResult, actualResult);
	}
	
	@Test
	public void shouldFetchAllAnagramsInStringCaseThree() {
		Set<Integer> expectedResult = new HashSet<Integer>();
		List<Integer> actualResult = unit.findAnagramsApproachTwo("af", "be");
		verify(expectedResult, actualResult);
	}

	private void verify(Set<Integer> expectedResult, List<Integer> actualResult) {
		Assert.assertEquals(expectedResult.size(), actualResult.size());
		for (int i = 0; i < actualResult.size(); i++) {
			Assert.assertTrue(expectedResult.contains(actualResult.get(i)));
		}
	}

}
