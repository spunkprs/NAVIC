import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import binarytree.TopKFrequentWords;
import org.junit.Assert;

public class TopKFrequentWordsTest {
	
	private TopKFrequentWords unit;
	
	@Before
	public void setUp() {
		unit = new TopKFrequentWords();
	}
	
	@Test
	public void shouldFetchTopKFrequentWordsCaseOne() {
		String words[] = {"i", "love", "leetcode", "i", "love", "coding"};
		
		List<String> expectedResult = new ArrayList<String>();
		
		expectedResult.add("i");
		expectedResult.add("love");
		
		List<String> result = unit.topKFrequent(words, 2);
		verifyResult(expectedResult, result);
	}
	
	@Test
	public void shouldFetchTopKFrequentWordsApproachTwoCaseOne() {
		String words[] = {"i", "love", "leetcode", "i", "love", "coding"};
		
		List<String> expectedResult = new ArrayList<String>();
		
		expectedResult.add("i");
		expectedResult.add("love");
		
		List<String> result = unit.topKFrequentApproachTwo(words, 2);
		verifyResult(expectedResult, result);
	}
	
	@Test
	public void shouldFetchTopKFrequentWordsApproachTwoCaseTwo() {
		String words[] = {"i", "love", "leetcode", "i", "love", "coding"};
		
		List<String> expectedResult = new ArrayList<String>();
		
		expectedResult.add("i");
		
		List<String> result = unit.topKFrequentApproachTwo(words, 1);
		verifyResult(expectedResult, result);
	}
	
	@Test
	public void shouldFetchTopKFrequentWordsCaseTwo() {
		String words[] = {"i", "love", "leetcode", "i", "love", "coding"};
		
		List<String> expectedResult = new ArrayList<String>();
		
		expectedResult.add("i");
		
		List<String> result = unit.topKFrequent(words, 1);
		verifyResult(expectedResult, result);
	}

	private void verifyResult(List<String> expectedResult, List<String> result) {
		Assert.assertEquals(expectedResult.size(), result.size());
		for (int i = 0; i < expectedResult.size(); i++) {
			Assert.assertEquals(expectedResult.get(i), result.get(i));
		}
		
	}

}
