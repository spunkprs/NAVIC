package leetcode75;

/**
 Given an integer array nums, return true if there exists a triple of indices (i, j, k)
 such that i < j < k and nums[i] < nums[j] < nums[k]. If no such indices exists, return false

Source : LeetCode
Level : Medium

 * */

public class IncreasingTripletSubSequence {

    public static void main(String ar[]) {
        IncreasingTripletSubSequence unit = new IncreasingTripletSubSequence();
        int nums[] = {1, 2, 3, 4, 5};

        System.out.print("Does the increasing triplet subsequence exist for the provided input " +
                unit.increasingTriplet(nums));
    }

    public boolean increasingTriplet(int[] nums) {

        if (nums.length <= 2) {
            return false;
        } else {
            return processToFindIncreasingTripletExistence(nums);
        }
    }

    private boolean processToFindIncreasingTripletExistence(int[] nums) {
        boolean interimStorage[] = phaseOne(nums);
        return phaseTwo(interimStorage, nums);
    }

    private boolean phaseTwo(boolean[] interimStorage, int[] nums) {
        boolean result = false;
            for (int j = nums.length - 3; j >= 0; j--) {
                for (int i = j + 1; i < nums.length; i++) {
                    if (interimStorage[i] && nums[j] < nums[i]) {
                        result = true;
                        break;
                    }
                }
                if (result) {
                    break;
                }
            }
        return result;
    }

    private boolean[] phaseOne(int[] nums) {
        boolean interimStorage[] = new boolean[nums.length];
        interimStorage[nums.length - 1] = false;

        for (int j = nums.length - 2; j >= 0; j--) {
            for (int i = j + 1; i < nums.length; i++) {
                if (interimStorage[i] && nums[j] < nums[i]) {
                    interimStorage[j] = true;
                    break;
                } else if (nums[j] < nums[i]) {
                    interimStorage[j] = true;
                    break;
                }
            }
        }
        return interimStorage;
    }
}
