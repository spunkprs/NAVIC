package agoda;

import java.util.*;

/**
Problem : 2602
Link : https://leetcode.com/problems/minimum-operations-to-make-all-array-elements-equal/description/?envType=company&envId=jpmorgan&favoriteSlug=jpmorgan-all

You are given an array nums consisting of positive integers.

You are also given an integer array queries of size m. For the ith query, you want to make all of the elements of nums equal to queries[i]. You can perform the following operation on the array any number of times:

Increase or decrease an element of the array by 1.
Return an array answer of size m where answer[i] is the minimum number of operations to make all elements of nums equal to queries[i].

Note that after each query the array is reset to its original state.

Level : Medium

Constraints:

a.) n == nums.length
b.) m == queries.length
c.) 1 <= n, m <= 10^5
d.) 1 <= nums[i], queries[i] <= 10^9

Reference Link for solution : https://www.youtube.com/watch?v=5vB9ibp3K6Y

Shall go through more of questions where subarray sum is involved && for the same prefix sum is used !!
 * */

public class MinimmumOperationsToMakeAllArrayElementsEqual {

    private int greaterIndex = -1;
    private int smallerIndex = -1;

    public static void main(String ar[]) {
        MinimmumOperationsToMakeAllArrayElementsEqual unit = new MinimmumOperationsToMakeAllArrayElementsEqual();
        //int nums[] = {3, 1, 6, 8};
        //int queries[] = {1, 5};

        int nums[] = {2, 9, 6, 3};
        int queries[] = {10};

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
     Have fixed the issue now && solution is accepted
     * */

    private List<Long> processToComputeMinOperationsOne(int[] nums, int[] queries) {
        List<Long> operationsList = new ArrayList<>();
        Arrays.sort(nums);

        TreeMap<Integer, Node> treeMap = new TreeMap<>();

        prepareTreeMap(nums, treeMap);

        long prefixSum[] = preparePrefixSum(nums);
        long operationSum = 0;

        for (int i = 0; i < queries.length; i++) {
            int queryVal = queries[i];
            Map.Entry<Integer, Node> higherEntry = treeMap.higherEntry(queryVal);
            if (higherEntry != null) {
                greaterIndex = higherEntry.getValue().startIndex;
            }

            Map.Entry<Integer, Node> lowerEntry = treeMap.lowerEntry(queryVal);
            if (lowerEntry != null) {
                smallerIndex = lowerEntry.getValue().endIndex;
            }

            operationSum = prepareOperationSum(nums, prefixSum, queryVal);

            operationsList.add(operationSum);
            smallerIndex = -1;
            greaterIndex = -1;
        }
        return operationsList;
    }

    private long prepareOperationSum(int[] nums, long[] prefixSum, int queryVal) {
        long result = 0;
        if (greaterIndex != -1) {
            long sumOne = prefixSum[nums.length] - prefixSum[greaterIndex];
            long sumTwo = 1L * (nums.length - greaterIndex) * queryVal;
            result += sumOne - sumTwo;
        }

        if (smallerIndex != -1) {
            long sumTwo = 1L * (smallerIndex + 1) * queryVal;
            long sumOne = prefixSum[smallerIndex + 1];
            result += sumTwo - sumOne;
        }

        return result;
    }

    private void prepareTreeMap(int[] nums, TreeMap<Integer, Node> treeMap) {
        for (int i = 0; i < nums.length; i++) {
            if (treeMap.containsKey(nums[i])) {
                Node node = treeMap.get(nums[i]);
                node.endIndex = i;
            } else {
                treeMap.put(nums[i], new Node(i, i));
            }
        }
    }

    private long[] preparePrefixSum(int[] nums) {
        long prefixSum[] = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        return prefixSum;
    }

    static class Node {
        private int startIndex;
        private int endIndex;

        public Node(int startIndex, int endIndex) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }
    }
}
