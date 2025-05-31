package multithreading.problems.nonblockingdatastructure.stack.variant1;

import java.util.concurrent.*;

/**
This class aims at demonstrating the simulation of a non blocking stack i.e where during multiple push && multiple pop operations can happen
 simultaneously{another thread will be keep on trying to perform it's action in the loop instead of getting blocked}, have made use of
 AtomicReference instead of any intrinsic or explicit locking methodologies, here are the following features of the stack :-

 a.) Supports push operation in the multithreaded environment and guarantees no inconsistency
 b.) Supports pop operation in the multithreaded environment and guarantees no inconsistency
 c.) Non blocking class i.e AtomicReference is being used for both push && pop operations
 d.) Makes use of Atomic Scalar i.e AtomicInteger to maintain count of records in the stack
 e.) Have made use of CyclicBarrier too with the initial barrier count of 2, just to make sure both the threads will only proceed towards pop once push by
 both are done successfully

 Drawbacks :-
 a.) This stack supports both pop && push operation in the multithreaded environment && makes sure no two pop OR push operations will be happening
 simultaneously that later lead to inconsistencies && this behaviour is tested too but push && pop simultaneous operations via two threads are not tested
 which will be done in the future versions of this stack

 References --> https://www.educative.io/courses/java-multithreading-for-senior-engineering-interviews/nonblocking-stack
 Credits --> Educative

* */

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

        /**
        Added this sleep just to make sure pop operation on the stack via 2 different threads gets completed in this time
        * */

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Number of elements in the stack = " + stack.getSize());
    }
}
