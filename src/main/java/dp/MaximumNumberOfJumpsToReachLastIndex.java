package dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        //int nums[] = {1,3,6,4,1,1};
        //int target = 0;

        int nums[] = {0, 1};
        int target = 1;

        System.out.println("Maximum number of steps to reach destination from start index 0 with constraints is " + unit.maximumJumps(nums, target));
    }

    public int maximumJumps(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(nums.length - 1, 0);

        for (int startIndex = nums.length - 1; startIndex >= 1; startIndex--) {
            processToBuildMaxPathMap(startIndex, nums, map, target);
        }

        int maxDistanceFromParent = -1;

        for (int startIndex = 1; startIndex < nums.length - 1; startIndex++) {
            if (Math.abs(nums[startIndex] - nums[0]) <= target && map.containsKey(startIndex)) {
                int maxDistanceFromChild = map.get(startIndex);
                if (maxDistanceFromChild != -1 && startIndex != nums.length - 1) {
                    maxDistanceFromParent = maxDistanceFromChild + 1 > maxDistanceFromParent ? maxDistanceFromChild + 1 : maxDistanceFromParent;
                } else if (maxDistanceFromChild == 0 && startIndex == nums.length - 1) {
                    maxDistanceFromParent = maxDistanceFromChild + 1 > maxDistanceFromParent ? maxDistanceFromChild + 1 : maxDistanceFromParent;
                }
            }
        }
        return maxDistanceFromParent;
    }

    private void processToBuildMaxPathMap(int parentIndex, int nums[], Map<Integer, Integer> map, int target) {
        int maxDistanceFromParent = -1;
        if (!map.containsKey(parentIndex)) {
            for (Integer childIndex : findChildren(parentIndex, nums)) {
                if (Math.abs(nums[childIndex] - nums[parentIndex]) <= target && map.containsKey(childIndex) && map.get(childIndex) != -1) {
                    int maxDistanceFromChild = map.get(childIndex);
                    maxDistanceFromParent = maxDistanceFromChild + 1 > maxDistanceFromParent ? maxDistanceFromChild + 1 : maxDistanceFromParent;
                }
            }
            map.put(parentIndex, maxDistanceFromParent);
        }
    }

    private List<Integer> findChildren(int parentIndex, int[] nums) {
        List<Integer> childIndexes = new ArrayList<>();
        for (int i = parentIndex + 1; i < nums.length; i++) {
            childIndexes.add(i);
        }
        return childIndexes;
    }
}
