import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import arrays.CombinationSumTwo;

public class CombinationSumTwoTest {
	
	private CombinationSumTwo unit;
	
	@Before
	public void setUp() {
		unit = new CombinationSumTwo();
	}
	
	@Test
	public void shouldPrepareResultForCombinationSumTwoCaseOne() {
		List<List<Integer>> expectedResult = new ArrayList<List<Integer>>();
		List<Integer> listOne = new ArrayList<Integer>();
		listOne.add(1);
		listOne.add(7);
		
		
		List<Integer> listTwo = new ArrayList<Integer>();
		listTwo.add(1);
		listTwo.add(2);
		listTwo.add(5);
		
		List<Integer> listThree = new ArrayList<Integer>();
		listThree.add(2);
		listThree.add(6);
		
		List<Integer> listFour = new ArrayList<Integer>();
		listFour.add(1);
		listFour.add(6);
		listFour.add(1);
		
		
		expectedResult.add(listTwo);
		expectedResult.add(listOne);
		expectedResult.add(listFour);
		expectedResult.add(listThree);
		
		int candidates[] = {10, 1, 2, 7, 6, 1, 5};
		
		List<List<Integer>> actualResult = unit.combinationSum2(candidates, 8);
		
		Assert.assertEquals(expectedResult.size(), actualResult.size());
		verifyContent(expectedResult, actualResult);
	}
	
	private void verifyContent(List<List<Integer>> expectedResult, List<List<Integer>> actualResult) {
		for (int i = 0; i < expectedResult.size(); i++) {
			for (int j = 0; j < expectedResult.get(i).size(); j++) {
				Assert.assertEquals(expectedResult.get(i).get(j), actualResult.get(i).get(j));
			}
		}
	}

}
