import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LongestSubstringWithoutRepeatingCharactersTest {

    private LongestSubstringWithoutRepeatingCharacters unit;

    @Before
    public void setUp() {
        unit = new LongestSubstringWithoutRepeatingCharacters();
    }

    @Test
    public void shouldFetchLongestSubstringWithoutRepeatingCharactersTestCaseOne() {
        Assert.assertEquals(3, unit.lengthOfLongestSubstring("abcabcbb"));
    }

    @Test
    public void shouldFetchLongestSubstringWithoutRepeatingCharactersTestCaseTwo() {
        Assert.assertEquals(3, unit.lengthOfLongestSubstring("pwwkew"));
    }

    @Test
    public void shouldFetchLongestSubstringWithoutRepeatingCharactersTestCaseTHree() {
        Assert.assertEquals(2, unit.lengthOfLongestSubstring("abba"));
    }

    @Test
    public void shouldFetchLongestSubstringWithoutRepeatingCharactersTestCaseFour() {
        Assert.assertEquals(4, unit.lengthOfLongestSubstring("uqinntq"));
    }

    @Test
    public void shouldFetchLongestSubstringWithoutRepeatingCharactersTestCaseFive() {
        Assert.assertEquals(3, unit.lengthOfLongestSubstring("dvdf"));
    }

    @Test
    public void shouldFetchLongestSubstringWithoutRepeatingCharactersTestCaseSix() {
        Assert.assertEquals(1, unit.lengthOfLongestSubstring("dddd"));
    }
}
