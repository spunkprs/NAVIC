package multithreading.problems.nonblockingdatastructure.stack.variant1;

import java.util.concurrent.*;

public class NonBlockingStackDemonstration {

    public static void main(String ar[]) {

        NonBlockingStack<Integer> stack = new NonBlockingStack<>();
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        int numThreads = 2;

        /**
         Barrier is making sure that once push is being done into NonBlockingStack by both the threads,
         then only pop will start happening upon NonBlockingStack simultaneously
         NO simultaneous push && pop are happening as part of this example
         */
        CyclicBarrier barrier = new CyclicBarrier(numThreads);

        Integer testValue = new Integer(51);

        try {
            for (int i = 0; i < numThreads; i++) {
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 10000; i++) {
                            stack.push(testValue);
                        }

                        try {
                            barrier.await();
                        } catch (InterruptedException | BrokenBarrierException ex) {
                            System.out.println("ignoring exception");
                            //ignore both exceptions
                        }

                        for (int i = 0; i < 10000; i++) {
                            stack.pop();
                        }
                    }
                });
            }
        } finally {
            executorService.shutdown();
            try {
                executorService.awaitTermination(1, TimeUnit.HOURS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Number of elements in the stack = " + stack.getSize());
    }
}
