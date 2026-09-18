package microsoft.greedy;

import java.util.*;

/**
Problem : 1353
Level : Medium
Link : https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended/description/
Reference : https://www.youtube.com/watch?v=dTVB1W7-BvY

You are given an array of events where events[i] = [startDayi, endDayi]. Every event i starts at startDayi and ends at endDayi.

You can attend an event i at any day d where startDayi <= d <= endDayi. You can only attend one event at any time d.

Return the maximum number of events you can attend.

Constraints:-

a.) 1 <= events.length <= 10^5
b.) events[i].length == 2
c.) 1 <= startDayi <= endDayi <= 10^5

Time Complexity = O(N * log(N))
Explicit time complexity = O(N)
 * */

public class MaximumNumberOfEventsThatCanBeAttended {

    public static void main(String ar[]) {
        MaximumNumberOfEventsThatCanBeAttended unit = new MaximumNumberOfEventsThatCanBeAttended();
        //int events[][] = {{1, 2}, {2, 3}, {3, 4}, {1, 2}};
        int events[][] = {{1,4},{4,4},{2,2},{3,4},{1,1}};
        System.out.println("Max events that can be attended is " + unit.maxEventsOne(events));
    }

    public int maxEventsOne(int[][] events) {
        Arrays.sort(events, Comparator.comparingInt(a -> a[0]));
        int index = 0;
        int day = 1;
        int result = 0;

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        while (!minHeap.isEmpty() || index < events.length) {
            if (minHeap.isEmpty()) {
                day = events[index][0];
            }

            while (index < events.length && events[index][0] <= day) {
                minHeap.add(events[index][1]);
                index++;
            }

            minHeap.poll();
            day++;
            result++;

            while (!minHeap.isEmpty() && minHeap.peek() < day) {
                minHeap.poll();
            }
        }

        return result;
        //return 0;
    }
}
