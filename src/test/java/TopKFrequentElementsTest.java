import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import binarytree.TopKFrequentElements;
import org.junit.Assert;

public class TopKFrequentElementsTest {
	
	private TopKFrequentElements unit;
	
	@Before
	public void setUp() {
		unit = new TopKFrequentElements();
	}
	
	@Test
	public void shouldFetchTopKFrequentElementsCaseOne() {
		int nums[] = {1,1,1,2,2,3};
		List<Integer> expectedList = new ArrayList<Integer>();
		expectedList.add(1);
		expectedList.add(2);
		
		List<Integer> result = unit.topKFrequent(nums, 2);
		verifyResult(expectedList, result);
	}
	
	@Test
	public void shouldFetchTopKFrequentElementsCaseTwo() {
		int nums[] = {1,2};
		List<Integer> expectedList = new ArrayList<Integer>();
		expectedList.add(1);
		expectedList.add(2);
		
		List<Integer> result = unit.topKFrequent(nums, 2);
		verifyResult(expectedList, result);
	}
	
	@Test
	public void shouldFetchTopKFrequentElementsCaseThree() {
		int nums[] = {4,1,-1,2,-1,2,3};
		List<Integer> expectedList = new ArrayList<Integer>();
		expectedList.add(-1);
		expectedList.add(2);
		
		List<Integer> result = unit.topKFrequent(nums, 2);
		verifyResult(expectedList, result);
	}
	
	private void verifyResult(List<Integer> expectedList, List<Integer> result) {
		Assert.assertEquals(expectedList.size(), result.size());
		for (int i = 0; i < expectedList.size(); i++) {
			Assert.assertEquals(expectedList.get(i), result.get(i));
		} 
	}

}
