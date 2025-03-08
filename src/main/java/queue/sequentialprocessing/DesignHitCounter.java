package queue.sequentialprocessing;

import java.util.HashMap;
import java.util.Map;

/*
*
Design a hit counter which counts the number of hits received in the past 5 minutes (i.e., the past 300 seconds).

Your system should accept a timestamp parameter (in seconds granularity), and you may assume that calls are being made to the system in chronological order (i.e., timestamp is monotonically increasing). Several hits may arrive roughly at the same time.

Implement the HitCounter class:

HitCounter() Initializes the object of the hit counter system.
void hit(int timestamp) Records a hit that happened at timestamp (in seconds). Several hits may happen at the same timestamp.
int getHits(int timestamp) Returns the number of hits in the past 5 minutes from timestamp (i.e., the past 300 seconds).
*
*
*
* Example 1:

Input
["HitCounter", "hit", "hit", "hit", "getHits", "hit", "getHits", "getHits"]
[[], [1], [2], [3], [4], [300], [300], [301]]
Output
[null, null, null, null, 3, null, 4, 3]

Explanation
HitCounter hitCounter = new HitCounter();
hitCounter.hit(1);       // hit at timestamp 1.
hitCounter.hit(2);       // hit at timestamp 2.
hitCounter.hit(3);       // hit at timestamp 3.
hitCounter.getHits(4);   // get hits at timestamp 4, return 3.
hitCounter.hit(300);     // hit at timestamp 300.
hitCounter.getHits(300); // get hits at timestamp 300, return 4.
hitCounter.getHits(301); // get hits at timestamp 301, return 3.
*
*
* */

public class DesignHitCounter {

    //Utility to test the code
    public static void main(String ar[]) {
        DesignHitCounter designHitCounter = new DesignHitCounter();
        designHitCounter.hit(2);
        designHitCounter.hit(3);
        designHitCounter.hit(4);
        System.out.println("Number of hits at timestamp 300 : " + designHitCounter.getHits(300));
        System.out.println("Number of hits at timestamp 301 : " + designHitCounter.getHits(301));
        System.out.println("Number of hits at timestamp 302 : " + designHitCounter.getHits(302));
        System.out.println("Number of hits at timestamp 303 : " + designHitCounter.getHits(303));
        System.out.println("Number of hits at timestamp 304 : " + designHitCounter.getHits(304));
        designHitCounter.hit(501);
        System.out.println("Number of hits at timestamp 600 : " + designHitCounter.getHits(600));
    }

    private Map<Integer, HitCounterNode> hitCounterMap = new HashMap<>();
    private HitCounterNode head;
    private HitCounterNode tail;

    public void hit(int timestamp) {
        if (!hitCounterMap.containsKey(timestamp)) {
            if (head == null) {
                head = new HitCounterNode();
                head.setTimestamp(timestamp);
                head.setNumberOfHits(1);
                tail = head;
            } else {
                HitCounterNode node = new HitCounterNode();
                node.setTimestamp(timestamp);
                node.setNumberOfHits(1);
                tail.setNext(node);
                tail = node;
            }
            hitCounterMap.put(timestamp, tail);
        } else {
            HitCounterNode node = hitCounterMap.get(timestamp);
            node.setNumberOfHits(node.getNumberOfHits() + 1);
        }
        removeUnwantedHits(0, false);
    }

    private void removeUnwantedHits(int startTimeStamp, boolean flag) {
        int endTimestamp = tail.getTimestamp();
        int startTimestampToBeConsidered = flag ? startTimeStamp : endTimestamp - 300;
        if (startTimestampToBeConsidered > 0) {
            HitCounterNode node = head;
            int startTs = node.getTimestamp();
            while (node != null && startTs <= startTimestampToBeConsidered) {
                if (hitCounterMap.containsKey(startTs)) {
                    hitCounterMap.remove(startTs);
                }
                node = node.getNext();
                startTs = node != null ? node.getTimestamp() : -1;
            }
            head = node;
        }
    }

    public int getHits(int timestamp) {
        if (head == null) {
            return 0;
        }
        int startTimestampToBeConsidered = timestamp - 300;

        if (head.getTimestamp() > startTimestampToBeConsidered || startTimestampToBeConsidered <= 0) {
            return processToComputeTotalNumberOfHits();
        }
        else {
            removeUnwantedHits(startTimestampToBeConsidered, true);
            return processToComputeTotalNumberOfHits();
        }
    }

    private int processToComputeTotalNumberOfHits() {
        int numberOfHits = 0;
        HitCounterNode node = head;
        if (node != null) {
            while (node != null) {
                numberOfHits += node.getNumberOfHits();
                node = node.getNext();
            }
        }
        return numberOfHits;
    }

    static class HitCounterNode {
        private int timestamp;
        private int numberOfHits;
        private HitCounterNode next;

        public int getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
        }

        public int getNumberOfHits() {
            return numberOfHits;
        }

        public void setNumberOfHits(int numberOfHits) {
            this.numberOfHits = numberOfHits;
        }

        public HitCounterNode getNext() {
            return next;
        }

        public void setNext(HitCounterNode next) {
            this.next = next;
        }
    }

}
