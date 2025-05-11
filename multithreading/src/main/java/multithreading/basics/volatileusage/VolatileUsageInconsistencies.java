package multithreading.basics.volatileusage;


/*
*
* With this example we proved that making a shared variable volatile alone doesn't guarantee changes to that variable are consistent,
* specifically in the context where shared variable is accessed by multiple writer threads
*
* Here we are spanning 10 threads and each thread is incrementing value of counter by 1000 hence final result shall be 10000
* but the response in not idempotent hence result is not consistent
*
* */

public class VolatileUsageInconsistencies {

        // volatile doesn't imply thread-safety!
        static volatile int count = 0;

        public static void main(String[] args) throws InterruptedException {

            int numThreads = 10;
            Thread[] threads = new Thread[numThreads];

            for (int i = 0; i < numThreads; i++) {
                threads[i] = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 1000; i++)
                            count++;
                    }
                });
            }

            for (int i = 0; i < numThreads; i++) {
                threads[i].start();
            }

            for (int i = 0; i < numThreads; i++) {
                threads[i].join();
            }

            System.out.println("count = " + count);
        }


}
