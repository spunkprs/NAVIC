package multithreading.ratelimiter;

import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicLong;

/**
This is the implementation for FixedWindowCounter algorithm that can be used against RateLimiting

It shall have following things as parameters :-
 1.) numberOfRequests
 2.) timeWindowLength


 * */

public class FixedWindowCounter implements RateLimiter {

    private long numberOfRequests;
    private long timeWindowLength;
    private volatile long rateLimiterInitialisationTime;


    private Object lock;
    private AtomicLong counter;

    private PriorityQueue<Long> timeStamp = new PriorityQueue<>();

    public FixedWindowCounter(long numberOfRequests, long timeWindowLength) {
        this.numberOfRequests = numberOfRequests;
        this.timeWindowLength = timeWindowLength;
        this.counter = new AtomicLong(0);
        this.rateLimiterInitialisationTime = System.currentTimeMillis();
        timeStamp.add(this.rateLimiterInitialisationTime);
        this.lock = new Object();
    }

    @Override
    public boolean tryAcquire() {
        long currentSystemTime = System.currentTimeMillis();

        synchronized (lock) {
            if (currentSystemTime - this.rateLimiterInitialisationTime < this.timeWindowLength) {
                return counter.incrementAndGet() <= this.numberOfRequests;
            } else {
                    if (counter.get() != 0) {
                        counter.set(0);
                    }
                    if (currentSystemTime >= this.timeStamp.peek()) {
                        this.timeStamp.poll();
                        this.timeStamp.add(currentSystemTime);
                    }
                rateLimiterInitialisationTime = this.timeStamp.peek();
                return counter.incrementAndGet() <= this.numberOfRequests;
            }
        }
    }
}
