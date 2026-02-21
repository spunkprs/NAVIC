package multithreading.problems.circuitbreaker;

import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class HalfOpen extends State {

    private long timeBasedSlidingWindow;
    private double failureRateThreshold;

    private AtomicInteger requestsAllowed;
    private double tokenRefillRate;
    private AtomicInteger failedRequestCountAcrossSlingWindow;

    private TreeMap<Long, Node> map = new TreeMap<>();
    private ReentrantLock lock;

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

    private HalfOpen(long timeBasedSlidingWindow, double failureRateThreshold, int requestsAllowed, double tokenRefillRate) {
        this.timeBasedSlidingWindow = timeBasedSlidingWindow;
        this.failureRateThreshold = failureRateThreshold;
        this.requestsAllowed = new AtomicInteger(requestsAllowed);
        this.tokenRefillRate = tokenRefillRate;
    }

    public static HalfOpen fetchInstance() {
        if (instance != null) {
            return instance;
        } else {
            try {
                instanceLock.lock();
                if (instance == null) {
                    instance = new HalfOpen(AllowedStatesConfigurations.HALF_OPEN_STATE_TIME_BASED_SLIDING_WINDOW,
                            AllowedStatesConfigurations.HALF_OPEN_STATE_FAILURE_RATE_THRESHOLD,
                            AllowedStatesConfigurations.HALF_OPEN_REQUESTS_ALLOWED,
                            AllowedStatesConfigurations.HALF_OPEN_TOKEN_REFILL_RATE);
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
