package agoda.greedy;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;


/**
Problem : 462
Link : https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii/description/
Given an integer array nums of size n, return the minimum number of moves required to make all array elements equal.

In one move, you can increment or decrement an element of the array by 1.

Test cases are designed so that the answer will fit in a 32-bit integer.

Constraints:-

a.) n == nums.length
b.) 1 <= nums.length <= 10^5
c.) -10^9 <= nums[i] <= 10^9

Level : Medium

Time Complexity : O(N*Log(N)), where N being number of elements in the array
Space Complexity : O(N)

Observation : Awesome Question !!
 * */

public class MinimumMovesToEqualArrayElements {

    public static void main(String ar[]) {
        MinimumMovesToEqualArrayElements unit = new MinimumMovesToEqualArrayElements();

        int nums[] = {1, 1, 2};
        System.out.print("Minimum moves required is " + unit.minMoves2(nums));
    }

    private int smallerIndex = -1;
    private int greaterIndex = -1;

    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int prefixSum[] = preparePrefixSum(nums);
        TreeMap<Integer, Node> treeMap = prepareTreeMap(nums);

        if (nums.length % 2 != 0) {
            int midIndex = (nums.length - 1) / 2;

            Map.Entry<Integer, Node> higherEntry = treeMap.higherEntry(nums[midIndex]);
            Map.Entry<Integer, Node> lowerEntry = treeMap.lowerEntry(nums[midIndex]);
            long result = 0;
            if (higherEntry != null) {
                long numOne = prefixSum[nums.length] - prefixSum[higherEntry.getValue().startIndex];
                long numTwo = 1L * (nums.length - higherEntry.getValue().endIndex) * nums[midIndex];
                result += numOne - numTwo;
            }

            if (lowerEntry != null) {
                long numTwo = 1L * (lowerEntry.getValue().endIndex + 1) * nums[midIndex];
                long numOne = prefixSum[lowerEntry.getValue().startIndex + 1];
                result += numTwo - numOne;
            }
            return (int) result;
        } else {
            int midIndex = (nums.length - 1) / 2;
            int indexOne = midIndex;
            int indexTwo = midIndex + 1;

            long resultOne = 0;
            Map.Entry<Integer, Node> higherEntryOne = treeMap.higherEntry(nums[indexOne]);
            Map.Entry<Integer, Node> lowerEntryOne = treeMap.lowerEntry(nums[indexOne]);

            if (higherEntryOne != null) {
                long numOne = prefixSum[nums.length] - prefixSum[higherEntryOne.getValue().startIndex];
                long numTwo = 1L * (nums.length - higherEntryOne.getValue().endIndex) * nums[indexOne];
                resultOne += numOne - numTwo;
            }

            if (lowerEntryOne != null) {
                long numTwo = 1L * (lowerEntryOne.getValue().endIndex + 1) * nums[indexOne];
                long numOne = prefixSum[lowerEntryOne.getValue().startIndex + 1];
                resultOne += numTwo - numOne;
            }

            smallerIndex = -1;
            greaterIndex = -1;

            long resultTwo = 0;
            if (indexTwo <= nums.length - 1) {
                Map.Entry<Integer, Node> higherEntryTwo = treeMap.higherEntry(nums[indexTwo]);
                Map.Entry<Integer, Node> lowerEntryTwo = treeMap.lowerEntry(nums[indexTwo]);

                if (higherEntryTwo != null) {
                    long numOne = prefixSum[nums.length] - prefixSum[higherEntryTwo.getValue().startIndex];
                    long numTwo = 1L * (nums.length - higherEntryTwo.getValue().endIndex) * nums[indexTwo];
                    resultTwo += numOne - numTwo;
                }

                if (lowerEntryTwo != null) {
                    long numTwo = 1L * (lowerEntryTwo.getValue().endIndex + 1) * nums[indexTwo];
                    long numOne = prefixSum[lowerEntryTwo.getValue().startIndex + 1];
                    resultTwo += numTwo - numOne;
                }
            }

            return (int)Math.min(resultOne, resultTwo);
        }
    }

    private int[] preparePrefixSum(int[] nums) {
        int prefixSum[] = new int[nums.length + 1];

        for (int i = 0; i < nums.length; i++) {
            prefixSum[i + 1] = nums[i] + prefixSum[i];
        }
        return prefixSum;
    }

    private TreeMap<Integer, Node> prepareTreeMap(int[] nums) {
        TreeMap<Integer, Node> treeMap = new TreeMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (treeMap.containsKey(nums[i])) {
                Node node = treeMap.get(nums[i]);
                node.endIndex = i;
            } else {
                treeMap.put(nums[i], new Node(i, i));
            }
        }
        return treeMap;
    }

    static class Node {
        int startIndex;
        int endIndex;

        public Node(int startIndex, int endIndex) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }
    }
}
