package leetcode75;

/**
Problem : 1703
You are given an integer array, nums, and an integer k. nums comprises of only 0's and 1's.
In one move, you can choose two adjacent indices and swap their values.

Return the minimum number of moves required so that nums has k consecutive 1's

Constraints:-

a.) 1 <= nums.length <= 10^5
b.) nums[i] is 0 or 1.
c.) 1 <= k <= sum(nums)

Level : Hard
Having issues with the way some of the examples have been solved !!
 * */

public class MinimumAdjacentSwapsForKAdjacentOnes {

    public static void main(String ar[]) {
        MinimumAdjacentSwapsForKAdjacentOnes unit = new MinimumAdjacentSwapsForKAdjacentOnes();
        int data[] = {1,1,0,1};
        int k = 2;
        System.out.println("Minimum adjacent swaps for " + k + " adjacent ones is " + unit.minMoves(data, k));
    }

    public int minMoves(int[] data, int k) {
        int minSwaps = Integer.MAX_VALUE;
        int swaps = 0;
        int count = fetchTotalNumberOfOnes(data);
        if (count == 0 || count == data.length) {
            return 0;
        } else {
            int leftPointer = 0;
            int rightPointer = 0;
            int numberOfOnes = 0;
            while (rightPointer < data.length) {
                if (data[rightPointer] == 1) {
                    numberOfOnes++;
                }
                if (rightPointer - leftPointer + 1 == k) {
                    if (numberOfOnes == k) {
                        minSwaps = Math.min(minSwaps, swaps);
                        if (data[leftPointer] == 1 && data[leftPointer + 1] == 1) {
                            numberOfOnes--;
                        }
                        swaps = 0;
                    } else {
                         if (data[leftPointer] == 1 && data[leftPointer + 1] == 0) {
                            swaps++;
                            data[leftPointer] = 0;
                            data[leftPointer + 1] = 1;
                        }
                    }
                    leftPointer++;
                }
                rightPointer++;
            }
            return minSwaps;
        }
    }

    private int fetchTotalNumberOfOnes(int[] data) {
        int count = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i] == 1) {
                count++;
            }
        }
        return count;
    }
}
