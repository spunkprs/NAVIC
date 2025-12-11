package leetcode75;

import java.util.TreeMap;

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
