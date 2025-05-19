package multithreading.problems.ratelimiter;

/*
This class aims at solving rate limiter problem, detailing about the problem is below -->

Imagine you have a bucket that gets filled with tokens at the rate of 1 token per second.
The bucket can hold a maximum of N tokens. Implement a thread-safe class that lets threads get a token when one is available.
If no token is available, then the token-requesting threads should block

API exposed to the public is getToken(), but in my implementation I have made API produceTokens() public as well
for the sake of implementation

Only single producer thread would be there but consumer threads could be multiple, consumer would be short lived but I have made producer
to be never ending, could have made the producer thread to be daemon as well such that with the main thread it will be stopped but
that's a design choice

* */

public class RateLimiterResource {

    private int maxTokensAllowed;
    private int currentTokenCount;
    private Object lock;

    public RateLimiterResource(int maxTokens) {
        this.maxTokensAllowed = maxTokens;
        lock = new Object();
    }

    public String getToken() throws Exception {
        try {
            synchronized (lock) {
                if (currentTokenCount == 0) {
                    System.out.println("Token bucket is empty !!");
                    lock.wait();
                } else {
                    currentTokenCount--;
                    lock.notifyAll();
                }
            }
            return "Token Released";
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void produceTokens() {
        while (true) {
            synchronized (lock) {
                if (currentTokenCount == maxTokensAllowed) {
                    try {
                        System.out.println("Token bucket is full !!");
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else if (currentTokenCount < maxTokensAllowed) {
                    try {
                        Thread.sleep(1000);
                        currentTokenCount++;
                        System.out.println("Token is pushed into the bucket !!");
                        lock.notifyAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
