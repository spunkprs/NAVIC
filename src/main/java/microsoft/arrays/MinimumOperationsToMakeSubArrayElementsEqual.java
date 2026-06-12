package microsoft.arrays;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class MinimumOperationsToMakeSubArrayElementsEqual {

    private int smallerIndex = -1;
    private int greaterIndex = -1;

    public static void main(String ar[]) {
        MinimumOperationsToMakeSubArrayElementsEqual unit = new MinimumOperationsToMakeSubArrayElementsEqual();
        //int nums[] = {4,-3,2,1,-4,6};
        //int k = 3;

        int nums[] = {6, 3, 16, 20, 20};
        int k = 5;

        System.out.print("Minimum operations to make all subarray elements equal of length " + k + " is " + unit.minOperations(nums, k));
    }

    public long minOperations(int[] nums, int k) {
        if (k == nums.length) {
            return minMoves(nums);
        } else {
            long result = Long.MAX_VALUE;
            for (int i = 0; i <= nums.length - k; i++) {
                smallerIndex = -1;
                greaterIndex = -1;
                int tmpArr[] = new int[k];
                int index = 0;
                for (int j = i; j <= i + k - 1; j++) {
                    tmpArr[index] = nums[j];
                    index++;
                }
                long interimResult = minMoves(tmpArr);
                if (interimResult == 0) {
                    result = interimResult;
                    break;
                }
                result = interimResult < result ? interimResult : result;
            }
            return result;
        }
    }

    private long minMoves(int[] nums) {
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
                long numTwo = 1L * (nums.length - higherEntry.getValue().startIndex) * nums[midIndex];
                result += numOne - numTwo;
            }

            if (lowerEntry != null) {
                long numTwo = 1L * (lowerEntry.getValue().endIndex + 1) * nums[midIndex];
                long numOne = prefixSum[lowerEntry.getValue().endIndex + 1];
                result += numTwo - numOne;
            }
            return result;
        } else {
            int midIndex = (nums.length - 1) / 2;
            int indexOne = midIndex;
            int indexTwo = midIndex + 1;

            long resultOne = 0;
            Map.Entry<Integer, Node> higherEntryOne = treeMap.higherEntry(nums[indexOne]);
            Map.Entry<Integer, Node> lowerEntryOne = treeMap.lowerEntry(nums[indexOne]);

            if (higherEntryOne != null) {
                long numOne = prefixSum[nums.length] - prefixSum[higherEntryOne.getValue().startIndex];
                long numTwo = 1L * (nums.length - higherEntryOne.getValue().startIndex) * nums[indexOne];
                resultOne += numOne - numTwo;
            }

            if (lowerEntryOne != null) {
                long numTwo = 1L * (lowerEntryOne.getValue().endIndex + 1) * nums[indexOne];
                long numOne = prefixSum[lowerEntryOne.getValue().endIndex + 1];
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
                    long numTwo = 1L * (nums.length - higherEntryTwo.getValue().startIndex) * nums[indexTwo];
                    resultTwo += numOne - numTwo;
                }

                if (lowerEntryTwo != null) {
                    long numTwo = 1L * (lowerEntryTwo.getValue().endIndex + 1) * nums[indexTwo];
                    long numOne = prefixSum[lowerEntryTwo.getValue().endIndex + 1];
                    resultTwo += numTwo - numOne;
                }
            }

            return Math.min(resultOne, resultTwo);
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
