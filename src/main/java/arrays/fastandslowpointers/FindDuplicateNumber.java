package arrays.fastandslowpointers;

/*
*
* Given an array of positive numbers, nums, such that the values lie in the range
[1,n] where both 1 && n are inclusive, and that there are n+1 numbers in the array,
* find and return the duplicate number present in nums. There is only one repeated number in nums,
* but it may appear more than once in the array
*
*
* NOTE : Needs to be solved in constant space
* */

public class FindDuplicateNumber {

    public static void main(String ar[]) {
        FindDuplicateNumber unit = new FindDuplicateNumber();
        int nums[] = {1, 4, 5, 2, 3, 2};
        System.out.print("Only repeating number in the array nums :: " + unit.findDuplicate(nums));
    }

    public int findDuplicate(int[] nums) {
        if (nums.length > 2) {
            return processToFindDuplicates(nums);
        } else {
            return nums[0];
        }
    }

    /*
    * This method effectively makes use of Floyd's Cycle Detection Algorithm for following parts :-
    * a.) To detect loop in the array
    * b.) To check from where the loop/cycle starts
    *
    * In the context of this problem repeating number is the node from where loop starts
    * */

    private int processToFindDuplicates(int[] nums) {
        int slowPointerNext = nums[nums[0]];
        int fastPointerNext = nums[slowPointerNext];

        while (slowPointerNext != fastPointerNext) {
            slowPointerNext = nums[slowPointerNext];
            fastPointerNext = nums[nums[fastPointerNext]];
        }

        int slowPointerUpdated = nums[0];

        while(slowPointerUpdated != slowPointerNext) {
            slowPointerNext = nums[slowPointerNext];
            slowPointerUpdated = nums[slowPointerUpdated];
        }
        return slowPointerUpdated;
    }
}
