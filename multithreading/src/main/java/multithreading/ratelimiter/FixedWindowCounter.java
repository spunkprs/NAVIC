package multithreading.ratelimiter;

import java.util.concurrent.atomic.AtomicLong;

/**
This is the implementation for FixedWindowCounter algorithm that can be used against RateLimiting

It shall have following things as parameters :-
 1.) numberOfRequests
 2.) timeWindowLength


 This approach will work perfectly but downside with it is the heavy usage of pessimistic locking whenever new request arrives
 it needs to take lock till then all other requests will be waiting hence it needs to be improved !!

 Reference material : https://medium.com/@devenchan/implementing-rate-limiting-in-java-from-scratch-fixed-window-and-sliding-window-implementation-a6e8d6407d17
 * */

public class FixedWindowCounter implements RateLimiter {

    private long numberOfRequests;
    private long timeWindowLength;
    private volatile long rateLimiterInitialisationTime;

    private Object lock;
    private AtomicLong counter;

    private long maxTimeStamp;

    public FixedWindowCounter(long numberOfRequests, long timeWindowLength) {
        this.numberOfRequests = numberOfRequests;
        this.timeWindowLength = timeWindowLength;
        this.counter = new AtomicLong(0);
        this.rateLimiterInitialisationTime = System.currentTimeMillis();
        maxTimeStamp = rateLimiterInitialisationTime;
        this.lock = new Object();
    }

    @Override
    public boolean tryAcquire() {
        long currentSystemTime = System.currentTimeMillis();

        synchronized (lock) {
            if (currentSystemTime - this.rateLimiterInitialisationTime < this.timeWindowLength) {
                return counter.incrementAndGet() < this.numberOfRequests;
            } else {
                    if (counter.get() != 0) {
                        counter.set(0);
                    }
                    if (currentSystemTime >= maxTimeStamp) {
                        maxTimeStamp = currentSystemTime;
                    }
                rateLimiterInitialisationTime = maxTimeStamp;
                return counter.incrementAndGet() < this.numberOfRequests;
            }
        }
    }
}
