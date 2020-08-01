package pt;

import org.junit.Before;
import org.junit.Test;

import graph.WordSearch;
import org.junit.Assert;

public class WordSearchTest {
	
	private WordSearch unit;
	
	@Before
	public void setUp() {
		unit = new WordSearch();
	}
	
	@Test
	public void shouldExploreIfWordExistsInBoardCaseOne() {
		char board[][] = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
		String word = "SEE";
		Assert.assertTrue(unit.exist(board, word));
	}
	
	@Test
	public void shouldExploreIfWordExistsInBoardCaseTwo() {
		char board[][] = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
		String word = "ABCB";
		Assert.assertFalse(unit.exist(board, word));
	}
	
	@Test
	public void shouldExploreIfWordExistsInBoardCaseThree() {
		char board[][] = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
		String word = "ABCCED";
		Assert.assertTrue(unit.exist(board, word));
	}
	
	@Test
	public void shouldExploreIfWordExistsInBoardCaseFour() {
		char board[][] = {{'a'}};
		String word = "a";
		Assert.assertTrue(unit.exist(board, word));
	}
	
	@Test
	public void shouldExploreIfWordExistsInBoardCaseFive() {
		char board[][] = {{'a', 'a'}};
		String word = "a";
		Assert.assertTrue(unit.exist(board, word));
	}

}
