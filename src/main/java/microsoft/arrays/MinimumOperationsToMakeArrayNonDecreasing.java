package microsoft.arrays;

import java.util.LinkedList;

/**
Problem : 3914
Level : Medium
Link : https://leetcode.com/problems/minimum-operations-to-make-array-non-decreasing/description/?envType=company&envId=amazon&favoriteSlug=amazon-three-months

You are given an integer array nums of length n.

In one operation, you may choose any subarray nums[l..r] and increase each element in that subarray by x, where x is any positive integer.

Return the minimum possible sum of the values of x across all operations required to make the array non-decreasing.

An array is non-decreasing if nums[i] <= nums[i + 1] for all 0 <= i < n - 1.

Constraints:-

a.) 1 <= n == nums.length <= 10^5
b.) 1 <= nums[i] <= 10^9


Status : Partially Solved [Need to come back on it]

Time Complexity : O(N)
Space Complexity : O(N)

Comments : Time complexity is fine but the code is not that readable, hence I need to tweak the approach will come back on it
 * */

public class MinimumOperationsToMakeArrayNonDecreasing {

    public static void main(String ar[]) {
        MinimumOperationsToMakeArrayNonDecreasing unit = new MinimumOperationsToMakeArrayNonDecreasing();
        //int arr[] = {3, 3, 2, 1};

        //int arr[] = {5 ,1, 2, 3};

        //int arr[] = {10, 12, 4};

        int arr[] = {18, 12, 7, 4, 30};

        System.out.print("Minimum possible sum is " + unit.minOperations(arr));
    }


    public long minOperations(int[] nums) {
        long numsLong[] = new long[nums.length];

        for (int i = 0; i < nums.length; i++) {
            numsLong[i] = nums[i];
        }
        return processToComputeMinOperations(numsLong);
        //return 0;
    }

    private long processToComputeMinOperations(long[] nums) {
        boolean flag = true;
        int indexOne = 0;
        long minimumSum = 0;

        while (flag) {
            while (indexOne < nums.length - 1 && nums[indexOne] <= nums[indexOne + 1]) {
                indexOne++;
            }

            if (indexOne + 1 < nums.length && nums[indexOne] > nums[indexOne + 1]) {
                LinkedList<Integer> queueOne = new LinkedList<>();
                LinkedList<Integer> queueTwo = new LinkedList<>();

                int indexTwo = indexOne + 1;
                while (indexTwo + 1 < nums.length && nums[indexTwo + 1] <= nums[indexTwo]) {
                    queueOne.add(indexTwo);
                    indexTwo++;
                }
                if (indexTwo + 1 <= nums.length) {
                    queueOne.add(indexTwo);
                }

                indexTwo = indexOne + 1;
                while (indexTwo + 1 < nums.length && nums[indexTwo + 1] >= nums[indexTwo]) {
                    queueTwo.add(indexTwo);
                    indexTwo++;
                }
                if (indexTwo + 1 <= nums.length) {
                    queueTwo.add(indexTwo);
                }

                if (queueOne.size() > queueTwo.size()) {
                    long difference = Math.abs(nums[indexOne] - nums[queueOne.peekLast()]);
                    nums[queueOne.peekLast()] = nums[indexOne];
                    indexOne = queueOne.peekLast();
                    minimumSum += difference;
                } else if (queueOne.size() < queueTwo.size()) {
                    long difference = Math.abs(nums[indexOne] - nums[queueTwo.peekFirst()]);
                    nums[queueTwo.peekLast()] = nums[indexOne];
                    indexOne = queueTwo.peekLast();
                    minimumSum += difference;
                } else {
                    long difference = Math.abs(nums[indexOne] - nums[queueOne.peekLast()]);
                    nums[queueOne.peekLast()] = nums[indexOne];
                    indexOne = queueOne.peekLast();
                    minimumSum += difference;
                }
            } else {
                flag = false;
            }
        }
        return minimumSum;
    }
}
