package multithreading.problems.circuitbreaker;

import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Closed extends State {

    private long timeBasedSlidingWindow;
    private double failureRateThreshold;
    private TreeMap<Long, Node> map = new TreeMap<>();
    private ReentrantLock lock;
    private AtomicInteger failedRequestCountAcrossSlingWindow;
    private static Closed instance;
    private static ReentrantLock instanceLock = new ReentrantLock();

    private Closed(long timeBasedSlidingWindow, double failureRateThreshold) {
        this.timeBasedSlidingWindow = timeBasedSlidingWindow;
        this.failureRateThreshold = failureRateThreshold;
        this.lock = new ReentrantLock();
        this.failedRequestCountAcrossSlingWindow = new AtomicInteger(0);
    }

    private void resetStates() {
        this.failedRequestCountAcrossSlingWindow = new AtomicInteger(0);
        this.map = new TreeMap<>();
    }

    public static Closed fetchInstance() {
        if (instance != null) {
            return instance;
        } else {
            try {
                instanceLock.lock();
                if (instance == null) {
                    instance = new Closed(AllowedStatesConfigurations.CLOSED_STATE_TIME_BASED_SLIDING_WINDOW,
                            AllowedStatesConfigurations.CLOSED_STATE_FAILURE_RATE_THRESHOLD);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                instanceLock.unlock();
            }
        }
        return instance;
    }

    @Override
    public State makeTransition() {
        return Open.fetchInstance();
    }

    public Result makeCallToApi(String url) {
        Result finalResult = null;
        lock.lock();
        Pair resultingPair = provideGenericApiBehaviour(url);
        try {
            if (map.isEmpty()) {
                if (!map.containsKey(resultingPair.getResponseTimeStamp())) {
                    if (!resultingPair.isResponse()) {
                        Node node = new Node(resultingPair.getResponseTimeStamp(), new AtomicInteger(1));
                        map.put(resultingPair.getResponseTimeStamp(), node);
                        this.failedRequestCountAcrossSlingWindow.incrementAndGet();
                    }
                }
            } else if (map.lastKey() - map.firstKey() < this.timeBasedSlidingWindow) {
                if (!map.containsKey(resultingPair.getResponseTimeStamp())) {
                    if (!resultingPair.isResponse()) {
                        Node node = new Node(resultingPair.getResponseTimeStamp(), new AtomicInteger(1));
                        map.put(resultingPair.getResponseTimeStamp(), node);
                        this.failedRequestCountAcrossSlingWindow.incrementAndGet();
                    }
                } else {
                    Node node = map.get(resultingPair.getResponseTimeStamp());
                    if (!resultingPair.isResponse()) {
                        node.failedRequestCount.incrementAndGet();
                        this.failedRequestCountAcrossSlingWindow.addAndGet(node.failedRequestCount.get());
                    }
                }
            } else {
                double computedFailureRate = (this.failedRequestCountAcrossSlingWindow.get() / this.timeBasedSlidingWindow);
                if (computedFailureRate >= failureRateThreshold) {
                    State transitionedState = makeTransition();
                    resetStates();
                    finalResult = new Result(resultingPair, transitionedState);
                } else {
                    AtomicInteger failedRequestCount = map.get(map.firstKey()).failedRequestCount;
                    this.failedRequestCountAcrossSlingWindow.addAndGet(-failedRequestCount.get());
                    map.remove(map.firstKey());
                    finalResult = new Result(resultingPair, this);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return finalResult;
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
