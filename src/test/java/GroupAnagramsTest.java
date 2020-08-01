import leetcode.random.GroupAnagrams;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class GroupAnagramsTest {

    private GroupAnagrams unit;

    @Before
    public void setUp() {
        unit = new GroupAnagrams();
    }

    @Test
    public void shouldGroupAnagramsTogetherCaseOne() {
        String input[] = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> result = unit.groupAnagrams(input);
        Assert.assertNotNull(result);
    }
}
