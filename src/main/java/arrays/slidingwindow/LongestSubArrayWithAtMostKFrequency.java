package arrays.slidingwindow;

/**
*
You are given an integer array nums and an integer k.
The frequency of an element x is the number of times it occurs in an array.
An array is called good if the frequency of each element in this array is less than or equal to k.
Return the length of the longest good subarray of nums.
A subarray is a contiguous non-empty sequence of elements within an array.
*
*
Constraints:
a.) 1 <= nums.length <= pow(10,5)
b.) 1 <= nums[i] <= pow(10,9)
c.) 1 <= k <= nums.length
*
*
* Time Complexity = O(n)
* Space Complexity = O(n)
*
* */

import java.util.HashMap;
import java.util.Map;

public class LongestSubArrayWithAtMostKFrequency {

    public static void main(String ar[]) {
        LongestSubArrayWithAtMostKFrequency unit = new LongestSubArrayWithAtMostKFrequency();
        int inputArr[] = {1,2,3,1,2,3,1,2};
        int k = 2;
        System.out.print("Length of longest subarray for the above i/p is :: " + unit.maxSubarrayLength(inputArr, k));
    }

    public int maxSubarrayLength(int[] nums, int k) {
        return processToFindMaxSubArrayLength(nums, k);
    }

    private int processToFindMaxSubArrayLength(int[] nums, int k) {
        int leftIndex = 0;
        int rightIndex = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int maxLength = 0;

        while (rightIndex < nums.length) {
            if (!map.containsKey(nums[rightIndex])) {
                map.put(nums[rightIndex], 1);
                maxLength = updatemaxLength(maxLength, leftIndex, rightIndex);
                rightIndex++;
            } else {
                if (map.get(nums[rightIndex]) + 1 <= k) {
                    map.put(nums[rightIndex], map.get(nums[rightIndex]) + 1);
                    maxLength = updatemaxLength(maxLength, leftIndex, rightIndex);
                    rightIndex++;
                } else {
                    while (map.get(nums[rightIndex]) + 1 > k) {
                        map.put(nums[leftIndex], map.get(nums[leftIndex]) - 1);
                        leftIndex++;
                    }
                }
            }
        }
        return maxLength;
    }

    private int updatemaxLength(int maxLength, int leftIndex, int rightIndex) {
        return rightIndex - leftIndex + 1 > maxLength ? rightIndex - leftIndex + 1 : maxLength;
    }
}
