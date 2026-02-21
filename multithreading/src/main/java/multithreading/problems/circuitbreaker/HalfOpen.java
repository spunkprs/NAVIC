package multithreading.problems.circuitbreaker;

import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class HalfOpen extends State {

    private AtomicInteger requestsAllowed;
    private double failureRateThreshold;
    private long tokenRefillRate;

    private TreeMap<Long, Node> map = new TreeMap<>();
    private ReentrantLock lock;
    private AtomicInteger failedRequestCountAcrossSlingWindow;

    private static HalfOpen instance;
    private static ReentrantLock instanceLock = new ReentrantLock();

    @Override
    public State makeTransition() {
        return null;
    }

    @Override
    public Result makeCallToApi(String url) {
        return null;
    }

    public static HalfOpen fetchInstance() {
        if (instance != null) {
            return instance;
        } else {
            try {
                instanceLock.lock();
                if (instance == null) {
                    //instance = new Closed(AllowedStates.CLOSED_STATE_TIME_BASED_SLIDING_WINDOW, AllowedStates.CLOSED_STATE_FAILURE_RATE_THRESHOLD);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                instanceLock.unlock();
            }
        }
        return instance;
    }

    static class Node {
        private long timeStamp;
        private AtomicInteger failedRequestCount;

        public Node(long timeStamp, AtomicInteger failedRequestCount) {
            this.timeStamp = timeStamp;
            this.failedRequestCount = failedRequestCount;
        }
    }
}
