package multithreading.latch;

import java.util.concurrent.CountDownLatch;

public class Worker implements Runnable {

    private int workerId;
    private CountDownLatch latch;

    public Worker(int workerId, CountDownLatch latch) {
        this.workerId = workerId;
        this.latch = latch;
    }

    @Override
    public void run() {

    }
}
