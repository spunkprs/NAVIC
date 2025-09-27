package multithreading.ratelimiter;

/**
Implementation of token bucket algorithm algorithm that can be used against RateLimiting

 It shall have following things as parameters :-
 1.) refreshRate
 2.) maxTokenSize
 3.) tokenCountAddition
 4.) Time Unit


 This approach will work just fine but downside with it is the heavy usage of pessimistic locking which needs to be improved !!
 * */

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class TokenBucket implements RateLimiter {

    private Semaphore tokenSempahore;

    private int maxTokenSize;
    private long refreshRate;
    private int tokenCountAddition;
    private TimeUnit timeUnit;
    private Object lock;

    public TokenBucket(int maxTokenSize, long refreshRate, int tokenCountAddition, TimeUnit timeUnit) {
        this.maxTokenSize = maxTokenSize;
        this.refreshRate = refreshRate;
        this.tokenCountAddition = tokenCountAddition;
        tokenSempahore = new Semaphore(maxTokenSize);
        this.timeUnit = timeUnit;
        lock = new Object();
    }

    @Override
    public boolean tryAcquire() {
        try {
            int availablePermits = 0;
            synchronized (lock) {
                availablePermits  = tokenSempahore.availablePermits();
                if (availablePermits > 0) {
                    return tokenSempahore.tryAcquire(1, this.refreshRate, timeUnit);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

     class TokenGeneration implements Runnable {

        @Override
        public void run() {
            boolean flag = true;
            while (flag) {
                try {
                    Thread.sleep(refreshRate);
                    int availablePermits = 0;
                    synchronized (lock) {
                        availablePermits = tokenSempahore.availablePermits();
                        if (availablePermits + tokenCountAddition <= maxTokenSize) {
                            tokenSempahore.release(tokenCountAddition);
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Exception raised while generating tokens in the bucket !!");
                }
            }
        }
    }
}
