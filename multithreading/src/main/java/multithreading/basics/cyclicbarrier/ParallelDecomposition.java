package multithreading.basics.cyclicbarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
This class aims at solving a problem where a 2D array is provided && we need to parse each row individually, need to perform an action when
all the running individual threads reach a common barrier point
Post that merger process will be keep on waiting for the results of the individual threads to arrive then only it can proceed towards the merge process

Making use of following tools to solve the problem -->
1.) CyclicBarrier --> To make sure all the individual running threads wait for each other till they a reach a common barrier point, once the last exits the
barrier BarrierAction thread will be triggered{before the individual threads are released}
2.) CountDownLatch --> Merger thread whose responsibility is to combine results from individual threads will wait till results
from each involved thread is not available

References -->
a.) https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CyclicBarrier.html
b.) https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CountDownLatch.html
* */

public class ParallelDecomposition {

    public static void main(String ar[]) {
        int arr[][] = new int[3][3];

        CyclicBarrier barrier = new CyclicBarrier(arr.length, new BarrierAction("Barrier Action"));
        CountDownLatch latch = new CountDownLatch(arr.length);

        Thread mergerThread = new Thread(new MergerTask(latch, "Merger Thread"));
        mergerThread.start();

        for (int i = 0; i < arr.length; i++) {
            long threadSleepTime = new Random().nextInt(10000);
            Thread t = new Thread(new Task("Thread-" + i, threadSleepTime, i, barrier, latch));
            t.start();
        }
    }


    static class Task implements Runnable {

        private String threadName;
        private long threadSleepTime;
        private int rowNum;
        private CyclicBarrier barrier;
        private CountDownLatch latch;

        public Task(String name, long threadSleepTime, int rowNum, CyclicBarrier barrier, CountDownLatch latch) {
            this.threadName = name;
            this.threadSleepTime = threadSleepTime;
            this.rowNum = rowNum;
            this.barrier = barrier;
            this.latch = latch;
        }

        public String getThreadName() {
            return threadName;
        }

        public int getRowNum() {
            return rowNum;
        }

        @Override
        public void run() {
            System.out.println(getThreadName() + " is ready to process row num " + getRowNum());
            try {
                System.out.println(getThreadName() + " has started with processing of row num " + getRowNum());
                Thread.sleep(threadSleepTime);

                //Step to mark individual threads have done their dependent part && reached a common barrier point
                barrier.await();

                /*
                Step to mark that this individual process is complete such that merger process can proceed towards
                merging results generated from individual threads
                * */
                System.out.println(getThreadName() + " is done with processing of row num " + getRowNum());
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

        }
    }

    /*
    A CyclicBarrier supports an optional Runnable command that is run once per barrier point, after the last thread in the party arrives,
    but before any threads are released.
    This barrier action is useful for updating shared-state before any of the parties continue
    */

    static class BarrierAction implements Runnable {

        private String threadName;

        public BarrierAction(String threadName) {
            this.threadName = threadName;
        }

        public String getThreadName() {
            return threadName;
        }

        @Override
        public void run() {
          System.out.println(getThreadName() + " - All the individual threads have reached barrier point successfully !!");
        }
    }


    static class MergerTask implements Runnable {

        private CountDownLatch latch;
        private String threadName;

        public MergerTask(CountDownLatch latch, String threadName) {
            this.latch = latch;
            this.threadName = threadName;
        }

        public String getThreadName() {
            return threadName;
        }

        @Override
        public void run() {
            System.out.println( getThreadName() + " is waiting for all the individual threads to complete !!");
            try {
                latch.await();
                System.out.println(getThreadName() + " proceeding with merging individual threads results as the results are available now !!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
