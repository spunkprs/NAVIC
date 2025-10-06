package multithreading.ratelimiter;

import java.util.concurrent.atomic.AtomicLong;


/**
 This is the implementation for SlidingWindowCounter algorithm that can be used against RateLimiting

 It shall have following things as parameters :-
 1.) allowedRequests within timeWindowLength
 2.) timeWindowLength


 This approach will work just fine but downside with it is the heavy usage of pessimistic locking which needs to be improved !!

 Reference material : https://medium.com/@devenchan/implementing-rate-limiting-in-java-from-scratch-fixed-window-and-sliding-window-implementation-a6e8d6407d17
 * */

public class SlidingWindowCounter implements RateLimiter {

    private long allowedRequests;
    private long timeWindowLength;

    private Node head;
    private Node tail;

    private Object lockForInitialisation = new Object();
    private Object lockForInsertion = new Object();
    private Object lockForRemoval = new Object();

    private AtomicLong queueSize = new AtomicLong();

    public SlidingWindowCounter(long allowedRequests, long timeWindowLength) {
        this.allowedRequests = allowedRequests;
        this.timeWindowLength = timeWindowLength;
    }

    @Override
    public boolean tryAcquire() {
        long currentTimeStamp = System.currentTimeMillis();
        if (head != null) {
           return addNodeToTheQueue(currentTimeStamp);
        } else {
            synchronized (lockForInitialisation) {
                if (head == null) {
                    Node node = new Node(currentTimeStamp);
                    head = node;
                    tail = node;
                    return queueSize.incrementAndGet() < allowedRequests;
                }
            }
          return addNodeToTheQueue(currentTimeStamp);
            }
    }

    private boolean addNodeToTheQueue(long currentTimeStamp) {
        Node node = new Node(currentTimeStamp);

        if (currentTimeStamp - head.timeStamp < timeWindowLength) {
            synchronized (lockForInsertion) {
                if (queueSize.get() < allowedRequests) {
                    tail.nextNode = node;
                    tail = node;
                    queueSize.incrementAndGet();
                    return true;
                }
                //For other edge cases request can be dropped or we can add that request to get added to waiting queue instead
            }
        } else {
            synchronized (lockForRemoval) {
                while (currentTimeStamp - head.timeStamp > timeWindowLength && head != null) {
                    head = head.nextNode;
                    queueSize.decrementAndGet();
                }
            }
            if (currentTimeStamp - head.timeStamp < timeWindowLength) {
                synchronized (lockForInsertion) {
                    if (queueSize.get() < allowedRequests) {
                        tail.nextNode = node;
                        tail = node;
                        queueSize.incrementAndGet();
                        return true;
                    }
                    //For other edge cases request can be dropped or we can add that request to get added to waiting queue instead
                }
            }
        }
        return false;
    }

    static class Node {
        private long timeStamp;
        private Node nextNode;

        public Node(long timeStamp) {
            this.timeStamp = timeStamp;
        }
    }
}
