package microsoft.dp;

import java.util.HashMap;
import java.util.Map;

public class CombinationSum4 {

    public static void main(String ar[]) {
        CombinationSum4 unit = new CombinationSum4();
        int nums[] = {9};
        int target = 3;

        System.out.print("Number of ways to reach target " + target + " is " + unit.combinationSum4(nums, target));
    }

    public int combinationSum4(int[] nums, int target) {
        return processToFindWaysToReachTargetSum(nums, target);
    }

    private int processToFindWaysToReachTargetSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= target) {
                result += process(nums[i], target, nums, map);
            }
        }
        return result;
    }

    private int process(int parent, int target, int[] nums, Map<Integer, Integer> map) {
        int resultCount = 0;
        if (parent == target) {
            map.put(parent, 1);
            resultCount = 1;
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (parent + nums[i] <= target) {
                    if (!map.containsKey(parent + nums[i])) {
                        int intermittentCount = process(parent + nums[i], target, nums, map);
                        resultCount += intermittentCount;
                    } else {
                        resultCount += map.get(parent + nums[i]);
                    }
                }
            }
            map.put(parent, resultCount);
        }
        return resultCount;
    }
}
