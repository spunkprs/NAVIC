package leetcode75;

/**
 Given an integer array nums and an integer val, remove all occurrences of val in nums in-place.
 The order of the elements may be changed. Then return the number of elements in nums which are
 not equal to val.

 Consider the number of elements in nums which are not equal to val be k, to get accepted,
 you need to do the following things:

 Change the array nums such that the first k elements of nums contain the elements which
 are not equal to val. The remaining elements of nums are not important as well as the size of nums.

 Return k.
 * */

public class RemoveElement {

    public static void main(String ar[]) {
        RemoveElement unit = new RemoveElement();

        int nums[] = {4, 3, 2, 3, 2, 2, 3, 5};
        int val = 3;

        System.out.println("Length with values removed is " + unit.removeElement(nums, val));
    }

    public int removeElement(int[] nums, int val) {
        int index = nums.length - 1;
        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val) {
                nums[i] = 101;
            }
        }

        while (index > 0) {
            if (nums[index] != 101 && nums[index - 1] == 101) {
                int temp = nums[index - 1];
                nums[index - 1] = nums[index];
                nums[index] = temp;

                shiftElementsRecursively(index, nums);
            }
            index--;
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 101) {
                count++;
            }
        }

        return count;

    }

    private void shiftElementsRecursively(int index, int nums[]) {
        if (index + 1 < nums.length && nums[index + 1] != 101) {
            int temp = nums[index + 1];
            nums[index + 1] = nums[index];
            nums[index] = temp;
            shiftElementsRecursively(index + 1, nums);
        }
    }


}
