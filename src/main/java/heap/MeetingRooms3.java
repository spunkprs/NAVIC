package heap;

import java.util.*;

/**
 Given an integer, rooms, which represents the total number of rooms, where each room is numbered from 0 to rooms - 1. Additionally, you are given a
 2D integer array called meetings, where each element meetings[i] = [start,end]
 indicates that a meeting will be held in the half-closed interval [start,end)

 Each startIndex is unique.

 Following are the rules for meeting room allocation :-

 1.) Meetings are allocated to rooms in the following manner:
 2.) Each meeting will take place in the unused room with the lowest number.
 3.) If there are no available rooms, the meeting will be delayed until a room becomes free.
 The delayed meeting should have the same duration as the original meeting.
 4.) When a room is vacated, the meeting with the earliest original start time is given priority for that room.

 Task is to determine the room number that hosted the most meetings. If there are multiple rooms, return the room with the lowest number.

 Constraints :-

 Constraints:

 1.) 1≤ rooms ≤100
 2.) 1≤ meetings.length ≤1000
 3.) meetings[i].length == 2
 4.) 0<= startIndex < endIndex <=10000

 Source --> Leetcode && Educative

 * */

public class MeetingRooms3 {

    public static void main(String ar[]) {

        //int meetings[][] = {{0,5}, {5,6}, {1,2}, {2,3}, {3,4}};

        int meetings[][] = {{0,4}, {1,4}, {2,5}, {3,6}, {4,7}, {5,8}};
        int rooms = 3;

        MeetingRooms3 unit = new MeetingRooms3();

        System.out.println("Room to hold max number of meetings :: " + unit.mostBooked(meetings, rooms));
    }

    public int mostBooked(int[][] meetings, int rooms) {
        Map<Integer, Integer> roomToCountOfMeetingsHoldMap = new HashMap<>();
        Pair meetingTimes[] = new Pair[meetings.length];
        generateStartTimes(meetingTimes, meetings);
        processToFindRoomThatHostedMaxMeetings(rooms, meetingTimes, roomToCountOfMeetingsHoldMap);

        if (roomToCountOfMeetingsHoldMap.size() != 0) {
            return processToFetchRoomThatHoldsMaxMeetings(roomToCountOfMeetingsHoldMap);
        }
        return -1;
    }

    private int processToFetchRoomThatHoldsMaxMeetings(Map<Integer, Integer> roomToCountOfMeetingsHoldMap) {
        int maxBookings = -1;
        int chosenRoomNum = -1;
        for (int roomNum : roomToCountOfMeetingsHoldMap.keySet()) {
            if (roomToCountOfMeetingsHoldMap.get(roomNum) > maxBookings) {
                maxBookings = roomToCountOfMeetingsHoldMap.get(roomNum);
                chosenRoomNum = roomNum;
            } else if (roomToCountOfMeetingsHoldMap.get(roomNum) == maxBookings) {
                chosenRoomNum = roomNum < chosenRoomNum ? roomNum : chosenRoomNum;
            }
        }
        return chosenRoomNum;
    }

    private void generateStartTimes(Pair[] meetingTimes, int[][] meetings) {
        for (int i = 0; i < meetings.length; i++) {
            meetingTimes[i] = new Pair(meetings[i][0], meetings[i][1]);
        }
        Arrays.sort(meetingTimes);
    }

    private void processToFindRoomThatHostedMaxMeetings(int rooms, Pair meetingTimes[], Map<Integer, Integer> roomToCountOfMeetingsHoldMap) {
        PriorityQueue<Integer> roomsMinHeap = new PriorityQueue<>();
        PriorityQueue<EndTimeWithAllotedRom> endTimeWithAllotedRoms = new PriorityQueue<>(new EndTimesComparator());

        for (int i = 0; i < rooms; i++) {
            roomsMinHeap.add(i);
        }

        int meetingsLength = meetingTimes.length;
        int index = 0;

        while (index < meetingsLength) {
            if (index == 0) {
                int roomNum = roomsMinHeap.peek();
                EndTimeWithAllotedRom endTimeWithAllotedRom = new EndTimeWithAllotedRom(meetingTimes[index].endTime, roomNum);
                endTimeWithAllotedRoms.add(endTimeWithAllotedRom);
                roomsMinHeap.poll();
                updateMap(roomToCountOfMeetingsHoldMap, roomNum);
            } else {
                if (meetingTimes[index].startTime < endTimeWithAllotedRoms.peek().endTime) {
                    if (!roomsMinHeap.isEmpty()) {
                        int availableRoomNum = roomsMinHeap.poll();
                        EndTimeWithAllotedRom endTimeWithAllotedRom = new EndTimeWithAllotedRom(meetingTimes[index].endTime, availableRoomNum);
                        endTimeWithAllotedRoms.add(endTimeWithAllotedRom);
                        updateMap(roomToCountOfMeetingsHoldMap, availableRoomNum);
                    } else {
                        //When there are no rooms available
                        int difference = endTimeWithAllotedRoms.peek().endTime - meetingTimes[index].startTime;
                        meetingTimes[index].startTime += difference;
                        meetingTimes[index].endTime += difference;

                        for (int i = index + 1; i < meetingTimes.length; i++) {
                            if (meetingTimes[i].startTime < meetingTimes[i - 1].startTime) {
                                meetingTimes[i].startTime += difference;
                                meetingTimes[i].endTime += difference;
                            }
                        }
                    }
                } else {
                    EndTimeWithAllotedRom polled = endTimeWithAllotedRoms.poll();
                    roomsMinHeap.add(polled.allotedRoom);

                    int availableRoomNum = roomsMinHeap.poll();
                    EndTimeWithAllotedRom endTimeWithAllotedRom = new EndTimeWithAllotedRom(meetingTimes[index].endTime, availableRoomNum);
                    endTimeWithAllotedRoms.add(endTimeWithAllotedRom);
                    updateMap(roomToCountOfMeetingsHoldMap, availableRoomNum);
                }
            }
            index++;
        }
    }

    private void updateMap(Map<Integer, Integer> roomToCountOfMeetingsHoldMap, int roomNum) {
        if (!roomToCountOfMeetingsHoldMap.containsKey(roomNum)) {
            roomToCountOfMeetingsHoldMap.put(roomNum, 1);
        } else {
            roomToCountOfMeetingsHoldMap.put(roomNum, roomToCountOfMeetingsHoldMap.get(roomNum) + 1);
        }
    }

    static class Pair implements Comparable<Pair> {

        private int startTime;
        private int endTime;

        public Pair(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public int compareTo(Pair pairObject) {
            return this.startTime < pairObject.startTime ? -1 : this.startTime == pairObject.startTime ? 0 : 1;
        }
    }

    static class EndTimeWithAllotedRom {

        private int endTime;
        private int allotedRoom;

        public EndTimeWithAllotedRom(int endTime, int allotedRoom) {
            this.endTime = endTime;
            this.allotedRoom = allotedRoom;
        }

    }

    static class EndTimesComparator implements Comparator<EndTimeWithAllotedRom> {

        @Override
        public int compare(EndTimeWithAllotedRom objOne, EndTimeWithAllotedRom objTwo) {
            return objOne.endTime < objTwo.endTime ? -1 : objOne.endTime == objTwo.endTime ? 0 : 1;
        }
    }

}
