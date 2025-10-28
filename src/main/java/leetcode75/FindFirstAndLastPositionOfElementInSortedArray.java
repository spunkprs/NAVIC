package leetcode75;

/**
Problem : 34

Given an array of integers nums sorted in non-decreasing order,
find the starting and ending position of a given target value.

If target is not found in the array, return [-1, -1].

You must write an algorithm with O(log n) runtime complexity

Constraints:-

a.) 0 <= nums.length <= 105
b.) -pow(10,9) <= nums[i] <= pow(10,9)
c.) nums is a non-decreasing array.
d.) -pow(10,9) <= target <= pow(10,9)

Level : Medium

Time Complexity = O(log(n))
Space Complexity = O(log(n)) --> Method stack space

 * */

public class FindFirstAndLastPositionOfElementInSortedArray {

    private int minPosition = Integer.MAX_VALUE;
    private int maxPosition = Integer.MIN_VALUE;

    public static void main(String ar[]) {
        FindFirstAndLastPositionOfElementInSortedArray unit = new FindFirstAndLastPositionOfElementInSortedArray();

        int nums[] = {1, 4};
        int target = 4;

        unit.searchRange(nums, target);
    }

    public int[] searchRange(int[] nums, int target) {
        int resultArr[] = {-1, -1};
        if (nums.length == 0) {
            return resultArr;
        } else if (nums.length > 1) {
            processToLookForSearchRange(nums, target, 0, nums.length - 1);
            if (minPosition != Integer.MAX_VALUE && maxPosition != Integer.MIN_VALUE) {
                resultArr[0] = minPosition;
                resultArr[1] = maxPosition;
            }
            return resultArr;
        } else {
            if (nums[0] == target) {
                resultArr[0] = 0;
                resultArr[1] = 0;
            }
            return resultArr;
        }
    }

    private void processToLookForSearchRange(int nums[], int target, int startIndex, int endIndex) {
        int midIndex = (startIndex + endIndex) / 2;

        int value = nums[midIndex];

        if (target > value) {
            if (endIndex >= midIndex + 1) {
                processToLookForSearchRange(nums, target, midIndex + 1, endIndex);
            }
        } else if (target < value) {
            if (startIndex <= midIndex - 1) {
                processToLookForSearchRange(nums, target, startIndex, midIndex - 1);
            }
        } else {
            updateMinAndMaxIndex(midIndex);
            if (midIndex - 1 >= 0 && nums[midIndex - 1] == target && startIndex != endIndex && midIndex - 1 < minPosition) {
                processToLookForSearchRange(nums, target, startIndex,midIndex - 1);
            }

            if (midIndex + 1 < nums.length && nums[midIndex + 1] == target && startIndex != endIndex && midIndex + 1 > maxPosition) {
                processToLookForSearchRange(nums, target, midIndex + 1, endIndex);
            }
        }
    }

    private void updateMinAndMaxIndex(int midIndex) {
        minPosition = midIndex < minPosition ? midIndex : minPosition;
        maxPosition = midIndex > maxPosition ? midIndex : maxPosition;
    }
}
