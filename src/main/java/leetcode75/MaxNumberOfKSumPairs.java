package leetcode75;

import java.util.HashMap;
import java.util.Map;

/**
You are given an integer array nums and an integer k.

In one operation, you can pick two numbers from the array whose sum
equals k and remove them from the array.

Return the maximum number of operations you can perform on the array

Constraints:-

a.) 1 <= nums.length <= pow(10,5)
b.) 1 <= nums[i] <= pow(10,9)
c.) 1 <= k <= pow(10,9)

Time Complexity = O(n)
Space Complexity = O(n)

Source : LeetCode
Level : Medium

 * */

public class MaxNumberOfKSumPairs {

    public static void main(String ar[]) {
        MaxNumberOfKSumPairs unit = new MaxNumberOfKSumPairs();
        int nums[] = {1,2,3,4};
        int k = 5;
        System.out.println("Maximum number of k sum pairs for value k " + k + " is " +
                unit.maxOperations(nums, k));
    }

    public int maxOperations(int[] nums, int k) {
        int result = 0;
        Map<Integer, Integer> map = new HashMap();

        populateMap(map, nums);

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= k) {
                int elementOne = nums[i];
                int elementTwo = k - nums[i];

                if (elementOne == elementTwo) {
                    if (map.containsKey(elementOne) && map.get(elementOne) >= 2) {
                        result++;
                        updateValueInTheMap(elementOne, map.get(elementOne) - 2, map);
                    }
                } else {
                    if (map.containsKey(elementOne) && map.containsKey(elementTwo) && map.get(elementOne) >= 1 && map.get(elementTwo) >= 1)  {
                        result++;
                        updateValueInTheMap(elementOne, map.get(elementOne) - 1, map);
                        updateValueInTheMap(elementTwo, map.get(elementTwo) - 1, map);

                    }
                }
            }
        }

        return result;
    }

    private void updateValueInTheMap(int key, int updatedValue, Map<Integer, Integer> map) {
        if (updatedValue >= 1) {
            map.put(key, updatedValue);
        } else {
            map.remove(key);
        }
    }

    private void populateMap(Map<Integer, Integer> map, int nums[]) {
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], 1);
            } else {
                map.put(nums[i], map.get(nums[i]) + 1);
            }
        }
    }

}
