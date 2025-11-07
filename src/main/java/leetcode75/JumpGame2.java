package leetcode75;

import java.util.*;

/**
Problem : 45

You are given a 0-indexed array of integers nums of length n. You are initially positioned at index 0.

Each element nums[i] represents the maximum length of a forward jump from index i. In other words, if you are at index i, you can jump to any index (i + j) where:

a.) 0 <= j <= nums[i] and
b.) i + j < n

Return the minimum number of jumps to reach index n - 1. The test cases are generated such that you can reach index n - 1

Constraints:-

a.) 1 <= nums.length <= 104
b.) 0 <= nums[i] <= 1000
c.) It's guaranteed that you can reach nums[n - 1]

Note : Need to improve time complexity of the solution, improvised time is O(n)
 * */

public class JumpGame2 {

    public static void main(String ar[]) {

        JumpGame2 unit = new JumpGame2();
        int nums[] = {4,1,1,3,1,1,1};

        System.out.println("Minimum number of jumps required to reach end is " + unit.jump(nums));
    }

    public int jump(int nums[]) {
        if (nums.length == 1) {
            return 0;
        }
        return processToComputeMinimumJumpsToReachEnd(nums);
    }

    /*private int processToComputeMinimumJumpsToReachEndOne(int nums[]) {
        int memory[] = new int[nums.length];
        memory[0] = 0;
        int minJumpsForIndex;
        for (int i = 1; i < nums.length; i++) {
            minJumpsForIndex = Integer.MAX_VALUE;
            for (int j = i - 1; j >= 0; j--) {
                if ( i - j <= nums[j]) {
                    minJumpsForIndex = memory[j] + 1 < minJumpsForIndex ? memory[j] + 1 : minJumpsForIndex;
                }
            }
            memory[i] = minJumpsForIndex;
        }
        return memory[nums.length - 1];
    }*/

    private int processToComputeMinimumJumpsToReachEnd(int nums[]) {
        int rightIndex = 0;
        int leftIndex = 0;
        int minJump = 0;

        PriorityQueue<Integer> maxHeapOne = new PriorityQueue(new ReverseComparator());
        PriorityQueue<Integer> maxHeapTwo = new PriorityQueue(new ReverseComparator());

        maxHeapOne.add(leftIndex);
        maxHeapTwo.add(rightIndex);

        do {
            rightIndex = maxHeapTwo.peek();
            leftIndex = maxHeapOne.peek();

            maxHeapTwo = new PriorityQueue(new ReverseComparator());
            maxHeapOne = new PriorityQueue(new ReverseComparator());

            int index = leftIndex;

            while (index <= rightIndex) {
                if (nums[index] != 0) {
                    maxHeapTwo.add(index + nums[index]);
                    maxHeapOne.add(index + 1);
                }
                index++;
            }

            minJump++;

            rightIndex = maxHeapTwo.peek();
            leftIndex = maxHeapOne.peek();

        } while (rightIndex < nums.length - 1);

        return minJump;
    }

    static class ReverseComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer a, Integer b) {
            return a < b ? 1 : a == b ? 0 : -1;
        }
    }
}
