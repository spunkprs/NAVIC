package multithreading.basics.atomics.optimisticlocking;

import java.util.concurrent.atomic.AtomicLong;

/*
This class aims at demonstration of usage of atomic scalar here made use of AtomicLong, initialised a counter witn starting value of 10
&& it's value gets altered by 4 concurrent threads final value of the counter shall be 14 && transition from value 10 to 14 is being taken care
of by individual threads

All 4 threads can enter critical section at the same time && won't be blocked unlike in the case of pessimistic locking hence saving CPU cycles
but the commit to the shared variable will be done by only one i.e only single thread would be able to make commit at the same time in the contention
period thus promoting optimistic locking

Below code snippet is taken from AtomicInteger{from JDK 8} against the usage of public method getAndIncrement() -->

public final int getAndIncrement() {
        return unsafe.getAndAddInt(this, valueOffset, 1);
    }


public final int getAndAddInt(Object var1, long var2, int var4) {
        int var5;
        do {
            var5 = this.getIntVolatile(var1, var2);
        } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));

        return var5;
    }


Above code is self explanatory as stating the evidence of usage of optimistic locking enabled by the usage of method compareAndSwapInt() where it's
clearly pointed out that in case of multithreaded environment all the threads would be able to enter into critical section but the changes to the
shared variable will be committed successfully only via one of them for rest of them they will keep on trying until they get success response from
method compareAndSwapInt()


References -->
a.) https://www.youtube.com/watch?v=ufWVK7CHOAk&list=PLL8woMHwr36EDxjUoCzboZjedsnhLP1j4&index=19
b.) https://cephas.net/blog/2006/09/06/atomicinteger/
c.) https://medium.com/@kevinsheeranxyj/in-depth-analysis-of-java-atomicinteger-atomic-type-41fe9b840e83 {code taken from jdk 5}
* */

public class OptimisticLockingDemonstration {

    static class Counter {

        private AtomicLong atomicCounter;

        public long getCount() {
            return this.atomicCounter.get();
        }

        public Counter(AtomicLong atomicCounter) {
            this.atomicCounter = atomicCounter;
        }

        public void increment() {
            boolean incrementSuccessful = false;

            while (!incrementSuccessful) {
                long value = this.atomicCounter.get();
                long updatedValueToBe = value + 1;
                incrementSuccessful = this.atomicCounter.compareAndSet(value, updatedValueToBe);
            }
        }
    }

    public static void main(String ar[]) {
        Counter counter = new Counter(new AtomicLong(10));

        Thread t1 = new Thread(() -> {
           System.out.println("Counter value before update via thread 1 : " + counter.getCount());
           counter.increment();
            System.out.println("Counter value after update via thread 1 : " + counter.getCount());
        });

        Thread t2 = new Thread(() -> {
            System.out.println("Counter value before update via thread 2 : " + counter.getCount());
            counter.increment();
            System.out.println("Counter value after update via thread 2 : " + counter.getCount());
        });

        Thread t3 = new Thread(() -> {
            System.out.println("Counter value before update via thread 3 : " + counter.getCount());
            counter.increment();
            System.out.println("Counter value after update via thread 3 : " + counter.getCount());
        });

        Thread t4 = new Thread(() -> {
            System.out.println("Counter value before update via thread 4 : " + counter.getCount());
            counter.increment();
            System.out.println("Counter value after update via thread 4 : " + counter.getCount());
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
