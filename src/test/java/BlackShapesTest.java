import graph.BlackShapes;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class BlackShapesTest {

    private BlackShapes unit;

    @Before
    public void setUp() {
        unit = new BlackShapes();
    }

    @Test
    public void shouldFetchNumberOfBlackShapesCaseOne() {
        ArrayList<String> stringList = new ArrayList<String>();
        stringList.add("OOOXOOO");
        stringList.add("OOXXOXO");
        stringList.add("OXOOOXO");
        Assert.assertEquals(3, unit.black(stringList));
    }

    @Test
    public void shouldFetchNumberOfBlackShapesCaseTwo() {

        ArrayList<String> stringList = new ArrayList<String>();
        stringList.add("XXX");
        stringList.add("XXX");
        stringList.add("XXX");

        Assert.assertEquals(1, unit.black(stringList));
    }
}
