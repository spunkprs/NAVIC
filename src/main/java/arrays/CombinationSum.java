package arrays;

import java.util.LinkedList;
import java.util.List;

public class CombinationSum {
	
	private List<List<Integer>> resultList = new LinkedList<List<Integer>>();
	
	public List<List<Integer>> combinationSum(int[] candidates, int target) { 
		List<Integer> list = new LinkedList<Integer>();
		for (int i = 0; i < candidates.length; i++) {
			list.add(candidates[i]);
			processToComputeCombinationSum(candidates, target, list, candidates[i], candidates[i], i);
			list.remove(list.size() - 1);
		}
        return resultList; 
    }

	private void processToComputeCombinationSum(int[] candidates, int target, List<Integer> list, int sumTillNow, int candidate, int parentCandidateIndex) {
		if (sumTillNow < target) {
			for (int i = parentCandidateIndex; i < candidates.length; i++) {
				list.add(candidates[i]);
				 processToComputeCombinationSum(candidates, target, list, sumTillNow + candidates[i], candidates[i], i);
				 list.remove(list.size() - 1);
			}
		} else if (sumTillNow == target) {
			updateResult(list);
		}
		
	}

	private void updateResult(List<Integer> list) {
		List<Integer> newList = new LinkedList<Integer>();
		for (Integer candidate : list) {
			newList.add(candidate);
		}
		resultList.add(newList);
	}

}
