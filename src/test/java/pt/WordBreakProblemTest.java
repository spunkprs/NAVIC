package pt;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dp.WordBreakProblem;
import org.junit.Assert;

public class WordBreakProblemTest {
	
	private WordBreakProblem unit;
	
	@Before
	public void setUp() {
		unit = new WordBreakProblem();
	}
	
	@Test
	public void shouldTestWordBreakProblemCaseOne() {
		List<String> dictionary = new ArrayList<String>();
		dictionary.add("c");
		dictionary.add("od");
		dictionary.add("e");
		dictionary.add("x");
		
		Assert.assertTrue(unit.wordBreak("code", dictionary));
	}
	
	@Test
	public void shouldTestWordBreakProblemCaseTwo() {
		List<String> dictionary = new ArrayList<String>();
		dictionary.add("leet");
		dictionary.add("code");
		
		Assert.assertTrue(unit.wordBreak("leetcode", dictionary));
	}
	
	@Test
	public void shouldTestWordBreakProblemCaseThree() {
		List<String> dictionary = new ArrayList<String>();
		dictionary.add("apple");
		dictionary.add("pen");
		
		Assert.assertTrue(unit.wordBreak("applepenapple", dictionary));
	}
	
	@Test
	public void shouldTestWordBreakProblemCaseFour() {
		List<String> dictionary = new ArrayList<String>();
		dictionary.add("cats");
		dictionary.add("dog");
		dictionary.add("sand");
		dictionary.add("and");
		dictionary.add("cat");
		
		Assert.assertFalse(unit.wordBreak("catsandog", dictionary));
	}

}
