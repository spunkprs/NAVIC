package multithreading.basics.countdownlatch;

import java.util.concurrent.CountDownLatch;

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
