package arrays;

public class SearchInRotatedSortedArray {
	
private int targetIndex = -1;
private int rotationIndex = -1;
private boolean targetFlag = false;
    
    public int search(int[] nums, int target) {
        if (nums.length > 1) {
        	String rotationAlingment = checkSideOnWHichRotationHasHappened(nums);
            processToSearchTargetOne(nums, target, 0, nums.length - 1, rotationAlingment);
        } else if (nums.length == 1) {
            return nums[0] == target ? 0 : targetIndex;
        }
        return targetIndex;
    }
    
    public int searchApproachTwo(int[] nums, int target) {
        if (nums.length > 1) {
        	checkIndexAtWhichIndexRotationHasHappened(nums, 0, nums.length - 1);
        	if (rotationIndex == -1) {
        		findElementInSortedArray(nums, 0, nums.length - 1, target);
        	} else {
        		if (target >= nums[rotationIndex] && target <= nums[nums.length - 1]) {
            		findElementInSortedArray(nums, rotationIndex, nums.length - 1, target);
            	} else if (target >= nums[0] && target > nums[rotationIndex]) {
            		findElementInSortedArray(nums, 0, rotationIndex - 1, target);
            	}
        	}
        } else if (nums.length == 1) {
            return nums[0] == target ? 0 : targetIndex;
        }
        return targetIndex;
    }
    
    public boolean searchApproachThree(int[] nums, int target) {
        if (nums.length > 1) {
        	checkIndexAtWhichIndexRotationHasHappened(nums, 0, nums.length - 1);
        	if (rotationIndex == -1) {
        		findElementInSortedArray(nums, 0, nums.length - 1, target);
        	} else {
        		if (target >= nums[rotationIndex] && target <= nums[nums.length - 1]) {
            		findElementInSortedArray(nums, rotationIndex, nums.length - 1, target);
            	} else if (target >= nums[0] && target > nums[rotationIndex]) {
            		findElementInSortedArray(nums, 0, rotationIndex - 1, target);
            	}
        	}
        } else if (nums.length == 1) {
            return nums[0] == target ? true : false;
        }
        return targetFlag;
    }
    
    private void findElementInSortedArray(int[] nums, int startIndex, int endIndex, int target) {
    	if (nums[startIndex] == target) {
    		targetIndex = startIndex;
    		targetFlag = true;
    	} else if (nums[endIndex] == target) {
    		targetIndex = endIndex;
    		targetFlag = true;
    	} else {
    		int midIndex = (startIndex + endIndex) / 2;
    		if (startIndex != midIndex && midIndex != endIndex) {
    			if (target <= nums[midIndex]) {
    				findElementInSortedArray(nums, startIndex, midIndex, target);
    			} else {
    				findElementInSortedArray(nums, midIndex + 1, endIndex, target);
    			}
    		}
    	}
	}

	private void checkIndexAtWhichIndexRotationHasHappened(int[] nums, int startIndex, int endIndex) {
		int midIndex = (startIndex + endIndex)/2;
		if (midIndex >= 1) {
			if (nums[midIndex] < nums[midIndex - 1] && nums[midIndex] <= nums[midIndex + 1]) {
				rotationIndex = midIndex;
			} else if (nums[midIndex] >= nums[startIndex] && nums[midIndex] >= nums[endIndex]) {
				checkIndexAtWhichIndexRotationHasHappened(nums, midIndex, endIndex);
			} else if (nums[midIndex] < nums[endIndex] && nums[midIndex] < nums[startIndex]) {
				checkIndexAtWhichIndexRotationHasHappened(nums, startIndex, midIndex);
			}
		}
	}

	private String checkSideOnWHichRotationHasHappened(int[] nums) {
		int startIndex = 0;
		int endIndex = nums.length - 1;
		int midIndex = (startIndex + endIndex) / 2;
		if (nums[startIndex] > nums[midIndex] && nums[midIndex] < nums[endIndex]) {
			return "left";
		} else if (nums[startIndex] < nums[midIndex] && nums[midIndex] > nums[endIndex]) {
			return "right";
		}
		return "neutral";
	}

	private void processToSearchTargetOne(int nums[], int target, int startIndex, int endIndex, String rotationAlingnment) {
    	if (startIndex <= endIndex) {
    		int midIndex = (startIndex + endIndex) / 2;
            if (nums[startIndex] == target) {
                targetIndex = startIndex;
            } else if (nums[midIndex] == target) {
                targetIndex = midIndex;
            } else if (nums[endIndex] == target) {
                targetIndex = endIndex;
            } else {
            	if (rotationAlingnment.equals("left")) {
            		if (target > nums[midIndex] && target < nums[endIndex]) {
            			processToSearchTargetOne(nums, target, midIndex + 1, endIndex - 1, rotationAlingnment);
            		} else if ((target < nums[startIndex] && target < nums[midIndex]) || target > nums[startIndex]) {
            			processToSearchTargetOne(nums, target, startIndex + 1, midIndex - 1, rotationAlingnment);
            		}
            	} else if (rotationAlingnment.equals("right")) {
            		if (target > nums[startIndex] && target < nums[midIndex]) {
            			processToSearchTargetOne(nums, target, startIndex + 1, midIndex - 1, rotationAlingnment);
            		} else if ((target > nums[midIndex] && target > nums[endIndex]) || target < nums[startIndex]) {
            			processToSearchTargetOne(nums, target, midIndex + 1, endIndex - 1, rotationAlingnment);
            		}
            	} else {
            		if (target > nums[midIndex]) {
            			processToSearchTargetOne(nums, target, midIndex + 1, endIndex - 1, rotationAlingnment);
            		} else {
            			processToSearchTargetOne(nums, target, startIndex + 1, midIndex - 1, rotationAlingnment);
            		}
            	}
            }
    	}
    }
    
}
