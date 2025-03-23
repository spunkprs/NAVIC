package arrays;

/*
* Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
* Processing needs to be done in O(n) time && O(1) space
* */

public class RotateArray {

    public static void main(String ar[]) {

    }

    public void rotate(int[] nums, int k) {
        if (k > 0 && nums.length > 1) {
            int numberOfSteps = Math.abs(nums.length - k);
            processToRotateArrayBySteps(nums, numberOfSteps);
        }
    }

    private void processToRotateArrayBySteps(int nums[], int numberOfSteps) {
        int counterMaxValue = nums.length;
        int count = 1;
        int temp = 0;
        boolean tempValueSet = false;
        int startIndex = nums.length - 1;

        while (count < counterMaxValue) {
            int nextIndex = startIndex + numberOfSteps;

            if (nextIndex > nums.length - 1) {
                nextIndex -= nums.length;
            }

            if (!tempValueSet) {
                temp = nums[nextIndex];
                nums[nextIndex] = nums[startIndex];
                tempValueSet = true;
            } else {
                int tmp = nums[nextIndex];
                nums[nextIndex] = temp;
                temp = tmp;
            }
            startIndex = nextIndex;
            count++;
        }
    }
}
