package dp;

/**
 You are given a 0-indexed array nums of n integers and an integer target.

 You are initially positioned at index 0. In one step, you can jump from index i to any index j such that:

 0 <= i < j < n
 -target <= nums[j] - nums[i] <= target
 Return the maximum number of jumps you can make to reach index n - 1.

 If there is no way to reach index n - 1, return -1.


 Constraints:

 1.) 2 <= nums.length == n <= 1000
 2.) -pow(10,9) <= nums[i] <= pow(10,9)
 3.) 0 <= target <= 2 * pow(10,9)

 Source : Leetcode
 * */

public class MaximumNumberOfJumpsToReachLastIndex {

    public static void main(String ar[]) {
        MaximumNumberOfJumpsToReachLastIndex unit = new MaximumNumberOfJumpsToReachLastIndex();


    }

    public int maximumJumps(int[] nums, int target) {
        return -1;
    }
}
