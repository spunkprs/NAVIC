package multithreading.basics.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
This class aims at demonstrating the usage of CyclicBarrier -->

Scenarios where it can be used -->
Can be used in the scenario where individual tasks are dependent on each other i.e they all wait for one another till they reach a common barrier point

Perfect example could be a bigger problem that's already split into multiple sub problems, all sub problems will continue their work independently
but they will wait for each other completion before the merge process actually starts

Little briefing about CyclicBarrier -->

A synchronization aid that allows a set of threads to all wait for each other to reach a common barrier point.
CyclicBarriers are useful in programs involving a fixed sized party of threads that must occasionally wait for each other.
The barrier is called cyclic because it can be re-used after the waiting threads are released.

A CyclicBarrier supports an optional Runnable command that is run once per barrier point, after the last thread in the party arrives,
but before any threads are released.
This barrier action is useful for updating shared-state before any of the parties continue.

Important point to be noticed -->
The CyclicBarrier uses an all-or-none breakage model for failed synchronization attempts: If a thread leaves a barrier point prematurely
because of interruption, failure, or timeout, all other threads waiting at that barrier point will also leave abnormally
via BrokenBarrierException (or InterruptedException if they too were interrupted at about the same time)

Sample usage: Here is an example of using a barrier in a parallel decomposition design:

 class Solver {
   final int N;
   final float[][] data;
   final CyclicBarrier barrier;

   class Worker implements Runnable {
     int myRow;
     Worker(int row) { myRow = row; }
     public void run() {
       while (!done()) {
         processRow(myRow);

         try {
           barrier.await();
         } catch (InterruptedException ex) {
           return;
         } catch (BrokenBarrierException ex) {
           return;
         }
       }
     }
   }

   public Solver(float[][] matrix) {
     data = matrix;
     N = matrix.length;
     Runnable barrierAction =
       new Runnable() { public void run() { mergeRows(...); }};
     barrier = new CyclicBarrier(N, barrierAction);

     List<Thread> threads = new ArrayList<Thread>(N);
     for (int i = 0; i < N; i++) {
       Thread thread = new Thread(new Worker(i));
       threads.add(thread);
       thread.start();
     }

     // wait until done
     for (Thread thread : threads)
       thread.join();
   }
 }
Here, each worker thread processes a row of the matrix then waits at the barrier until all rows have been processed.
When all rows are processed the supplied Runnable barrier action is executed and merges the rows. If the merger determines
that a solution has been found then done() will return true and each worker will terminate.

Reference -->
a.) https://www.educative.io/courses/java-multithreading-for-senior-engineering-interviews/cyclicbarrier
Credits --> Educative

* */

public class CyclicBarrierUsageDemonstration {

        public static void main (String args[]) {

            //Creating CyclicBarrier with 3 parties i.e. 3 Threads needs to call await()
            final CyclicBarrier cb = new CyclicBarrier(3, new Runnable(){

                //Action that executes after the last thread arrives
                @Override
                public void run(){
                    //This task will be executed once all threads reaches barrier
                    System.out.println("All parties have arrived at the barrier, lets continue execution");
                }
            });

            //starting each thread
            Thread t1 = new Thread(new Task(cb), "Thread 1");
            Thread t2 = new Thread(new Task(cb), "Thread 2");
            Thread t3 = new Thread(new Task(cb), "Thread 3");

            t1.start();
            t2.start();
            t3.start();
        }


    static class Task implements Runnable {

        private CyclicBarrier barrier;

        public Task(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        //Await is invoked to wait for other threads
        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " is waiting on barrier");
                barrier.await();
                //printing after crossing the barrier
                System.out.println(Thread.currentThread().getName() + " has crossed the barrier");
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } catch (BrokenBarrierException ex) {
                ex.printStackTrace();
            }
        }

    }
}
