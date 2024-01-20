package dp;

import java.util.HashMap;
import java.util.Map;

public class LongestIncreasingSubsequence {
	
	 private Map<Integer, Integer> map = new HashMap<Integer, Integer>();
	    private int maxLength = Integer.MIN_VALUE;
	    
	    public int lengthOfLIS(int[] nums) {
	    	
	    	if (nums.length >= 1) {
	    		maxLength = 1;
		        map.put(nums.length - 1, 1);
		        
		        for (int i = nums.length - 2; i >=0; i--) {
		            processToComputeLongestIncreasinigSequenceForEachIndex(i, nums);
		        }
		        return maxLength;
	    	} 
	        return 0;
	    }
	    
	    private void processToComputeLongestIncreasinigSequenceForEachIndex(int index, int nums[]) {
	        for (int j = index + 1; j < nums.length; j++) {
	            if (nums[index] < nums[j]) {
	                int computedLength = map.get(j) + 1;
	                if (computedLength > maxLength) {
	                    maxLength = computedLength;
	                }
	                
	                if (!map.containsKey(index)) {
	                    map.put(index, computedLength);
	                } else {
	                    int alreadyComputedLength = map.get(index);
	                    if (computedLength > alreadyComputedLength) {
	                        map.put(index, computedLength);
	                    }
	                }
	            } else {
	            	if (!map.containsKey(index)) {
	            		map.put(index, 1);
	            	}
	            }
	        }
	    }

}
