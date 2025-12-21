package leetcode75;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 You are given a 0-indexed integer array nums of length n. You are initially standing at index 0.
 You can jump from index i to index j where i < j if:

a.) nums[i] <= nums[j] and nums[k] < nums[i] for all indexes k in the range i < k < j, or
b.) nums[i] > nums[j] and nums[k] >= nums[i] for all indexes k in the range i < k < j.
c.) You are also given an integer array costs of length n where costs[i] denotes the cost of jumping to index i.

Return the minimum cost to jump to the index n - 1

Constraints:-

a.) n == nums.length == costs.length
b.) 1 <= n <= 10^5
c.) 0 <= nums[i], costs[i] <= 10^5

The solution makes use of top down DP approach hence making it's a recursive solution but for some of the test cases
it is getting timed out

Will try iterative DP approach OR Bottom Up approach instead

 * */

public class JumpGame8 {

    public static void main(String ar[]) {
        JumpGame8 unit = new JumpGame8();
        int nums[] = {3,2,4,4,1};
        int costs[] = {3,7,6,4,2};

        // int nums[] = {0, 1, 2};
        // int costs[] = {1, 1, 1};
        System.out.print("Minimal cost to reach destination is " + unit.minCost(nums, costs));
    }

    public long minCost(int[] nums, int[] costs) {
        Map<Integer, Long> map = new HashMap<>();
        processToFindMinimumCost(nums, costs, 0, map);
        return map.get(0) - costs[0];
    }

    private long processToFindMinimumCost(int[] nums, int[] costs, int parentIndex, Map<Integer, Long> map) {
        if (parentIndex == nums.length - 1) {
            map.put(parentIndex, (long) costs[parentIndex]);
        } else {
            List<Integer> childIndexes = fetchPossibleChildIndexes(nums, parentIndex);
            long minimalCost = Long.MAX_VALUE;
            for (int index : childIndexes) {
                if (!map.containsKey(index)) {
                    long cost = processToFindMinimumCost(nums, costs, index, map);
                    minimalCost = updateMinimalCost(minimalCost, cost);
                } else {
                    long cost = map.get(index);
                    minimalCost = updateMinimalCost(minimalCost, cost);
                }
            }
            map.put(parentIndex, minimalCost + costs[parentIndex]);
        }
        return map.get(parentIndex);
    }

    private long updateMinimalCost(long minimalCost, long cost) {
        return cost < minimalCost ? cost : minimalCost;
    }

    private List<Integer> fetchPossibleChildIndexes(int[] nums, int parentIndex) {
        List<Integer> childIndexes = new ArrayList<>();
        for (int i = parentIndex + 1; i < nums.length; i++) {
            if (nums[parentIndex] <= nums[i]) {
                if (i - parentIndex == 1) {
                    childIndexes.add(i);
                } else {
                    boolean flag = true;
                    for (int k = parentIndex + 1; k < i; k++) {
                        if (nums[k] >= nums[parentIndex]) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        childIndexes.add(i);
                    }
                }
            } else {
                if (i - parentIndex == 1) {
                    childIndexes.add(i);
                } else {
                    boolean flag = true;
                    for (int k = parentIndex + 1; k < i; k++) {
                        if (nums[k] < nums[parentIndex]) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        childIndexes.add(i);
                    }
                }
            }
        }
        return childIndexes;
    }

}
