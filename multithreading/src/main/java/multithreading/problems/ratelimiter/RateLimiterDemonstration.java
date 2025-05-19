package multithreading.problems.ratelimiter;

/*
Driver class for the simulation of RateLimiter problem -->

References --> https://www.educative.io/courses/java-multithreading-for-senior-engineering-interviews/rate-limiting-using-token-bucket-filter
Credits --> Educative
* */

public class RateLimiterDemonstration {

    public static void main(String ar[]) {

        RateLimiterResource rateLimiterResource = new RateLimiterResource(6);

        Thread threads[] = new Thread[5];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new TokenConsumer("thread " + i, rateLimiterResource));
        }

        Thread tokenProducerThread = new Thread(() -> {
           rateLimiterResource.produceTokens();
        });

        tokenProducerThread.start();

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
    }

}
