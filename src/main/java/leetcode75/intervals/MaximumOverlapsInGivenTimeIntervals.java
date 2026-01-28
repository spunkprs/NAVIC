package leetcode75.intervals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
This problem revolves around finding maximum overlap provided among the intervals

Reference -->
a.) https://www.youtube.com/watch?v=Fdgxkvivn1s
b.) https://dilipkumar.medium.com/interval-coding-pattern-068c36197cf5

Time Complexity = O(N Log(N)), where N being number of intervals
Space Complexity = O(N)
 * */

public class MaximumOverlapsInGivenTimeIntervals {

    public static void main(String ar[]) {
        MaximumOverlapsInGivenTimeIntervals unit = new MaximumOverlapsInGivenTimeIntervals();
        //int intervals[][] = {{0, 2}, {1, 3}, {3, 5}, {5, 8}, {9, 10}};
        int intervals[][] = {{0, 2}, {3, 5}, {9, 10}};
        System.out.println("Maximum overlapping intervals count is " + unit.fetchMaximumOverlaps(intervals));
    }


    /**
    This method consider no overlap when ending time of one interval is same as starting time of another interval
     * */
    public int fetchMaximumOverlaps(int intervals[][]) {
        int maxOverlaps = 0;
        List<Interval> intervalList = new ArrayList<>();

        for (int i = 0; i < intervals.length; i++) {
            intervalList.add(new Interval(intervals[i][0], "S"));
            intervalList.add(new Interval(intervals[i][1], "E"));
        }

        Collections.sort(intervalList, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.time < o2.time ? -1 : o1.time == o2.time ? o1.type.compareTo(o2.type) : 1;
            }
        });

        Interval lastProcessedInterval = null;
        int length = 0;

        for (Interval interval : intervalList) {
            if (interval.type.equals("S")) {
                if (lastProcessedInterval == null) {
                    lastProcessedInterval = interval;
                    length++;
                } else {
                    length++;
                    if (lastProcessedInterval.type.equals("S")) {
                        maxOverlaps = length > maxOverlaps ? length : maxOverlaps;
                    }
                    lastProcessedInterval = interval;
                }
            } else {
                length--;
                lastProcessedInterval = interval;
            }
        }
        return maxOverlaps;
    }

    class Interval {
        private int time;
        private String type;

        public Interval(int time, String type) {
            this.time = time;
            this.type = type;
        }
    }
}
