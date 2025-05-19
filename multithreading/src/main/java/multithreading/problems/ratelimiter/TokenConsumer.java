package multithreading.problems.ratelimiter;

/*
Consumer thread will be pulling tokens from the bucket
*/

public class TokenConsumer implements Runnable {

    private String threadName;
    private RateLimiterResource rateLimiterResource;

    public TokenConsumer(String threadName, RateLimiterResource rateLimiterResource) {
        this.threadName = threadName;
        this.rateLimiterResource = rateLimiterResource;
    }

    @Override
    public void run() {
        try {
            System.out.println(rateLimiterResource.getToken() + " from thread number " + this.threadName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
