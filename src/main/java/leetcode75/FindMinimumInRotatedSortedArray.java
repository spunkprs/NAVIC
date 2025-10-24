package leetcode75;

/**
 Problem : 153
 Suppose an array of length n sorted in ascending order is rotated between 1 and n times.
 For example, the array nums = [0,1,2,4,5,6,7] might become:

 [4,5,6,7,0,1,2] if it was rotated 4 times.
 [0,1,2,4,5,6,7] if it was rotated 7 times.
 Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time
 results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].

 Given the sorted rotated array nums of unique elements, return the minimum element of this array.

 You must write an algorithm that runs in O(log n) time.

Constraints:-

a.) n == nums.length
b.) 1 <= n <= 5000
c.) -5000 <= nums[i] <= 5000
d.) All the integers of nums are unique.
e.) nums is sorted and rotated between 1 and n times.

Level : Medium

Time Complexity = O(log(n))

Space Complexity = O(log(n)) --> Method stack space
 * */

public class FindMinimumInRotatedSortedArray {

    private int minIndex = 0;
    private int minElement = Integer.MAX_VALUE;
    public int findMin(int[] nums) {

        if (nums.length == 1) {
            return nums[0];
        }
        processToFindMin(nums);
        return minElement == Integer.MAX_VALUE ? nums[0] : minElement;
    }

    private void processToFindMin(int nums[]) {
        process(nums, 0, nums.length - 1);
    }

    private void process(int nums[], int startIndex, int endIndex) {
        if (nums[startIndex] > nums[endIndex]) {
            int midIndex = (startIndex + endIndex) / 2;

            if (nums[midIndex + 1] < nums[midIndex]) {
                updateIndex(nums, midIndex + 1);
            } else {
                if (nums[startIndex] > nums[midIndex]) {
                    process(nums, startIndex, midIndex);
                }

                if (nums[midIndex + 1] > nums[endIndex]) {
                    process(nums, midIndex + 1, endIndex);
                }
            }
        }
    }

    private void updateIndex(int nums[], int index) {
        if (nums[index] < minElement) {
            minElement = nums[index];
        }
    }
}
