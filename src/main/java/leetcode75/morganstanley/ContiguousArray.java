package leetcode75.morganstanley;

import java.util.HashMap;
import java.util.Map;

/**
Problem : 525
Given a binary array nums, return the maximum length of a contiguous subarray with an equal number of 0 and 1

Constraints:-

a.) 1 <= nums.length <= 10^5
b.) nums[i] is either 0 or 1

 * */

public class ContiguousArray {

    public static void main(String args[]) {
        ContiguousArray unit = new ContiguousArray();
        int nums[] = {0, 1, 1};
        System.out.print("Maximum length achieved having equal 0's && 1's is " + unit.findMaxLength(nums));
    }

    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int maxLengthOne = 0;
        int maxLengthTwo = 0;
        int maxLength = 0;

        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                sum -=1;
            } else {
                sum +=1;
            }
        }

        if (sum == 0) {
            maxLength = nums.length;
        } else {
            maxLengthOne = phaseOne(map, nums, 0);
            map = new HashMap<>();
            maxLengthTwo = phaseTwo(map, nums, 0);
            maxLength = maxLengthOne >= maxLengthTwo ? maxLengthOne : maxLengthTwo;
        }
        return maxLength;
    }

    private int phaseOne(Map<Integer, Integer> map, int[] nums, int maxLength) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                sum -= 1;
            } else {
                sum += 1;
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            } else {
                int existingIndex = map.get(sum);
                maxLength = updateMaxLength(i - existingIndex, maxLength);
            }
        }
        return maxLength;
    }

    private int phaseTwo(Map<Integer, Integer> map, int[] nums, int maxLength) {
        int sum = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] == 0) {
                sum -= 1;
            } else {
                sum += 1;
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            } else {
                int existingIndex = map.get(sum);
                int distance = existingIndex - i;
                maxLength = maxLength <= distance ? distance : maxLength;
            }
        }
        return maxLength;
    }

    private int updateMaxLength(int distance, int maxLength) {
        return maxLength <= distance ? distance : maxLength;
    }
}
