package leetcode75.dp;

import java.util.*;

/**
Problem : 1043
Given an integer array arr, partition the array into (contiguous) subarrays of length at most k. After partitioning,
each subarray has their values changed to become the maximum value of that subarray.

Return the largest sum of the given array after partitioning. Test cases are generated so that the answer fits in a 32-bit integer

Constraints:-

a.) 1 <= arr.length <= 500
b.) 0 <= arr[i] <= 10^9
c.) 1 <= k <= arr.length

 * */

public class PartitionArrayForMaximumSum {

    public static void main(String ar[]) {
        PartitionArrayForMaximumSum unit = new PartitionArrayForMaximumSum();
        //int arr[] = {1,15,7,9,2,5,10};
        //int k = 3;

        int arr[] = {1,4,1,5,7,3,6,1,9,9,3};
        int k = 4;
        System.out.println("Maximum Sum post partition is " + unit.maxSumAfterPartitioning(arr, k));
    }

    public int maxSumAfterPartitioning(int[] arr, int k) {
        if (k == 1) {
            return sumUpEntireArray(arr);
        } else if (k == arr.length) {
            return findMaxElementInArray(arr) * arr.length;
        } else {
            Map<Pair, Integer> mapOne = new HashMap<>();
            Map<Pair, Integer> mapTwo = new HashMap<>();
            return processToComputeMaxSumTwo(arr, k, mapOne, mapTwo, new Pair(-1, -1));
        }
    }

    private int processToComputeMaxSumTwo(int[] arr, int k, Map<Pair, Integer> mapOne, Map<Pair, Integer> mapTwo, Pair parent) {
            int startIndex = parent.rightIndex + 1;
            int endIndex = startIndex;
            int j = startIndex;
            int limit = Math.min(startIndex + k - 1, arr.length - 1);
            int result = 0;

                while (j <= limit) {
                    Pair childPair = new Pair(startIndex, j);
                    if (!mapOne.containsKey(childPair)) {
                        populateMapOne(mapOne, childPair, arr);
                    }

                    if (!mapTwo.containsKey(childPair)) {
                        int intermittentResult = processToComputeMaxSumTwo(arr, k, mapOne, mapTwo, childPair);
                        if (intermittentResult > 0) {
                            mapTwo.put(childPair, intermittentResult);
                        }
                        result = Math.max(result, intermittentResult);
                    } else {
                        result = Math.max(result, mapTwo.get(childPair));
                    }
                    j++;
                }
                if (parent.rightIndex >= 0 && parent.leftIndex >= 0) {
                    int val = mapOne.get(parent);
                    int distance = parent.rightIndex - parent.leftIndex + 1;
                    result += val * distance;
                }
                mapTwo.put(parent, result);
                return result;
    }

    private void populateMapOne(Map<Pair, Integer> mapOne, Pair p, int[] arr) {
        int startIndex = p.leftIndex;
        int endIndex = p.rightIndex;

        if (startIndex == endIndex) {
            mapOne.put(p, arr[startIndex]);
        } else {
            Pair existingPair = new Pair(startIndex, endIndex - 1);
            if (mapOne.containsKey(existingPair)) {
                int value = mapOne.get(existingPair);
                mapOne.put(p, value < arr[endIndex] ? arr[endIndex] : value);
            } else {
                int maxResult = 0;
                for (int i = startIndex; i <= endIndex; i++) {
                    maxResult = arr[i] > maxResult ? arr[i] : maxResult;
                }
                mapOne.put(p, maxResult);
            }
        }
    }

    private int sumUpEntireArray(int[] arr) {
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            result += arr[i];
        }
        return result;
    }

    private int findMaxElementInArray(int[] arr) {
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            result = arr[i] > result ? arr[i] : result;
        }
        return result;
    }

    static class Pair {
        private int leftIndex;
        private int rightIndex;

        public Pair(int leftIndex, int rightIndex) {
            this.leftIndex = leftIndex;
            this.rightIndex = rightIndex;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return leftIndex == pair.leftIndex && rightIndex == pair.rightIndex;
        }

        @Override
        public int hashCode() {
            return Objects.hash(leftIndex, rightIndex);
        }
    }
}
