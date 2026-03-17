package multithreading.problems.ratelimiter.slidingWindowLog;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
Proper implementation of Sliding Window Log algorithm internally used by Rate Limiters
Questions can be raised around throughput of the system but in this system correctness is given priority over throughput
 * */

public class RateLimitingSystem {

    private int numberOFRequestsAllowed;
    private long slidingWindowLength;
    private int currentRequestCount;

    private Node head;
    private Node tail;

    private ReentrantLock lock = new ReentrantLock();

    public RateLimitingSystem(int numberOFRequestsAllowed, long slidingWindowLength) {
        this.numberOFRequestsAllowed = numberOFRequestsAllowed;
        this.slidingWindowLength = slidingWindowLength;
    }

    public boolean acceptRequest(long requestTimeStamp) {
        boolean result = true;
        try {
            /**
             This will not make the requesting threads to block forever for the lock to be attained, simple lock() method will keep on waiting till it gets interrupted by
             some other thread OR the lock is attained by the waiting thread
             * */
            result = lock.tryLock(100, TimeUnit.MILLISECONDS);
            if (result) {
                Node requestNode = new Node(requestTimeStamp);
                insertRequestAndPerformInternalOperation(requestNode);
            }
        } catch (InterruptedException e) {
            result = false;
            e.printStackTrace();
        }
        return result;
    }

    private void insertRequestAndPerformInternalOperation(Node requestNode) {
        if (head == null) {
            head = requestNode;
            tail = head;
            currentRequestCount++;
        } else {
            if (requestNode.getTimeStamp() - head.getTimeStamp() >= slidingWindowLength) {
                while (requestNode.getTimeStamp() - head.getTimeStamp() >= slidingWindowLength) {
                    head = head.getNext();
                    currentRequestCount--;
                }
            } else {
                if (currentRequestCount >= numberOFRequestsAllowed) {
                    while (currentRequestCount >= numberOFRequestsAllowed) {
                        head = head.getNext();
                        currentRequestCount--;
                    }
                } else {
                    tail.setNext(requestNode);
                    tail = requestNode;
                    currentRequestCount++;
                }
            }
        }
    }

}
