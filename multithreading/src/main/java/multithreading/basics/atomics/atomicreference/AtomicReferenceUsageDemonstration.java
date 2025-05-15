package multithreading.basics.atomics.atomicreference;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceUsageDemonstration {

    static AtomicReference<Thread> atomicReference = new AtomicReference<>();

        public static void main( String args[] ) throws Exception {

            ExecutorService executor = Executors.newFixedThreadPool(50);
            try {
                for (int i = 0; i < 25; i++) {
                    executor.submit(new Runnable() {
                        @Override
                        public void run() {

                            if (atomicReference.get() == null) {
                                if (atomicReference.compareAndSet(null, Thread.currentThread())) {
                                    System.out.println(Thread.currentThread().getName() + " takes first turn");
                                }
                            }
                        }
                    });
                }
            } finally {
                executor.shutdown();
                executor.awaitTermination(1, TimeUnit.HOURS);
            }
        }
}
