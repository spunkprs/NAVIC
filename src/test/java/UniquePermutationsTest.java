import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class UniquePermutationsTest {

    private UniquePermutations unit;

    @Before
    public void setUp() {
        unit = new UniquePermutations();
    }


    @Test
    public void shouldFetchUniquePermutationsCaseOne() {
        int nums[] = {1, 2, 3};
        List<List<Integer>> result = unit.permuteUnique(nums);
        Assert.assertEquals(6, result.size());
    }

    @Test
    public void shouldFetchUniquePermutationsCaseTwo() {
        int nums[] = {1, 2, 2};
        List<List<Integer>> result = unit.permuteUnique(nums);
        Assert.assertEquals(3, result.size());
    }

    @Test
    public void shouldFetchUniquePermutationsCaseThree() {
        int nums[] = {1, 1, 2};
        List<List<Integer>> result = unit.permuteUnique(nums);
        Assert.assertEquals(3, result.size());
    }

    @Test
    public void shouldFetchUniquePermutationsCaseFour() {
        int nums[] = {1, 1, 1};
        List<List<Integer>> result = unit.permuteUnique(nums);
        Assert.assertEquals(1, result.size());
    }


}
