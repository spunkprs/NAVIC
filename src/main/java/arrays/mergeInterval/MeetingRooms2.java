package arrays.mergeInterval;

import java.util.Arrays;

/**
 We are given an input array of meeting time intervals, intervals, where each interval has a start time and an end time.
 Your task is to find the minimum number of meeting rooms required to hold these meetings

 Constraints :-
 a.) 1 <= intervals.length <= pow(10,3)
 b.) 0 ≤ startIndex < endIndex <= pow(10, 6)


 Credits --> Educative
 References -->
 a.) https://www.educative.io/interview-prep/coding/meeting-rooms-ii
 b.) https://www.youtube.com/watch?v=FdzJmTCVyJU
* */

public class MeetingRooms2 {

    public static void main(String ar[]) {
        MeetingRooms2 meetingRooms = new MeetingRooms2();

        int inputIntervals [][] = {{1, 7}, {2, 6}, {3, 7}, {4, 8}, {5, 8}, {2, 9}, {1, 8}};
        System.out.println("Minimum number of meeting rooms required to schedule above meetings is " + meetingRooms.findSets(inputIntervals));
    }

    public int findSets(int[][] intervals) {
        int startIntervals[] = new int[intervals.length];
        int endIntervals[] = new int[intervals.length];

        //To handle the case when only one schedule is provided
        if (intervals.length == 1) {
            return 1;
        }

        //populate start && end intervals
        populateStartAndEndIntervals(startIntervals, endIntervals, intervals);

        //sort start && end intervals in ascending order
        Arrays.sort(startIntervals);
        Arrays.sort(endIntervals);

        return processToFindMinimumNumberOfRoomsNeededToEntertainMeetings(startIntervals, endIntervals);
    }

    /**
    Function responsible for finding minimum number of rooms to schedule provided meetings
    * */
    private int processToFindMinimumNumberOfRoomsNeededToEntertainMeetings(int[] startIntervals, int[] endIntervals) {
        int numberOfRooms = 0;
        int roomsCount = 0;
        int i = 0, j = 0;

        while (i < startIntervals.length || j < endIntervals.length) {

            if (i < startIntervals.length && j < endIntervals.length) {
                if (startIntervals[i] < endIntervals[j]) {
                    roomsCount++;
                    numberOfRooms = updateMinimumNumberOfRoomesNeeded(numberOfRooms, roomsCount);
                    i++;
                } else if (startIntervals[i] >= endIntervals[j]) {
                    roomsCount--;
                    numberOfRooms = updateMinimumNumberOfRoomesNeeded(numberOfRooms, roomsCount);
                    j++;
                }

                /**
                 No need to update roomsCount && numberOfRooms here because by here numberOfRooms would have already been calculated,
                 condition i < startIntervals.length && j >= endIntervals.length is not considered because it's not going to happen aka
                 meeting has already ended but not yet started
                * */
            } else if (i >= startIntervals.length && j < endIntervals.length) {
                j++;
            }
        }
        return numberOfRooms;
    }

    private int updateMinimumNumberOfRoomesNeeded(int numberOfRooms, int roomsCount) {
        return roomsCount > numberOfRooms ? roomsCount : numberOfRooms;
    }

    private void populateStartAndEndIntervals(int[] startIntervals, int[] endIntervals, int[][] intervals) {
        for (int i = 0; i < intervals.length; i++) {
            for (int j = 0; j < intervals[i].length; j++) {
                if (j == 0) {
                    startIntervals[i] = intervals[i][j];
                } else {
                    endIntervals[i] = intervals[i][j];
                }
            }
        }
    }
}
