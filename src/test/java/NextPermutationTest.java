import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NextPermutationTest {

    private NextPermutation unit;

    @Before
    public void setUp() {
        unit = new NextPermutation();
    }

    @Test
    public void shouldFetchNextPermutationCaseOne() {
        int arr[] = {1, 2, 3};
        int expectedArr[] = {1, 3, 2};
        unit.nextPermutation(arr);
        verify(expectedArr, arr);
    }

    @Test
    public void shouldFetchNextPermutationCaseTwo() {
        int arr[] = {1, 1, 5};
        int expectedArr[] = {1, 5, 1};
        unit.nextPermutation(arr);
        verify(expectedArr, arr);
    }

    @Test
    public void shouldFetchNextPermutationCaseThree() {
        int arr[] = {3, 2, 1};
        int expectedArr[] = {1, 2, 3};
        unit.nextPermutation(arr);
        verify(expectedArr, arr);
    }

    @Test
    public void shouldFetchNextPermutationCaseFour() {
        int arr[] = {4, 5, 8, 7, 6, 4, 3, 2};
        int expectedArr[] = {4, 6, 2, 3, 4, 5, 7, 8};
        unit.nextPermutation(arr);
        verify(expectedArr, arr);
    }

    @Test
    public void shouldFetchNextPermutationCaseFive() {
        int arr[] = {4, 5, 8, 7, 6, 4, 3};
        int expectedArr[] = {4, 6, 3, 4, 5, 7, 8};
        unit.nextPermutation(arr);
        verify(expectedArr, arr);
    }

    @Test
    public void shouldFetchNextPermutationCaseSix() {
        int arr[] = {1, 3, 2};
        int expectedArr[] = {2, 1, 3};
        unit.nextPermutation(arr);
        verify(expectedArr, arr);
    }

    @Test
    public void shouldFetchNextPermutationCaseSeven() {
        int arr[] = {2, 3, 1};
        int expectedArr[] = {3, 1, 2};
        unit.nextPermutation(arr);
        verify(expectedArr, arr);
    }

    @Test
    public void shouldFetchNextPermutationCaseEight() {
        int arr[] = {5, 4, 7, 5, 3, 2};
        int expectedArr[] = {5, 5, 2, 3, 4, 7};
        unit.nextPermutation(arr);
        verify(expectedArr, arr);
    }

    private void verify(int[] expectedArr, int[] arr) {
        for (int i = 0; i < expectedArr.length; i++) {
            Assert.assertEquals(expectedArr[i], arr[i]);
        }
    }
}
