package multithreading.problems.circuitbreaker;

import java.util.concurrent.locks.ReentrantLock;

public class Open extends State {

    private long transitionTimeout;
    private long lastRequestReceivedTime;

    private ReentrantLock lock;
    private static Open instance;
    private static ReentrantLock instanceLock = new ReentrantLock();

    public static Open fetchInstance() {
        if (instance != null) {
            return instance;
        } else {
            try {
                instanceLock.lock();
                if (instance == null) {
                    instance = new Open(AllowedStatesConfigurations.OPEN_STATE_TRANSITION_TIMEOUT);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                instanceLock.unlock();
            }
        }
        return instance;
    }

    private void resetStates() {
        this.lastRequestReceivedTime = -1;
    }

    private Open(long transitionTimeout) {
        this.transitionTimeout = transitionTimeout;
        this.lastRequestReceivedTime = -1;
        this.lock = new ReentrantLock();
    }

    @Override
    public State makeTransition() {
        return HalfOpen.fetchInstance();
    }

    @Override
    public Result makeCallToApi(String url) {
        Result finalResult = null;
        try {
            lock.lock();
            long requestReceivedTimeStamp = System.currentTimeMillis(); // No call to external API shall be made at this point
            if (lastRequestReceivedTime == -1) {
                lastRequestReceivedTime = requestReceivedTimeStamp;
            } else {
                if (requestReceivedTimeStamp - this.lastRequestReceivedTime < this.transitionTimeout) {
                    finalResult = new Result(null, this);
                } else {
                    State transitionedState = makeTransition();
                    resetStates();
                    finalResult = new Result(null, transitionedState);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           lock.unlock();
        }
        return finalResult;
    }
}
