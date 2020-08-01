import dp.CherryPickUp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CherryPickUpTest {

    private CherryPickUp unit;

    @Before
    public void setUp() {
        unit = new CherryPickUp();
    }

    @Test
    public void shouldFetchMaximumCherriesCaseOne() {
        int grid[][] = {{0, 1, -1}, {1, 0, -1}, {1, 1, 1}};
        Assert.assertEquals(5, unit.cherryPickup(grid));
    }

    @Test
    public void shouldFetchMaximumCherriesCaseTwo() {
        int grid[][] = {{1, 1, -1}, {1, -1, 1}, {-1, 1, 1}};
        Assert.assertEquals(0, unit.cherryPickup(grid));
    }

    @Test
    public void shouldFetchMaximumCherriesCaseThree() {
        int grid[][] = {{1, 0, 0}, {1, -1, -1}, {0, 0, 1}};
        Assert.assertEquals(3, unit.cherryPickup(grid));
    }
}
