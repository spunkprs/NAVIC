package arrays;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class CombinationSumTwo {
	
	private List<List<Integer>> result = new LinkedList<List<Integer>>();
	private Set<Pair> set = new HashSet<Pair>();
    
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<Integer> listResult = new LinkedList<Integer>();
        for (int i = 0; i < candidates.length; i++) {
            int sum = candidates[i]; 
            listResult.add(candidates[i]);
            if (sum < target) {
                processToComputeCombinationSum(listResult, sum, candidates, target, i + 1, sum);
            } else if (sum == target) {
            	Pair pair = new Pair(listResult.size(), sum);
                updateResult(listResult, pair);
            }
            if (listResult.size() >= 1) {
                listResult.remove(listResult.size() - 1);
            }
        }
        return result;
    }
	
	private void processToComputeCombinationSum(List<Integer> listResult, int sumTillNow, int candidates[], int target, int index, int productTillNow) {
        
        for (int j = index; j < candidates.length; j++) {
            sumTillNow += candidates[j];
            listResult.add(candidates[j]);
            productTillNow *= candidates[j];
            if (sumTillNow < target) {
                processToComputeCombinationSum(listResult, sumTillNow, candidates, target, j + 1, productTillNow);
            } else if (sumTillNow == target) {
            	Pair pair = new Pair(listResult.size(), productTillNow);
                updateResult(listResult, pair);
            }
            if (listResult.size() >= 1) {
            listResult.remove(listResult.size() - 1);
            }
            sumTillNow -= candidates[j];
            productTillNow /= candidates[j];
        } 
}
	
	private void updateResult(List<Integer> listResult, Pair pair) {
		if (!set.contains(pair)) {
			set.add(pair);
			if (listResult.size() >= 1) {
	            List<Integer> copyList = new LinkedList<Integer>();
	        for (int i = 0; i < listResult.size(); i++) {
	            copyList.add(listResult.get(i));
	        }
	        result.add(copyList);
	        }
		}
    }
	
	class Pair {
    	
    	private int length;
    	private int product;
    	
    	public Pair(int length, int product) {
    		this.length = length;
    		this.product = product;
    	}
    	
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + length;
			result = prime * result + product;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Pair other = (Pair) obj;
			if (length != other.length)
				return false;
			if (product != other.product)
				return false;
			return true;
		}
    }

}
