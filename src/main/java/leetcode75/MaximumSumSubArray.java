package leetcode75;

/**
Problem : 53
Given an integer array nums, find the subarray with the largest sum, and return its sum

Constraints:-

a.) 1 <= nums.length <= 10^5
b.) -10^4 <= nums[i] <= 10^4

Time Complexity = O(N)
Space Complexity = O(1) --> No additional space required

 * */

public class MaximumSumSubArray {

    public int maxSubArray(int[] nums) {
        int sumTillNow = nums[0];
        int maxSum = Integer.MIN_VALUE;
        maxSum = sumTillNow > maxSum ? sumTillNow : maxSum;

        for (int i = 1; i < nums.length; i++) {
            if (sumTillNow < 0) {
                if (nums[i] >= 0) {
                    sumTillNow = nums[i];
                    maxSum = updateMaxSum(maxSum, sumTillNow);
                } else {
                    sumTillNow = nums[i];
                    maxSum = updateMaxSum(maxSum, sumTillNow);
                }
            } else {
                if (nums[i] >= 0) {
                    sumTillNow += nums[i];
                    maxSum = updateMaxSum(maxSum, sumTillNow);
                } else {
                    if (Math.abs(nums[i]) > sumTillNow) {
                        sumTillNow = nums[i];
                        maxSum = updateMaxSum(maxSum, sumTillNow);
                    } else {
                        sumTillNow += nums[i];
                        maxSum = updateMaxSum(maxSum, sumTillNow);
                    }
                }
            }
        }
        return maxSum;
    }

    private int updateMaxSum(int maxSum, int sumTillNow) {
        return sumTillNow > maxSum ? sumTillNow : maxSum;
    }
}
