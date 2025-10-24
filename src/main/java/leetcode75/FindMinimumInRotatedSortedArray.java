package leetcode75;

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
