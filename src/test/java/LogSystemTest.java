import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import org.junit.Assert;
import list.LogSystem;

public class LogSystemTest {
	
	private LogSystem unit;
	
	@Before
	public void setUp() {
		unit = new LogSystem();
	}
	
	@Test
	public void shouldTestLogSystemCaseOne() {
		unit.put(1, "2017:01:01:23:59:59");
		unit.put(2, "2017:01:01:22:59:59");
		unit.put(3, "2016:01:01:00:00:00");
		
		List<Integer> expectedResult = new ArrayList<Integer>();
		expectedResult.add(1);
		expectedResult.add(2);
		expectedResult.add(3);
		
		List<Integer> result = unit.retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Year");
		verify(expectedResult, result);
	}
	
	@Test
	public void shouldTestLogSystemCaseTwo() {
		unit.put(1, "2017:01:01:23:59:59");
		unit.put(2, "2017:01:01:22:59:59");
		unit.put(3, "2016:01:01:00:00:00");
		
		List<Integer> expectedResult = new ArrayList<Integer>();
		expectedResult.add(1);
		expectedResult.add(2);
		//expectedResult.add(3);
		
		List<Integer> result = unit.retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Hour");
		verify(expectedResult, result);
	}

	private void verify(List<Integer> expectedResult, List<Integer> result) {
		Assert.assertEquals(expectedResult.size(), result.size());
		for (int i = 0; i < expectedResult.size(); i++) {
			Assert.assertTrue(result.contains(expectedResult.get(i)));
		}
		
	}

}
