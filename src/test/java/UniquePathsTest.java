import dp.UniquePaths;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UniquePathsTest {

    private UniquePaths unit;

    @Before
    public void setUp() {
        unit = new UniquePaths();
    }

    @Test
    public void shouldFetchUniquePathsCaseOne() {
        Assert.assertEquals(3, unit.uniquePaths(3, 2));
    }

    @Test
    public void shouldFetchUniquePathsCaseTwo() {
        Assert.assertEquals(28, unit.uniquePaths(7, 3));
    }

    @Test
    public void shouldFetchUniquePathsCaseThree() {
        Assert.assertEquals(1, unit.uniquePaths(1, 1));
    }

    @Test
    public void shouldFetchUniquePathsCaseFour() {
        Assert.assertEquals(1, unit.uniquePaths(2, 1));
    }

    @Test
    public void shouldFetchUniquePathsCaseFive() {
        Assert.assertEquals(1, unit.uniquePaths(1, 2));
    }
}
