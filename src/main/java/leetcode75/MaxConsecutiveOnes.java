package leetcode75;

/**
Problem : 1004
Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's

Constraints:-

a.) 1 <= nums.length <= pow(10,5)
b.) nums[i] is either 0 or 1.
c.) 0 <= k <= nums.length

Time Complexity = O(n)
Space Complexity = O(1)

Source : LeetCode
Level : Medium
 * */

public class MaxConsecutiveOnes {

    public static void main(String ar[]) {
        MaxConsecutiveOnes unit = new MaxConsecutiveOnes();
        int nums[] = {1,1,1,0,0,0,1,1,1,1,0};
        int k = 2;
        //int nums[] = {0,0,1,1,1,0,0};
        //int k = 0;

        System.out.println("Maximum length of consecutive ones post flip is " + unit.longestOnes(nums, k));
    }

    public int longestOnes(int[] nums, int k) {
        if (nums.length == 1) {
            if (nums[0] == 0 && k == 0) {
                return 0;
            } else {
                return 1;
            }
        } else {
            int result = processToFindLongestLengthAfterFlippingZeroes(nums, k);
            return  result == Integer.MIN_VALUE ? 0 : result;
        }
    }

    private int processToFindLongestLengthAfterFlippingZeroes(int nums[], int k) {
        int leftIndex = 0;
        int rightIndex = 0;
        int counter = k;

        int length = Integer.MIN_VALUE;

        while (rightIndex < nums.length) {
            if (nums[rightIndex] == 1) {
                length = updateLength(length, leftIndex, rightIndex);
                rightIndex++;
            } else {
                if (counter > 0) {
                    length = updateLength(length, leftIndex, rightIndex);
                    rightIndex++;
                    counter--;
                } else if (counter == 0 ) {
                    if (counter != k) {
                        while (counter == 0) {
                            if (nums[leftIndex] == 0) {
                                counter++;
                            }
                            leftIndex++;
                        }
                        if (counter > 0 && nums[rightIndex] == 0) {
                            counter--;
                            length = updateLength(length, leftIndex, rightIndex);
                        }
                    } else {
                        leftIndex = rightIndex;
                        leftIndex++;
                    }
                    rightIndex++;
                }
            }
        }
        return length;
    }

    private int updateLength(int length, int leftIndex, int rightIndex) {
        return rightIndex - leftIndex + 1 > length ? rightIndex - leftIndex + 1 : length;
    }

}
