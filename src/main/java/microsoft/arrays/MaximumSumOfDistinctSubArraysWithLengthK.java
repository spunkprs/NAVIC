package microsoft.arrays;

import java.util.*;

/**
Problem : 2461
You are given an integer array nums and an integer k. Find the maximum subarray sum of all the subarrays of nums that meet the following conditions:

The length of the subarray is k, and
All the elements of the subarray are distinct.
Return the maximum subarray sum of all the subarrays that meet the conditions. If no subarray meets the conditions, return 0.

A subarray is a contiguous non-empty sequence of elements within an array.


 Constraints:-
a.) 1 <= k <= nums.length <= 10^5
b.) 1 <= nums[i] <= 10^5

Time Complexity = O(N), where N being number of elements in the array
Space Complexity = O(N), where N being number of elements in the array
 * */

public class MaximumSumOfDistinctSubArraysWithLengthK {

    public static void main(String ar[]) {
        MaximumSumOfDistinctSubArraysWithLengthK unit = new MaximumSumOfDistinctSubArraysWithLengthK();
        int nums[] = {9,18,10,13,17,9,19,2,1,18};
        int k = 5;

        //int nums[] = {9,9,9,1,2,3};
        //int k = 3;

        System.out.print("Maximum sum of distinct sub arrays with length K is " + unit.maximumSubarraySum(nums, k));
    }

    public long maximumSubarraySum(int[] nums, int k) {
        if (k > 1) {
            return processToFindMaximumSum(nums, k);
        }
        return Arrays.stream(nums).max().getAsInt();
    }

    private long processToFindMaximumSum(int[] nums, int k) {
        int leftPointer = 0;
        int rightPointer = 0;
        long sumTillNow = 0;
        long maxSum = 0;
        Map<Integer, Integer> map = new HashMap<>();

        while (rightPointer < nums.length) {
            int length = rightPointer - leftPointer + 1;

            if (length == k) {
                if (!map.containsKey(nums[rightPointer])) {
                    sumTillNow += nums[rightPointer];
                    map.put(nums[rightPointer], rightPointer);
                    maxSum = Math.max(maxSum, sumTillNow);
                } else {
                    int existingIndex = map.get(nums[rightPointer]);
                    while (leftPointer <= existingIndex) {
                        sumTillNow -= nums[leftPointer];
                        map.remove(nums[leftPointer]);
                        leftPointer++;
                    }
                    sumTillNow += nums[rightPointer];
                    map.put(nums[rightPointer], rightPointer);
                }
            } else if (length < k) {
                if (!map.containsKey(nums[rightPointer])) {
                    sumTillNow += nums[rightPointer];
                    map.put(nums[rightPointer], rightPointer);
                } else {
                    int existingIndex = map.get(nums[rightPointer]);
                    while (leftPointer <= existingIndex) {
                        sumTillNow -= nums[leftPointer];
                        map.remove(nums[leftPointer]);
                        leftPointer++;
                    }
                    sumTillNow += nums[rightPointer];
                    map.put(nums[rightPointer], rightPointer);
                }
            } else {
                if (!map.containsKey(nums[rightPointer])) {
                    sumTillNow += nums[rightPointer];
                    map.put(nums[rightPointer], rightPointer);
                    sumTillNow -= nums[leftPointer];
                    map.remove(nums[leftPointer]);
                    leftPointer++;
                    maxSum = Math.max(maxSum, sumTillNow);
                } else {
                    int existingIndex = map.get(nums[rightPointer]);
                    while (leftPointer <= existingIndex) {
                        sumTillNow -= nums[leftPointer];
                        map.remove(nums[leftPointer]);
                        leftPointer++;
                    }
                    sumTillNow += nums[rightPointer];
                    map.put(nums[rightPointer], rightPointer);
                }
            }
            rightPointer++;
        }
        return maxSum;
    }
}
