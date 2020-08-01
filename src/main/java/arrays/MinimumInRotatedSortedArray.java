package arrays;

public class MinimumInRotatedSortedArray {
	
	private int rotationIndex = -1;
    
    public int findMin(int[] nums) {
    	
    	if (nums.length == 1) {
    		return nums[0];
    	} else if (nums.length == 2) {
    		if (nums[0] < nums[1]) {
    			return nums[0];
    		} else {
    			return nums[1];
    		}
    	}
    	
    	checkIndexAtWhichIndexRotationHasHappenedApproachTwo(nums, 0, nums.length - 1);
        
        if (rotationIndex == -1) {
            return nums[0];
        } else {
            return nums[rotationIndex];
        }
        
    }
	
	private void checkIndexAtWhichIndexRotationHasHappenedApproachTwo(int[] nums, int startIndex, int endIndex) {
		int midIndex = (startIndex + endIndex)/2;
		if (midIndex >= 1) {
			if (nums[midIndex] > nums[midIndex - 1] && nums[midIndex] > nums[midIndex + 1]) {
				rotationIndex = midIndex + 1;
			} else if (nums[midIndex] > nums[startIndex] && nums[midIndex] > nums[endIndex]) {
				checkIndexAtWhichIndexRotationHasHappenedApproachTwo(nums, midIndex, endIndex);
			} else if (nums[midIndex] < nums[endIndex] && nums[midIndex] < nums[startIndex]) {
				checkIndexAtWhichIndexRotationHasHappenedApproachTwo(nums, startIndex, midIndex);
			}
		} else if (midIndex == 0) {
			rotationIndex = midIndex + 1;
		}
	}

}
