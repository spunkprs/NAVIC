import dp.DungeonGame;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DungeonGameTest {

    private DungeonGame unit;

    @Before
    public void setUp() {
        unit = new DungeonGame();
    }

    @Test
    public void shouldComputeMinimumHealthPackCaseOne() {
        int dungeon[][] = {{-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}};
        Assert.assertEquals(7, unit.calculateMinimumHP(dungeon));
    }

    @Test
    public void shouldComputeMinimumHealthPackCaseTwo() {
        int dungeon[][] = {{-2}};
        Assert.assertEquals(3, unit.calculateMinimumHP(dungeon));
    }

    @Test
    public void shouldComputeMinimumHealthPackCaseThree() {
        int dungeon[][] = {{100}};
        Assert.assertEquals(1, unit.calculateMinimumHP(dungeon));
    }

    @Test
    public void shouldComputeMinimumHealthPackCaseFour() {
        int dungeon[][] = {{-3, 5}};
        Assert.assertEquals(4, unit.calculateMinimumHP(dungeon));
    }

    @Test
    public void shouldComputeMinimumHealthPackCaseFive() {
        int dungeon[][] = {{0, 0, 0}, {1, 1, -1}};
        Assert.assertEquals(1, unit.calculateMinimumHP(dungeon));
    }
}
