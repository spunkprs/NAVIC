import graph.CommutableIslands;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class CommutableIslandsTest {

    private CommutableIslands unit;

    @Before
    public void setUp() {
        unit = new CommutableIslands();
    }


    @Test
    public void shouldFetchMinimumCostCaseOne() {
        ArrayList<Integer> arrayListOne = new ArrayList<>();
        arrayListOne.add(1);
        arrayListOne.add(2);
        arrayListOne.add(1);

        ArrayList<Integer> arrayListTwo = new ArrayList<>();
        arrayListTwo.add(2);
        arrayListTwo.add(3);
        arrayListTwo.add(4);

        ArrayList<Integer> arrayListThree = new ArrayList<>();
        arrayListThree.add(1);
        arrayListThree.add(4);
        arrayListThree.add(3);

        ArrayList<Integer> arrayListFour = new ArrayList<>();
        arrayListFour.add(4);
        arrayListFour.add(3);
        arrayListFour.add(2);

        ArrayList<Integer> arrayListFive = new ArrayList<>();
        arrayListFive.add(1);
        arrayListFive.add(3);
        arrayListFive.add(10);

        ArrayList<ArrayList<Integer>> inputList = new ArrayList<ArrayList<Integer>>();
        inputList.add(arrayListOne);
        inputList.add(arrayListTwo);
        inputList.add(arrayListThree);
        inputList.add(arrayListFour);
        inputList.add(arrayListFive);

        Assert.assertEquals(6, unit.solve(4, inputList));

    }

    @Test
    public void shouldFetchMinimumCostCaseTwo() {
        ArrayList<Integer> arrayListOne = new ArrayList<>();
        arrayListOne.add(1);
        arrayListOne.add(2);
        arrayListOne.add(1);

        ArrayList<Integer> arrayListTwo = new ArrayList<>();
        arrayListTwo.add(2);
        arrayListTwo.add(3);
        arrayListTwo.add(2);

        ArrayList<Integer> arrayListThree = new ArrayList<>();
        arrayListThree.add(3);
        arrayListThree.add(4);
        arrayListThree.add(4);

        ArrayList<Integer> arrayListFour = new ArrayList<>();
        arrayListFour.add(1);
        arrayListFour.add(4);
        arrayListFour.add(3);

        ArrayList<ArrayList<Integer>> inputList = new ArrayList<ArrayList<Integer>>();
        inputList.add(arrayListOne);
        inputList.add(arrayListTwo);
        inputList.add(arrayListThree);
        inputList.add(arrayListFour);

        Assert.assertEquals(6, unit.solve(4, inputList));

    }

    @Test
    public void shouldFetchMinimumCostCaseThree() {
        ArrayList<Integer> arrayListOne = new ArrayList<>();
        arrayListOne.add(1);
        arrayListOne.add(2);
        arrayListOne.add(10);

        ArrayList<Integer> arrayListTwo = new ArrayList<>();
        arrayListTwo.add(2);
        arrayListTwo.add(3);
        arrayListTwo.add(5);

        ArrayList<Integer> arrayListThree = new ArrayList<>();
        arrayListThree.add(1);
        arrayListThree.add(3);
        arrayListThree.add(9);

        ArrayList<ArrayList<Integer>> inputList = new ArrayList<ArrayList<Integer>>();
        inputList.add(arrayListOne);
        inputList.add(arrayListTwo);
        inputList.add(arrayListThree);

        Assert.assertEquals(14, unit.solve(3, inputList));

    }
}
