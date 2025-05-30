package multithreading.basics.atomics;

import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

/**
In the case of a single thread, i.e. zero contention environment, an operation that relies on CAS (e.g. updating an AtomicInteger)
will be faster than an operation that involves locking first. On single CPU machines, CAS operations almost always succeed other
than in the very rare case of a thread being interrupted in the middle of a read-modify-write operation. Even with moderate contention,
CAS operations are faster as they avoid thread suspension and resumption, which locking solutions must deal with.

In benchmark tests, it has been observed that atomics perform better than locks under low to moderate contention, which is representative of real-life programs.
However, in a highly contended environment, the majority of threads will waste CPU cycles retrying CAS operations but using locks in the same situation would
have the threads suspended and then resumed later. Granted, thread suspension/resumption incurs overhead but at some point the CAS-retry expense dwarfs the overhead
of suspending and resuming threads. In reality such high contention is unlikely and atomics are always preferable over lock-based solutions.

Important thing to notice --> Results of this exercise is not matching with the theory mentioned above hence we are getting better performance with
java monitor/intrinsic locks rather than atomic scalar i.e AtomicInteger

Credits --> Educative

* */

public class PerformanceOfAtomicAgainstLockDemonstration {

        static AtomicInteger counter = new AtomicInteger(0);
        static int simpleCounter = 0;

        public static void main( String args[] ) throws Exception {
            test(true);
            test(false);
        }

        synchronized static void incrementSimpleCounter() {
            simpleCounter++;
        }

        static void test(boolean isAtomic) throws Exception {
            ExecutorService executorService = Executors.newFixedThreadPool(10);
            long start = System.currentTimeMillis();

            try {
                for (int i = 0; i < 10; i++) {
                    executorService.submit(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 0; i < 1000000; i++) {

                                if (isAtomic) {
                                    counter.incrementAndGet();
                                } else {
                                    incrementSimpleCounter();
                                }
                            }
                        }
                    });
                }
            } finally {
                executorService.shutdown();
                executorService.awaitTermination(1, TimeUnit.HOURS);
            }

            long timeTaken = System.currentTimeMillis() - start;
            System.out.println("Time taken by " + (isAtomic ? "atomic integer counter " : "integer counter ") + timeTaken + " milliseconds.");
        }

}
