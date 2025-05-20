package multithreading.basics.countdownlatch;

import java.util.concurrent.CountDownLatch;

/*
This class aims at demonstrating the usage of CountDownLatch -->

It shall be used in the scenario where an action is dependent on several other N number of actions or same dependent action but that needs to be
executed N number of times then this tool becomes handy

Little briefing about CountDownLatch -->

A synchronization aid that allows one or more threads to wait until a set of operations being performed in other threads completes

A CountDownLatch is initialized with a given count. The await methods block until the current count reaches zero due to invocations of the countDown method,
after which all waiting threads are released and any subsequent invocations of await return immediately.
This is a one-shot phenomenon -- the count cannot be reset.
If you need a version that resets the count, consider using a CyclicBarrier


A CountDownLatch is a versatile synchronization tool and can be used for a number of purposes. A CountDownLatch initialized with a count of one serves as a
simple on/off latch, or gate: all threads invoking await wait at the gate until it is opened by a thread invoking countDown.
A CountDownLatch initialized to N can be used to make one thread wait until N threads have completed some action,
or some action has been completed N times.

A useful property of a CountDownLatch is that it doesn't require that threads calling countDown wait for the count to reach zero before proceeding,
it simply prevents any thread from proceeding past an await until all threads could pass.

Sample usage: Here is a pair of classes in which a group of worker threads use two countdown latches :-

a.) The first is a start signal that prevents any worker from proceeding until the driver is ready for them to proceed;
b.) The second is a completion signal that allows the driver to wait until all workers have completed.

 class Driver { // ...
   void main() throws InterruptedException {
     CountDownLatch startSignal = new CountDownLatch(1);
     CountDownLatch doneSignal = new CountDownLatch(N);

     for (int i = 0; i < N; ++i) // create and start threads
       new Thread(new Worker(startSignal, doneSignal)).start();

     doSomethingElse();            // don't let run yet
     startSignal.countDown();      // let all threads proceed
     doSomethingElse();
     doneSignal.await();           // wait for all to finish
   }
 }

 class Worker implements Runnable {
   private final CountDownLatch startSignal;
   private final CountDownLatch doneSignal;
   Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
     this.startSignal = startSignal;
     this.doneSignal = doneSignal;
   }
   public void run() {
     try {
       startSignal.await();
       doWork();
       doneSignal.countDown();
     } catch (InterruptedException ex) {} // return;
   }

   void doWork() { ... }
 }

* */

public class CountDownLatchUsageDemonstration {


    public static void main(String[] args) throws InterruptedException
    {
        //Created CountDownLatch for 2 threads
        CountDownLatch countDownLatch = new CountDownLatch(2);

        //Created and started two threads
        Worker A = new Worker(countDownLatch, "A");
        Worker B = new Worker(countDownLatch, "B");

        A.start();
        B.start();

        //When two threads(A and B)complete their tasks, they are returned (counter reached 0).
        countDownLatch.await();

        //Now execution of master thread has started
        Master D = new Master("Master");
        D.start();
    }

    static class Worker extends Thread {
        private CountDownLatch countDownLatch;

        public Worker(CountDownLatch countDownLatch, String name) {
            super(name);
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run()
        {
            System.out.println("Worker " +Thread.currentThread().getName()+" started");
            try
            {
                Thread.sleep(3000);
            }
            catch (InterruptedException ex)
            {
                ex.printStackTrace();
            }
            System.out.println("Worker  "+Thread.currentThread().getName()+" finished");

            //Each thread calls countDown() method on task completion.
            countDownLatch.countDown();
        }
    }

    static class Master extends Thread
    {
        public Master(String name)
        {
            super(name);
        }

        @Override
        public void run()
        {
            System.out.println( Thread.currentThread().getName() + " Executed");
            try
            {
                Thread.sleep(2000);
            }
            catch (InterruptedException ex)
            {
                ex.printStackTrace();
            }
        }
    }

}
