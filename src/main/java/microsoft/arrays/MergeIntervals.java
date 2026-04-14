package microsoft.arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
Problem : 56
Given an array of intervals where intervals[i] = [starti, endi], merge all
overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.

 Constraints:-

a.) 1 <= intervals.length <= 10^4
b.) intervals[i].length == 2
c.) 0 <= starti <= endi <= 10^4

Time Complexity = O(N*Log(N))
Space Complexity = O(N)

Reference : https://www.youtube.com/watch?v=44H3cEC2fFM
 * */

public class MergeIntervals {

    public static void main(String ar[]) {
        MergeIntervals unit = new MergeIntervals();

       int intervals[][] = {{1,3},{2,6},{8,10},{15,18}};
       int result[][] = unit.merge(intervals);
    }

    public int[][] merge(int[][] intervals) {

        List<Interval> intervalList = new ArrayList<>();
        List<Interval> result = new ArrayList<>();

        for (int i = 0; i < intervals.length; i++) {
            intervalList.add(new Interval(intervals[i][0], intervals[i][1]));
        }

        Collections.sort(intervalList, (x, y) -> {
            return x.startTime < y.startTime ? -1 : x.startTime > y.startTime ? 1 : 0;
        });

        processToMergeIntervals(intervalList, result);

        int finalResult[][] = new int[result.size()][2];

        for (int i = 0; i < result.size(); i++) {
            finalResult[i][0] = result.get(i).startTime;
            finalResult[i][1] = result.get(i).endTime;
        }

        return finalResult;
    }

    private void processToMergeIntervals(List<Interval> intervalList, List<Interval> result) {
        Interval previousInterval = intervalList.get(0);

        for (int i = 1; i < intervalList.size(); i++) {
            Interval currentInterval = intervalList.get(i);
            if (currentInterval.startTime <= previousInterval.endTime) {
                previousInterval = new Interval(previousInterval.startTime, Math.max(previousInterval.endTime, currentInterval.endTime));
            } else {
                result.add(previousInterval);
                previousInterval = currentInterval;
            }
        }
        result.add(previousInterval);
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
