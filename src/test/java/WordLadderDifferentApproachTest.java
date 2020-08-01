

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dp.WordLadder;
import org.junit.Assert;

public class WordLadderDifferentApproachTest {
	
	private WordLadder unit;
	
	@Before
	public void setUp() {
		unit = new WordLadder();
	}
	
	@Test
	public void shouldFetchResultCaseOne() {
		List<String> input = Arrays.asList("hot","dot","dog","lot","log","cog");
		Assert.assertEquals(5, unit.ladderLength("hit", "cog", input));
	}
	
	@Test
	public void shouldFetchResultCaseTwo() {
		List<String> input = Arrays.asList("hot","dot","dog","lot","log");
		Assert.assertEquals(0, unit.ladderLength("hit", "cog", input));
	}
	
	@Test
	public void shouldFetchResultSolutionApproachTwoCaseOne() {
		List<String> input = Arrays.asList("hot","dot","dog","lot","log","cog");
		Assert.assertEquals(5, unit.ladderLengthBFSApproach("hit", "cog", input));
	}
	
	@Test
	public void shouldFetchResultSolutionApproachTwoCaseTwo() {
		List<String> input = Arrays.asList("a", "b", "c");
		Assert.assertEquals(2, unit.ladderLengthBFSApproach("a", "c", input));
	}
	
	@Test
	public void shouldFetchResultSolutionApproachTwoCaseThree() {
		List<String> input = Arrays.asList("most","mist","miss","lost","fist","fish");
		Assert.assertEquals(4, unit.ladderLengthBFSApproach("lost", "miss", input));
	}

}
