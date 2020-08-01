import dp.DecodeWays;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DecodeWaysTest {

    private DecodeWays unit;

    @Before
    public void setUp() {
        unit = new DecodeWays();
    }

    @Test
    public void shouldReturnNumberOfWaysToDecodeCaseTwo() {
        Assert.assertEquals(2, unit.numDecodings("12"));
    }

    @Test
    public void shouldReturnNumberOfWaysToDecodeCaseOne() {
        Assert.assertEquals(3, unit.numDecodings("226"));
    }

    @Test
    public void shouldReturnNumberOfWaysToDecodeCaseThree() {
        Assert.assertEquals(5, unit.numDecodings("1212"));
    }

    @Test
    public void shouldReturnNumberOfWaysToDecodeCaseFour() {
        Assert.assertEquals(3, unit.numDecodings("2266"));
    }

    @Test
    public void shouldReturnNumberOfWaysToDecodeCaseFive() {
        Assert.assertEquals(0, unit.numDecodings("0"));
    }

    @Test
    public void shouldReturnNumberOfWaysToDecodeCaseSix() {
        Assert.assertEquals(1, unit.numDecodings("10"));
    }

    @Test
    public void shouldReturnNumberOfWaysToDecodeCaseSeven() {
        Assert.assertEquals(0, unit.numDecodings("00"));
    }

    @Test
    public void shouldReturnNumberOfWaysToDecodeCaseEight() {
        Assert.assertEquals(589824, unit.numDecodings("4757562545844617494555774581341211511296816786586787755257741178599337186486723247528324612117156948"));
    }

    @Test
    public void shouldReturnNumberOfWaysToDecodeCaseNine() {
        Assert.assertEquals(1, unit.numDecodings("101"));
    }


}
