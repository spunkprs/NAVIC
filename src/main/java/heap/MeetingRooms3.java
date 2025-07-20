package heap;

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

 * */

public class MeetingRooms3 {

    private MeetingRooms3 unit = new MeetingRooms3();


    public static void main(String ar[]) {

    }

    public int mostBooked(int[][] meetings, int rooms) {

        // Replace this placeholder return statement with your code
        return -1;
    }

}
