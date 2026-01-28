package leetcode75.intervals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NonOverlappingIntervals {

    public static void main(String ar[]) {
        NonOverlappingIntervals unit = new NonOverlappingIntervals();
        //int intervals[][] = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        int intervals[][] = {{1, 2}, {1, 2}, {1, 2}};
        System.out.print("Number of intervals to be erased to achieve max non overlapping intervals is " + unit.eraseOverlapIntervals(intervals));
    }

    public int eraseOverlapIntervals(int[][] intervals) {
        List<Interval> intervalList = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            intervalList.add(new Interval(intervals[i][0], intervals[i][1]));
        }

        Collections.sort(intervalList, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.endTime < o2.endTime ? -1 : o1.endTime > o2.endTime ? 1 : o1.startTime < o2.startTime ? -1 : o1.startTime > o2.startTime ? 1 : 0;
            }
        });

        int result = 0;

        int k = Integer.MIN_VALUE;

        for (Interval interval : intervalList) {
            if (interval.startTime >= k) {
                k = interval.endTime;
            } else {
                result++;
            }
        }

        return result;
    }

    static class Interval {
        private int startTime;
        private int endTime;

        public Interval(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }
}
