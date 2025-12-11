package leetcode75;

import java.util.TreeMap;

/**
 Problem : 1696
 You are given a 0-indexed integer array nums and an integer k.

 You are initially standing at index 0. In one move, you can jump at most k steps forward
 without going outside the boundaries of the array. That is, you can jump from index i to
 any index in the range [i + 1, min(n - 1, i + k)] inclusive.

 You want to reach the last index of the array (index n - 1). Your score is the sum of all
 nums[j] for each index j you visited in the array.

 Return the maximum score you can get.

Constraints:-

a.) 1 <= nums.length, k <= 10^5
b.) -10^4 <= nums[i] <= 10^4


Time Complexity : O(Nlog(K)), where N being size of array provided and K is the window size
Space Complexity : O(N)
 * */

public class JumpGame6 {

    public static void main(String ar[]) {
        JumpGame6 unit = new JumpGame6();
        int nums[] = {1,-1,-2,4,-7,3};
        int k = 2;

        System.out.println("Maximum score that can be achieved is " + unit.maxResultOne(nums, k));
    }

    public int maxResultOne(int[] nums, int k) {

        if (nums.length == 1) {
            return nums[0];
        }

        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(nums[0], 1);
        int windowSize = 1;
        int index = 1;

        int resultArr[] = new int[nums.length];
        resultArr[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (windowSize < k) {
                int element = findMaxElement(map);
                pushElementToMap(element + nums[i], map);
                windowSize++;
                resultArr[index] = element + nums[i];
                index++;
            } else {
                int element = findMaxElement(map);
                pushElementToMap(element + nums[i], map);
                resultArr[index] = element + nums[i];
                index++;
                int indexNeededForRemoval = i - k;
                removeElementFromMap(resultArr[indexNeededForRemoval], map);
            }
        }
        return resultArr[nums.length - 1];
    }

    private void removeElementFromMap(int elementToBeRemoved, TreeMap<Integer, Integer> map) {
            int valueCount = map.get(elementToBeRemoved);
            if (valueCount == 1) {
                map.remove(elementToBeRemoved);
            } else {
                map.put(elementToBeRemoved, valueCount - 1);
            }
    }

    private void pushElementToMap(int element, TreeMap<Integer, Integer> map) {
        if (!map.containsKey(element)) {
            map.put(element, 1);
        } else {
            map.put(element, map.get(element) + 1);
        }
    }

    private int findMaxElement(TreeMap<Integer, Integer> map) {
        return map.lastKey();
    }
}
