package microsoft.arrays;

import java.util.HashMap;
import java.util.Map;

/**
Problem : 334
Given an integer array nums, return true if there exists a triple of indices (i, j, k)
such that i < j < k and nums[i] < nums[j] < nums[k]. If no such indices exists, return false.

Time Complexity : O(N^2), where N being number of elements in the array
Space Complexity : O(N), where N being number of elements in the array
 * */

public class IncreasingTripletSubsequence {

    public static void main(String ar[]) {
        IncreasingTripletSubsequence unit = new IncreasingTripletSubsequence();
        int nums[] = {4,5,2147483647,1,2};
        System.out.print("Does valid increasing triplet subsequence exists " + unit.increasingTriplet(nums));
    }

    public boolean increasingTriplet(int[] nums) {
        if (nums.length <= 2) {
            return false;
        } else {
            return processToFindIncreasingTripletExistence(nums);
        }
    }

    private boolean processToFindIncreasingTripletExistence(int[] nums) {
        boolean result = false;
        Map<Integer, Boolean> map = prepareInternalMapFirst(nums);
        for (int i = 0; i <= nums.length - 3; i++) {
            for (int j = i + 1; j <= nums.length - 2; j++) {
                if (nums[i] < nums[j] && map.containsKey(j)) {
                    result = true;
                    break;
                }
            }
            if (result) {
                break;
            }
        }
        return result;
    }

    private Map<Integer, Boolean> prepareInternalMapFirst(int[] nums) {
        Map<Integer, Boolean> map = new HashMap<>();
        map.put(nums.length - 1, true);
        for (int i = nums.length - 2; i >= 0; i--) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] < nums[j]) {
                    map.put(i, true);
                    break;
                }
            }
        }
        return map;
    }

}
