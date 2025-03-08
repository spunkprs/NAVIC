package queue.monotonicqueue;

/*
*
Given an integer array nums and an integer k, return the length of the shortest non-empty subarray of nums with a sum of at least k.
* If there is no such subarray,
* return -1.

Example 1:

Input: nums = [1], k = 1
Output: 1
Example 2:

Input: nums = [1,2], k = 4
Output: -1
Example 3:

Input: nums = [2,-1,2], k = 3
Output: 3
*
* */

public class ShortestSubArrayWithSumAtLeastK {

    public static void main(String ar[]) {
        ShortestSubArrayWithSumAtLeastK unit = new ShortestSubArrayWithSumAtLeastK();
        int nums[] = {2, -1, 2};
        System.out.println(unit.shortestSubarray(nums, 3));
    }

    public int shortestSubarray(int[] nums, int k) {
        int length = -1;
        if (k <= 0) {
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] >= k) {
                    length = 1;
                    break;
                }
            }
        } else {
            length = processToFindShortestSubarray(nums, k, length);
        }
        return length;
    }

    private int processToFindShortestSubarray(int nums[], int k, int length) {
        int leftIndex = 0;
        int rightIndex = 0;

        int sum = nums[leftIndex];

        while (leftIndex < nums.length || rightIndex < nums.length) {
            if (leftIndex == 0 && rightIndex == 0) {
                sum = nums[leftIndex];
                if (sum >= k) {
                    length = 1;
                    break;
                } else {
                    rightIndex++;
                    sum += nums[rightIndex];
                }
            } else {
                if (sum < k) {
                    if (rightIndex < nums.length) {
                        rightIndex++;
                        if (rightIndex < nums.length) {
                            sum += nums[rightIndex];
                        }
                    } else {
                        if (leftIndex < nums.length) {
                            sum -= nums[leftIndex];
                            leftIndex++;
                        }
                    }

                } else if (sum >= k) {
                    length = updateValueOfLength(length, leftIndex, rightIndex == nums.length ? nums.length - 1 : rightIndex);
                    sum -= nums[leftIndex];
                    leftIndex++;
                }
            }
        }
        return length;
    }

    private int updateValueOfLength(int length, int leftIndex, int rightIndex) {
        if (length == -1) {
            return rightIndex - leftIndex + 1;
        } else {
            return rightIndex - leftIndex + 1 < length ? rightIndex - leftIndex + 1 : length;
        }
    }
}
