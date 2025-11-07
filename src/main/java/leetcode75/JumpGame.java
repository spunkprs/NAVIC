package leetcode75;

/**
Problem : 55
You are given an integer array nums. You are initially positioned at the array's first index,
and each element in the array represents your maximum jump length at that position.

Return true if you can reach the last index, or false otherwise

Constraints:-

a.) 1 <= nums.length <= pow(10,4)
b.) 0 <= nums[i] <= pow(10,5)

Time Complexity = O(N * N)
Space Complexity = O(N)

Note : Time complexity can be improved to O(N) by making use of greedy approach instead, have made use of similar logic in JumpGame2 problem !!

*/

public class JumpGame {

    public static void main(String ar[]) {
        int nums[] = {1,0,1,0};
        JumpGame unit = new JumpGame();

        System.out.print("Can jump from start index to end index " + unit.canJump(nums));
    }

    public boolean canJump(int[] nums) {
        int result = 0;
        if (nums.length == 1) {
            return true;
        } else if (nums.length > 1) {
            if (nums[0] == 0) {
                return false;
            }
            result =  processToComputeMinimumJumpsToReachEndOne(nums);
        }
        return result != Integer.MAX_VALUE ? true : false;
    }

    private int processToComputeMinimumJumpsToReachEndOne(int nums[]) {
        int memory[] = new int[nums.length];
        memory[0] = 0;

        for (int k = 1; k < nums.length; k++) {
            memory[k] = Integer.MAX_VALUE;
        }

        for (int i = 1; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (memory[j] != Integer.MAX_VALUE && i - j <= nums[j]) {
                    memory[i] = memory[j] + 1 < memory[i] ? memory[j] + 1 : memory[i];
                }
            }
        }
        return memory[nums.length - 1];
    }
}
