package multithreading.latch;

import java.util.concurrent.CountDownLatch;

public class Worker implements Runnable {

    private int workerId;
    private CountDownLatch latchOne;
    private CountDownLatch latchTwo;

    public Worker(int workerId, CountDownLatch latchOne, CountDownLatch latchTwo) {
        this.workerId = workerId;
        this.latchOne = latchOne;
        this.latchTwo = latchTwo;
    }

    @Override
    public void run() {
        try {
            latchOne.await();
            System.out.println("Worker " + workerId + " proceeding with work !!");
            doWork();
            latchTwo.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doWork() {
        System.out.println("Worker " + workerId + " is doing it's work !!");
    }
}
