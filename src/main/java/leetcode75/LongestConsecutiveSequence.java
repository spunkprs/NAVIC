package leetcode75;

import java.util.HashMap;
import java.util.Map;

/**
Given an unsorted array of integers nums, return the length of the
longest consecutive elements sequence.

You must write an algorithm that runs in O(n) time



 * */

public class LongestConsecutiveSequence {

    public static void main(String ar[]) {
        LongestConsecutiveSequence unit = new LongestConsecutiveSequence();
        int nums[] = {100,4,200,1,3,2};

        System.out.print("Length of longest consecutive sequence for the provided input is " +
                unit.longestConsecutive(nums));
    }

    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> map = populateMap(nums);
        int result = 0;

        for (int i = 0; i < nums.length; i++) {
            if (map.get(nums[i]) == 0) {
                int value = nums[i];
                int numOne = value + 1;
                int numTwo = value - 1;
                int count = 0;

                while (map.containsKey(numOne) && map.get(numOne) == 0)     {
                    count++;
                    map.put(numOne, 1);
                    numOne++;

                }

                while (map.containsKey(numTwo) && map.get(numTwo) == 0)           {
                    count++;
                    map.put(numTwo, 1);
                    numTwo--;
                }

                map.put(value, 1);
                count++;

                result = updateValue(count, result);
            }
        }
        return result;
    }

    private int updateValue(int value, int result) {
        return value >= result ? value : result;
    }

    private Map<Integer, Integer> populateMap(int nums[]) {
        Map<Integer, Integer> map = new HashMap();

        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], 0);
            }
        }
        return map;
    }

}
