import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import arrays.CombinationSum;
import org.junit.Assert;

public class CombinationSumTest {
	
	private CombinationSum unit;
	
	@Before
	public void setUp() {
		unit = new CombinationSum();
	}
	
	
	@Test
	public void shouldFindCombinationSumCaseOne() {
		List<List<Integer>> expectedResult = new ArrayList<List<Integer>>();
		List<Integer> listOne = new ArrayList<Integer>();
		listOne.add(2);
		listOne.add(2);
		listOne.add(3);
		
		List<Integer> listTwo = new ArrayList<Integer>();
		listTwo.add(7);
		
		expectedResult.add(listOne);
		expectedResult.add(listTwo);
		
		int candidates[] = {2, 3, 6, 7};
		
		List<List<Integer>> actualResult = unit.combinationSum(candidates, 7);
		
		Assert.assertEquals(expectedResult.size(), actualResult.size());
		verifyContent(expectedResult, actualResult);
	}
	
	@Test
	public void shouldFindCombinationSumCaseTwo() {
		List<List<Integer>> expectedResult = new ArrayList<List<Integer>>();
		List<Integer> listOne = new ArrayList<Integer>();
		listOne.add(2);
		listOne.add(2);
		listOne.add(2);
		listOne.add(2);
		
		List<Integer> listTwo = new ArrayList<Integer>();
		listTwo.add(2);
		listTwo.add(3);
		listTwo.add(3);
		
		List<Integer> listThree = new ArrayList<Integer>();
		listThree.add(3);
		listThree.add(5);
		
		
		expectedResult.add(listOne);
		expectedResult.add(listTwo);
		expectedResult.add(listThree);
		
		int candidates[] = {2, 3, 5};
		
		List<List<Integer>> actualResult = unit.combinationSum(candidates, 8);
		
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
