package multithreading.basics.atomics;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class VolatileNotEquivalentToAtomics {

        static volatile boolean won = false;

        public static void main( String args[] ) throws Exception {
            ExecutorService es = Executors.newFixedThreadPool(25);
            try {
                int numThreads = 15;
                Runnable[] racers = new Runnable[numThreads];
                Future[] futures = new Future[numThreads];

                // create thread tasks
                for (int i = 0; i < numThreads; i++) {
                    racers[i] = new Runnable() {
                        @Override
                        public void run() {
                            race();
                        }
                    };
                }

                // submit threads for execution
                for (int i = 0; i < numThreads; i++) {
                    futures[i] = es.submit(racers[i]);
                }

                // wait for threads to finish
                for (int i = 0; i < numThreads; i++) {
                    futures[i].get();
                }
            } finally {
                es.shutdown();
            }
        }

        static void race() {
            if (!won) {
                won = true;
                System.out.println(Thread.currentThread().getName() + " won the race.");
            } else {
                System.out.println(Thread.currentThread().getName() + " lost.");
            }
        }
}
