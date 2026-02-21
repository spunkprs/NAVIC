package multithreading.problems.circuitbreaker;

import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
In HALF_OPEN implementation have made use of following algorithms internally :-
a.) Token Bucket Algorithm
b.) Sliding Window log algorithm
 * */

public class HalfOpen extends State {

    private long timeBasedSlidingWindow;
    private double failureRateThreshold;

    private AtomicInteger requestsAllowed;
    private double tokenRefillRate;
    private AtomicInteger failedRequestCountAcrossSlingWindow;
    private long timeForLastConsideredRequest;

    private TreeMap<Long, Node> map = new TreeMap<>();
    private ReentrantLock lock;

    private static HalfOpen instance;
    private static ReentrantLock instanceLock = new ReentrantLock();

    private int stateTransition = 0;

    @Override
    public State makeTransition() {
        return stateTransition == 1 ? Open.fetchInstance() : stateTransition == 2 ? Closed.fetchInstance() : this;
    }

    private void resetStates() {
        this.failedRequestCountAcrossSlingWindow = new AtomicInteger(0);
        this.map = new TreeMap<>();
        this.timeForLastConsideredRequest = -1;
        this.stateTransition = 0;
    }

    @Override
    public Result makeCallToApi(String url) {
        Result finalResult = null;
        Pair resultingPair = provideGenericApiBehaviour(url);
        try {
            lock.lock();
            if (this.timeForLastConsideredRequest == -1) {
                this.timeForLastConsideredRequest = System.currentTimeMillis();
                requestsAllowed.decrementAndGet();
                if (map.isEmpty()) {
                    if (!resultingPair.isResponse()) {
                        Node node = new Node(resultingPair.getResponseTimeStamp(), new AtomicInteger(1));
                        if (!map.containsKey(resultingPair.getResponseTimeStamp())) {
                            map.put(resultingPair.getResponseTimeStamp(), node);
                            failedRequestCountAcrossSlingWindow.incrementAndGet();
                        }
                    }
                }
            } else {
                long currentTime = System.currentTimeMillis();
                long difference = currentTime - timeForLastConsideredRequest;

                int tokensMaxCapacity = AllowedStatesConfigurations.HALF_OPEN_REQUESTS_ALLOWED;

                int addedTokens = Integer.valueOf(String.valueOf(Math.floor(difference/1000) * tokenRefillRate));
                requestsAllowed.addAndGet(Math.min(addedTokens, tokensMaxCapacity - requestsAllowed.get())); //Make sure here that tokens doesn't overflow
                this.timeForLastConsideredRequest = currentTime;

                if (requestsAllowed.get() == 0) {
                    finalResult = new Result(resultingPair, makeTransition()); //To handle situation where we have run out of allowed requests and will return HALF_OPEN as state of breaker
                } else {
                    requestsAllowed.decrementAndGet();
                    if (map.isEmpty()) {
                        if (!resultingPair.isResponse()) {
                            Node node = new Node(resultingPair.getResponseTimeStamp(), new AtomicInteger(1));
                            if (!map.containsKey(resultingPair.getResponseTimeStamp())) {
                                map.put(resultingPair.getResponseTimeStamp(), node);
                                failedRequestCountAcrossSlingWindow.incrementAndGet();
                            }
                        }
                    } else {
                        if (map.lastKey() - map.firstKey() < timeBasedSlidingWindow) {
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
                            finalResult = new Result(resultingPair, makeTransition()); //To handle situation where we have allowed requests but sliding window has not reached it's limit hence will return HALF_OPEN as state of breaker
                        } else {
                            double computedFailureRate = (this.failedRequestCountAcrossSlingWindow.get() / this.timeBasedSlidingWindow);

                            //No need for removal of elements from the map like we have done in Closed state implementation because in both the below mentioned condition we are making transition
                            if (computedFailureRate >= failureRateThreshold) {
                                stateTransition = 1;
                                State transitionedState = makeTransition();
                                resetStates();
                                finalResult = new Result(resultingPair, transitionedState); //To handle situation where we have allowed requests but as sliding window has reached along with failureRateThreshold breached it's limit hence will return OPEN as state of breaker
                            } else {
                                stateTransition = 2;
                                AtomicInteger failedRequestCount = map.get(map.firstKey()).failedRequestCount;
                                this.failedRequestCountAcrossSlingWindow.addAndGet(-failedRequestCount.get());
                                map.remove(map.firstKey());
                                State transitionedState = makeTransition();
                                resetStates();
                                finalResult = new Result(resultingPair, transitionedState); //To handle situation where we have allowed requests but as sliding window has reached along with failureRateThreshold not breached it's limit hence will return CLOSED as state of breaker
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return finalResult;
    }

    private HalfOpen(long timeBasedSlidingWindow, double failureRateThreshold, int requestsAllowed, double tokenRefillRate) {
        this.timeBasedSlidingWindow = timeBasedSlidingWindow;
        this.failureRateThreshold = failureRateThreshold;
        this.requestsAllowed = new AtomicInteger(requestsAllowed);
        this.tokenRefillRate = tokenRefillRate;
        this.timeForLastConsideredRequest = -1;
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
