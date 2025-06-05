package arrays.mergeInterval;

/**
 You are given a positive integer, days, which represents the total number of days an employee is available for work, starting from day 1,
 You are also given a 2D array, meetings, where each entry meetings[i] = [starti, endi] indicates that a meeting is scheduled from day
 starti to endi(both inclusive).

 Your task is to count the days when the employee is available for work but has no scheduled meetings

 Credits --> Educative
 References --> https://www.educative.io/interview-prep/coding/count-days-without-meetings
*/

public class CountDaysWithoutMeetings {

    public static void main(String ar[]) {
        CountDaysWithoutMeetings unit = new CountDaysWithoutMeetings();
    }

    public int countDays(int days, int[][] meetings) {

        // Replace this placeholder return statement with your code
        return -1;
    }

    static class Pair {
        private int startTime;
        private int endTime;

        public Pair(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }
}
