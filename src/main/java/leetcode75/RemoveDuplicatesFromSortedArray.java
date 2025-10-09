package leetcode75;

/**
 Given an integer array nums sorted in non-decreasing order,
 remove some duplicates in-place such that each unique element appears
 at most twice. The relative order of the elements should be kept the same.

 Since it is impossible to change the length of the array in some languages,
 you must instead have the result be placed in the first part of the array nums.
 More formally, if there are k elements after removing the duplicates,
 then the first k elements of nums should hold the final result.
 It does not matter what you leave beyond the first k elements.

 Return k after placing the final result in the first k slots of nums.

 Do not allocate extra space for another array. You must do this by modifying
 the input array in-place with O(1) extra memory.

Source : LeetCode
Level : Medium
Status : Accepted
 * */

public class RemoveDuplicatesFromSortedArray {

    public static void main(String ar[]) {
        RemoveDuplicatesFromSortedArray unit = new RemoveDuplicatesFromSortedArray();
        int nums[] = {1, 1, 1};
        System.out.println("length post removing duplicates is " + unit.removeDuplicates(nums));
    }

    public int removeDuplicates(int[] nums) {
        int startIndex = 0;
        int index = 0;
        int counter = 0;

        while (index < nums.length - 1) {
            if (nums[index] == nums[index + 1]) {
                index++;
            } else {
                int diff = index - startIndex + 1;
                if (diff > 1) {
                    populateIndexesWithDummyValues(startIndex + 2, index, nums);
                }
                startIndex = index + 1;
                index++;
            }
        }

        if (index - startIndex + 1 > 1) {
            populateIndexesWithDummyValues(startIndex + 2, index, nums);
        }

        int backIndex = nums.length - 1;

        while (backIndex > 0) {
            if (nums[backIndex] != 10001 && nums[backIndex - 1] == 10001) {
                int temp = nums[backIndex];
                nums[backIndex] = nums[backIndex - 1];
                nums[backIndex - 1] = temp;
                continueSwappingElements(backIndex, nums);
            }
            backIndex--;
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 10001) {
                counter++;
            }
        }
        return counter;
    }

    private void continueSwappingElements(int index, int[] nums) {
        for (int i = index; i < nums.length - 1; i++) {
            if (nums[i] == 10001 && nums[i + 1] != 10001) {
                int temp = nums[i];
                nums[i] = nums[i + 1];
                nums[i + 1] = temp;
            }
        }
    }

    private void populateIndexesWithDummyValues(int startIndex, int endIndex, int[] nums) {
        for (int i = startIndex; i <= endIndex; i++) {
            nums[i] = 10001;
        }
    }
}
