package agoda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
Problem : 2602
Link : https://leetcode.com/problems/minimum-operations-to-make-all-array-elements-equal/description/?envType=company&envId=jpmorgan&favoriteSlug=jpmorgan-all

You are given an array nums consisting of positive integers.

You are also given an integer array queries of size m. For the ith query, you want to make all of the elements of nums equal to queries[i]. You can perform the following operation on the array any number of times:

Increase or decrease an element of the array by 1.
Return an array answer of size m where answer[i] is the minimum number of operations to make all elements of nums equal to queries[i].

Note that after each query the array is reset to its original state.

Level : Medium

 * */

public class MinimmumOperationsToMakeAllArrayElementsEqual {

    private int greaterIndex = -1;
    private int smallerIndex = -1;

    public static void main(String ar[]) {
        MinimmumOperationsToMakeAllArrayElementsEqual unit = new MinimmumOperationsToMakeAllArrayElementsEqual();
        int nums[] = {3, 1, 6, 8};
        int queries[] = {1, 5};

        List<Long> resultList = unit.minOperations(nums, queries);
        System.out.print(resultList);
    }

    public List<Long> minOperations(int[] nums, int[] queries) {
        
        return processToComputeMinOperationsOne(nums, queries);
    }

    /**
     Time Complexity of this method is O(queries.length * nums.length) that's why I am getting TLE for 7 of the test cases 24/31
     * */
    private List<Long> processToComputeMinOperations(int[] nums, int[] queries) {
        List<Long> operationsList = new ArrayList<>();
        long operationNum = 0;
        for (int i = 0; i < queries.length; i++) {
            int queryVal = queries[i];
            for (int j = 0; j < nums.length; j++) {
                if (queryVal != nums[j]) {
                    operationNum += Math.abs(nums[j] - queryVal);
                }
            }
            operationsList.add(operationNum);
            operationNum = 0;
        }
        return operationsList;
    }

    /**
     Time Complexity of this method is -->
     N = nums.length
     P = queries.length
     Sorting = O(N * log(N))
     Searching && Computing sum between indexes = O(P * log(N))
     Hence total time complexity = O(N * log(N)) + O(P * log(N))

     Space Complexity = O(N)

     But I am getting ArrayIndexOutOfBoundsException for it, which I will have to check && fix
     * */

    private List<Long> processToComputeMinOperationsOne(int[] nums, int[] queries) {
        List<Long> operationsList = new ArrayList<>();
        Arrays.sort(nums);

        long prefixSum[] = preparePrefixSum(nums);
        long operationSum = 0;
        for (int i = 0; i < queries.length; i++) {
            int queryVal = queries[i];
            indexGreaterThanQueryVal(queryVal, nums);
            indexLesserThanQueryVal(queryVal, nums);

            if (greaterIndex != -1 && smallerIndex != -1) {
                long sumOne = queryVal * (nums.length - greaterIndex);
                long sumTwo = prefixSum[nums.length] - prefixSum[greaterIndex];

                long sumThree = queryVal * (smallerIndex + 1);
                long sumFour = prefixSum[smallerIndex + 1] - prefixSum[0];
                operationSum += Math.abs(sumOne - sumTwo) + Math.abs(sumThree - sumFour);
            } else if (greaterIndex == -1 && smallerIndex != -1) {
                long sumOne = queryVal * (smallerIndex + 1);
                long sumTwo = prefixSum[smallerIndex + 1] - prefixSum[0];
                operationSum += Math.abs(sumOne - sumTwo);
            } else if (greaterIndex != -1 && smallerIndex == -1) {
                long sumOne = queryVal * (nums.length - greaterIndex);
                long sumTwo = prefixSum[nums.length] - prefixSum[greaterIndex];
                operationSum += Math.abs(sumOne - sumTwo);
            }
            operationsList.add(operationSum);
            operationSum = 0;
            smallerIndex = -1;
            greaterIndex = -1;
        }

        return operationsList;
    }

    private void indexLesserThanQueryVal(int queryVal, int[] nums) {
        fetchSmallerIndex(queryVal, nums, 0, nums.length - 1);
    }

    private void fetchSmallerIndex(int queryVal, int[] nums, int startIndex, int endIndex) {
        int midIndex = (startIndex + endIndex) / 2;
        if (nums[midIndex] < queryVal && nums[midIndex + 1] >= queryVal) {
            smallerIndex = midIndex;
            return;
        } else if (nums[midIndex - 1] < queryVal && nums[midIndex] >= queryVal && midIndex < nums.length - 1) {
            smallerIndex = midIndex;
            return;
        } else if (nums[midIndex] >= queryVal && midIndex > 0) {
            fetchSmallerIndex(queryVal, nums, startIndex, midIndex - 1);
        } else if (nums[midIndex] < queryVal && midIndex + 1 < nums.length) {
            fetchSmallerIndex(queryVal, nums, midIndex + 1, endIndex);
        }
    }

    private void indexGreaterThanQueryVal(int queryVal, int[] nums) {
        fetchGreaterIndex(queryVal, nums, 0, nums.length - 1);
    }

    private void fetchGreaterIndex(int queryVal, int[] nums, int startIndex, int endIndex) {
        int midIndex = (startIndex + endIndex) / 2;
        if (nums[midIndex] > queryVal && nums[midIndex - 1] <= queryVal) {
            greaterIndex = midIndex;
            return;
        } else if (nums[midIndex - 1] <= queryVal && nums[midIndex] > queryVal && midIndex > 0) {
            greaterIndex = midIndex;
            return;
        } else if (nums[midIndex] > queryVal && midIndex == 0) {
            greaterIndex = midIndex;
            return;
        } else if (nums[midIndex] <= queryVal && midIndex + 1 < nums.length) {
            fetchGreaterIndex(queryVal, nums, midIndex + 1, endIndex);
        } else {
            fetchGreaterIndex(queryVal, nums, startIndex, midIndex - 1);
        }
    }

    private long[] preparePrefixSum(int[] nums) {
        long prefixSum[] = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        return prefixSum;
    }
}
