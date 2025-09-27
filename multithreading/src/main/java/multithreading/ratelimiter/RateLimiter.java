package multithreading.ratelimiter;

public interface RateLimiter {

    boolean tryAcquire();
}
