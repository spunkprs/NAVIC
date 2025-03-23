package arrays;

/*
* Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
* Processing needs to be done in O(n) time && O(1) space
* */

public class RotateArray {

    public static void main(String ar[]) {

        //Input
        //int inputArray[] = {-1,-100,3,99};
        int inputArray [] = {1,2,3,4,5,6,7};

        RotateArray unit = new RotateArray();
        unit.rotate(inputArray, 10);
    }

    public void rotate(int[] nums, int k) {
        if (k > 0 && nums.length > 1) {
            int numberOfSteps = k % nums.length;
            if (numberOfSteps != 0) {
                processToRotateArrayBySteps(nums, numberOfSteps);
            }
        }
        System.out.println("Array post right rotation ");
        printElements(nums);
    }

    private void printElements(int[] nums) {
        for (Integer num : nums) {
            System.out.println(num);
        }
    }

    /*
    *
    * Process for doing the rotation in O(n) time && O(1) space can be broken down in to following parts :-
    * Step 1: Reverse elements from n - k - 1 index to n - 1 index
    * Step 2: Reverse elements from 0 index to n - k - 2 index
    * Step 3: Reverse elements from 0 index to n - 1 index
    * */

    private void processToRotateArrayBySteps(int nums[], int numberOfSteps) {
        reverse(nums, nums.length - numberOfSteps, nums.length - 1);
        reverse(nums, 0, nums.length - numberOfSteps - 1);
        reverse(nums, 0, nums.length - 1);
    }

    private void reverse(int nums[], int startIndex, int endIndex) {
        int i = startIndex;
        int j = endIndex;

        while(i < j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }
    }
}
