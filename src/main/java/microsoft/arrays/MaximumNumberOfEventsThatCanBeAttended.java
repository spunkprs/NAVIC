package microsoft.arrays;

import java.util.*;

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
