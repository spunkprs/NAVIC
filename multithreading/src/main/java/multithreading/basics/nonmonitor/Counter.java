package multithreading.basics.nonmonitor;


/*
Concept behind coming up with this class is to showcase the achievement of both atomicity && visibility by not making use of
traditional Java monitor pattern in the multithreaded environment
As state of the object needs to be altered considering no interim stale data, here we making use of lock that acts as
monitor/intrinsic lock
*
* */

public class Counter {

    private long value;
    private final Object lock = new Object();

    public long getValue() {
        synchronized (lock) {
            return this.value;
        }
    }

    public long incrementValue() {
        synchronized (lock) {
            if (this.value == Long.MAX_VALUE) {
                throw new IllegalArgumentException("Counter Overflow");
            }
            this.value = this.value + 1;
            return this.value;
        }
    }
}
