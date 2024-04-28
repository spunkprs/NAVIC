package multithreading.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

public class Worker implements Runnable {

    private int workerId;
    private CyclicBarrier cyclicBarrier;

    public Worker(int workerId, CyclicBarrier cyclicBarrier) {
        this.workerId = workerId;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {

    }
}
