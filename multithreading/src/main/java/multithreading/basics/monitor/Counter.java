package multithreading.basics.monitor;

/*
Concept behind coming up with this class is to showcase Java monitor pattern i.e how implicit locks/monitor
are being used to guard the state of an object using it's own monitor in the multithreaded environment
*
* */

public class Counter {

    private long value;

    public synchronized long getValue() {
        return this.value;
    }

    public synchronized long incrementValue() {
        if (this.value == Long.MAX_VALUE) {
            throw new IllegalArgumentException("Counter Overflow");
        }
        this.value = this.value + 1;
        return this.value;
    }
}
