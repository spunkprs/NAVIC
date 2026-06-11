package microsoft.arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
Problem : 3026
You are given an array nums of length n and a positive integer k.

A subarray of nums is called good if the absolute difference between its first and last element is exactly k,
in other words, the subarray nums[i..j] is good if |nums[i] - nums[j]| == k.

Return the maximum sum of a good subarray of nums. If there are no good subarrays, return 0.

Referred Link : https://www.youtube.com/watch?v=So4VkfDiF2M

Information : 754 / 783 testcases passed [One of the nicest question] --> Approach 1

Time Complexity = O(N), where N being number of elements in the array
Space Complexity = O(N)

Information : 780 / 783 testcases passed --> Approach 2

Time Complexity = O(N^2), where N being number of elements in the array[this will happen only for those cases where duplication of elements is
huge hence computations for finding max subarray sum needs to be computed for all the indexes for which Math.abs(nums[i]- nums[j]) == k is
satisfied]
Cases where no duplicates are there or little duplicates are there time complexity would be O(N) only

Space Complexity = O(N)
 * */

public class MaximumGoodSubArraySum {

    public static void main(String ar[]) {
        MaximumGoodSubArraySum unit = new MaximumGoodSubArraySum();
        int nums[] = {1,3,8,9,5};
        int k = 4;

        //int nums[] = {1, 5};
        //int k = 2;

        System.out.println("Maximum good subarray sum is " + unit.maximumSubarraySum(nums, k));
        System.out.println("Maximum good subarray sum is " + unit.maximumSubarraySumOne(nums, k));
    }

    public long maximumSubarraySum(int[] nums, int k) {
        long maximumSum = Integer.MIN_VALUE;
        long prefixSumArr[] = preparePrefixSum(nums);
        Map<Integer, Integer> map = populateMap(prefixSumArr, nums);
        for (int i = 1; i < nums.length; i++) {
            int numOne = k + nums[i];
            int numTwo = nums[i] - k;
            if (map.containsKey(numOne)) {
                int index = map.get(numOne);
                if (index < i) {
                    long fetchedSum = prefixSumArr[i + 1] - prefixSumArr[index];
                    maximumSum = Math.max(maximumSum, fetchedSum);
                }
            }

            if (map.containsKey(numTwo)) {
                int index = map.get(numTwo);
                if (index < i) {
                    long fetchedSum = prefixSumArr[i + 1] - prefixSumArr[index];
                    maximumSum = Math.max(maximumSum, fetchedSum);
                }
            }
        }
        return maximumSum == Integer.MIN_VALUE ? 0 : maximumSum;
    }

    public long maximumSubarraySumOne(int[] nums, int k) {
        long maximumSum = Long.MIN_VALUE;
        long prefixSumArr[] = preparePrefixSum(nums);
        Map<Long, List<Integer>> map = populateMapOne(nums);

        for (int i = 1; i < nums.length; i++) {
            long numOne = k + nums[i];
            long numTwo = nums[i] - k;
            if (map.containsKey(numOne)) {
                List<Integer> indexes = map.get(numOne);
                for (int index : indexes) {
                    if (index < i) {
                        long fetchedSum = prefixSumArr[i + 1] - prefixSumArr[index];
                        maximumSum = Math.max(maximumSum, fetchedSum);
                    }
                }

            }

            if (map.containsKey(numTwo)) {
                List<Integer> indexes = map.get(numTwo);

                for (int index : indexes) {
                    if (index < i) {
                        long fetchedSum = prefixSumArr[i + 1] - prefixSumArr[index];
                        maximumSum = Math.max(maximumSum, fetchedSum);
                    }
                }
            }
        }
        return maximumSum == Long.MIN_VALUE ? 0 : maximumSum;
    }

    private Map<Integer, Integer> populateMap(long[] prefixSumArr, int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], i);
            } else {
                int existingIndex = map.get(nums[i]);
                long existingPrefixSum = prefixSumArr[existingIndex + 1];
                long nextPrefixSum = prefixSumArr[i + 1];
                if (nextPrefixSum < existingPrefixSum) {
                    map.put(nums[i], i);
                }
            }
        }
        return map;
    }

    private Map<Long, List<Integer>> populateMapOne(int[] nums) {
        Map<Long, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(1L * nums[i])) {
                List<Integer> indexes = new ArrayList<>();
                indexes.add(i);
                map.put(1L * nums[i], indexes);
            } else {
                /*int existingIndex = map.get(nums[i]);
                long existingPrefixSum = prefixSumArr[existingIndex + 1];
                long nextPrefixSum = prefixSumArr[i + 1];
                if (nextPrefixSum < existingPrefixSum) {
                    map.put(nums[i], i);
                }*/
                map.get(1L * nums[i]).add(i);
            }
        }
        return map;
    }

    private long[] preparePrefixSum(int[] nums) {
        long prefixSumArr[] = new long[nums.length + 1];
        for (int i = 1; i < prefixSumArr.length; i++) {
            prefixSumArr[i] = nums[i - 1] + prefixSumArr[i - 1];
        }
        return prefixSumArr;
    }
}
