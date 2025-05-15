package multithreading.basics.atomics.optimisticlocking;

import java.util.concurrent.atomic.AtomicLong;

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
