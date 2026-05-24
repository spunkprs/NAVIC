package agoda;

import java.util.*;

/**
Problem : 3893
Link : https://leetcode.com/problems/maximum-team-size-with-overlapping-intervals/description/?envType=company&envId=agoda&favoriteSlug=agoda-all
You are given two integer arrays startTime and endTime of length n.

startTime[i] represents the start time of the ith employee.
endTime[i] represents the end time of the ith employee.

Two employees i and j can interact if their time intervals overlap. Two intervals are considered overlapping if
they share at least one common time point.

A team is valid if there exists at least one employee in the team who can interact with every other member of the team.

Return an integer denoting the maximum possible size of such a team.

Time Complexity = O(NLogN)
Space Complexity = O(N)

Got confused with the solution myself because of involved edge case scenarios, hence will deal with it later
Level : Medium
 * */

public class MaximumTeamSizeWithOverlappingIntervals {

    private int startTimeIndex = -1;
    private int endTimeIndex = -1;

    private List<TimeNode> startTimeNodes = new ArrayList<>();
    private List<TimeNode> endTimeNodes = new ArrayList<>();
    private Map<Integer, TimeNode> mapper = new HashMap<>();

    public static void main(String ar[]) {
        MaximumTeamSizeWithOverlappingIntervals unit = new MaximumTeamSizeWithOverlappingIntervals();
        int startTime[] = {6, 17};
        int endTime[] = {6, 19};

        System.out.print("Maximum team size with overlapping intervals is " + unit.maximumTeamSizeOne(startTime, endTime));
    }

    public int maximumTeamSizeOne(int[] startTime, int[] endTime) {
        prepareTimeNodes(startTime, endTime);
        int result = 0;

        for (TimeNode timeNode : startTimeNodes) {
            findOverlappingNodesUsingStartTime(timeNode, endTimeNodes);
            findOverlappingNodesUsingEndTime(mapper.get(timeNode.index), startTimeNodes);
            int resultOne = endTimeNodes.size() - startTimeIndex;
            int resultTwo = endTimeIndex == -1 ? 0 : startTimeNodes.size() - endTimeIndex;
            result = resultOne - resultTwo > result ? resultOne - resultTwo : result;
            startTimeIndex = -1;
            endTimeIndex = -1;
        }

        return result;
    }

    private void findOverlappingNodesUsingStartTime(TimeNode timeNode, List<TimeNode> endTimeNodes) {
        findStartTimeIndex(timeNode.time, 0, endTimeNodes.size() - 1, endTimeNodes);
    }

    private void findOverlappingNodesUsingEndTime(TimeNode timeNode, List<TimeNode> startTimeNodes) {
        findEndTimeIndex(timeNode.time, 0, startTimeNodes.size() - 1, startTimeNodes);
    }

    private void findStartTimeIndex(int time, int startIndex, int endIndex, List<TimeNode> endTimeNodes) {
        if (endIndex >= 0) {
            int midIndex = (startIndex + endIndex) / 2;
            int timeAtMidIndex = endTimeNodes.get(midIndex).time;
            if (time > timeAtMidIndex && time <= endTimeNodes.get(midIndex + 1).time) {
                startTimeIndex = midIndex + 1;
                return;
            } else if (time >= timeAtMidIndex) {
                findStartTimeIndex(time, midIndex + 1, endIndex, endTimeNodes);
            } else if (time <= timeAtMidIndex) {
                findStartTimeIndex(time, startIndex, midIndex - 1, endTimeNodes);
            }
        } else {
            startTimeIndex = 0;
        }
    }

    private void findEndTimeIndex(int time, int startIndex, int endIndex, List<TimeNode> startTimeNodes) {
            int midIndex = (startIndex + endIndex) / 2;
            int timeAtMidIndex = startTimeNodes.get(midIndex).time;
            if (midIndex + 1 <= endIndex) {
                if (time > timeAtMidIndex && time < startTimeNodes.get(midIndex + 1).time) {
                    endTimeIndex = midIndex + 1;
                    return;
                } if (time < timeAtMidIndex && time > startTimeNodes.get(midIndex - 1).time) {
                    endTimeIndex = midIndex;
                    return;
                } else if (time > timeAtMidIndex) {
                    findEndTimeIndex(time, midIndex + 1, endIndex, startTimeNodes);
                } else if (time < timeAtMidIndex) {
                    findEndTimeIndex(time, startIndex, midIndex - 1, startTimeNodes);
                }
            } else {
                endTimeIndex = -1;
            }
    }

    private void prepareTimeNodes(int[] startTimes, int[] endTimes) {
        for (int i = 0; i < startTimes.length; i++) {
            startTimeNodes.add(new TimeNode(startTimes[i], i));
        }

        for (int i = 0; i < endTimes.length; i++) {
            endTimeNodes.add(new TimeNode(endTimes[i], i));
        }

        for (TimeNode tNode : startTimeNodes) {
            mapper.put(tNode.index, endTimeNodes.get(tNode.index));
        }

        Collections.sort(startTimeNodes, (a, b) -> {
            if (a.time < b.time) {
                return -1;
            } else if (a.time > b.time) {
                return 1;
            } else {
                return 0;
            }
        });

        Collections.sort(endTimeNodes, (a, b) -> {
            if (a.time < b.time) {
                return -1;
            } else if (a.time > b.time) {
                return 1;
            } else {
                return 0;
            }
        });
    }

    static class TimeNode {
        private int time;
        private int index;

        public TimeNode(int time, int index) {
            this.time = time;
            this.index = index;
        }
    }

}
