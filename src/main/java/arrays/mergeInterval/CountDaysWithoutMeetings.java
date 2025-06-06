package arrays.mergeInterval;

import java.util.Arrays;

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

        int meetings [][] = {{10, 12}, {1, 5}, {4, 8}};
        int days = 12;

        /*int meetings [][] = {{7, 9}, {3, 6}};
        int days = 10;*/

        System.out.println("Days when the employee is available for work but has no scheduled meetings is " + unit.countDays(days, meetings));
    }

    public int countDays(int days, int[][] meetings) {

        Pair pairMeetings[] = computePairs(meetings);

        //Sort provided pairs depending on the start time, before we start merging the intervals
        Arrays.sort(pairMeetings);

        Node headNode = conversionToNodePair(pairMeetings);

        processToMergeIntervals(headNode);
        return days - computeCountDays(headNode);
    }

    private void processToMergeIntervals(Node headNode) {
        Node node = headNode;
        Node nextNode = null;

        while (node != null) {
            nextNode = node.next;
            if (nextNode != null) {
                if (nextNode.pair.startTime > node.pair.endTime) {
                    node = nextNode;
                } else {
                    Node next = nextNode.next;
                    Pair mergedPair = new Pair(node.pair.startTime, Math.max(node.pair.endTime, nextNode.pair.endTime));
                    node.pair = mergedPair;
                    node.next = next;
                }
            } else {
                node = nextNode;
            }
        }
    }

    private int computeCountDays(Node head) {
        int countDays = 0;
        Node node = head;

        while (node != null) {
            countDays+= node.pair.endTime - node.pair.startTime + 1;
            node = node.next;
        }
        return countDays;
    }

    /**
     * This the logic that's responsible for merging overlapping intervals
     * */

    private Node conversionToNodePair(Pair[] pairMeetings) {
        Node head = null;
        Node tail = null;
        for (Pair pair : pairMeetings) {
            if (head == null) {
                head = new Node(pair);
                tail = head;
            } else {
                Node node = new Node(pair);
                tail.next = node;
                tail = node;
            }
        }
        return head;
    }

    private Pair[] computePairs(int [][] inputInterval) {
        Pair pairs[] = new Pair[inputInterval.length];
        for (int i = 0; i < inputInterval.length; i++) {
            Pair pair = new Pair(inputInterval[i][0], inputInterval[i][1]);
            pairs[i] = pair;
        }
        return pairs;
    }

    static class Pair implements Comparable<Pair> {
        private int startTime;
        private int endTime;

        public Pair(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public int compareTo(Pair object) {
            return this.startTime - object.startTime;
        }
    }

    static class Node {
        private Pair pair;
        private Node next;

        public Node(Pair pair) {
            this.pair = pair;
        }
    }
}
