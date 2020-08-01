import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class WordLadderTest {

    private WordLadder unit;

    @Before
    public void setUp() {
        unit = new WordLadder();
    }

    @Test
    public void shouldComputeLengthOfWordLadderCaseOne() {
        List<String> wordList = new ArrayList<String>();
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("log");
        wordList.add("cog");

        Assert.assertEquals(5, unit.ladderLength("hit", "cog", wordList));
    }
    
    @Test
    public void shouldComputeLengthOfWordLadderCaseTwo() {
        List<String> wordList = new ArrayList<String>();
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("log");
       
        Assert.assertEquals(0, unit.ladderLength("hit", "cog", wordList));
    }
}
