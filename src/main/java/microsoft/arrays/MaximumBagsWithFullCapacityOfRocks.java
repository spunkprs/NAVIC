package microsoft.arrays;

import java.util.PriorityQueue;

/**
You have n bags numbered from 0 to n - 1. You are given two 0-indexed integer arrays capacity and rocks.
The ith bag can hold a maximum of capacity[i] rocks and currently contains rocks[i] rocks. You are also
given an integer additionalRocks, the number of additional rocks you can place in any of the bags.

Return the maximum number of bags that could have full capacity after placing the additional rocks in some bags.


Constraints:-

a.) n == capacity.length == rocks.length
b.) 1 <= n <= 5 * 10^4
c.) 1 <= capacity[i] <= 10^9
d.) 0 <= rocks[i] <= capacity[i]
e.) 1 <= additionalRocks <= 10^9
 * */

public class MaximumBagsWithFullCapacityOfRocks {

    public static void main(String ar[]) {
        MaximumBagsWithFullCapacityOfRocks unit = new MaximumBagsWithFullCapacityOfRocks();
        int capacity[] = {91,54,63,99,24,45,78};
        int rocks[] = {35,32,45,98,6,1,25};
        int additionalRocks = 17;

        System.out.println("Maximum bags will be " + unit.maximumBags(capacity, rocks, additionalRocks));
    }

    public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {

        int maximumBags = 0;
        PriorityQueue<Integer> priorityQueue = buildPriorityQueue(capacity, rocks);
        while (!priorityQueue.isEmpty() && additionalRocks > 0) {
            int num = priorityQueue.peek();
            if (num > 0 && num <= additionalRocks) {
                additionalRocks -= num;
                maximumBags++;
            } else if (num == 0) {
                maximumBags++;
            }
            priorityQueue.poll();
        }

        return maximumBags;
    }

    private PriorityQueue<Integer> buildPriorityQueue(int[] capacity, int[] rocks) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < rocks.length; i++) {
            int difference = capacity[i] - rocks[i];
            priorityQueue.add(difference);
        }
        return priorityQueue;
    }

}
