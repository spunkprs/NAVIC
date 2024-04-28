package multithreading.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
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
        System.out.println("Worker " + workerId + " in the process of performing pre waiting work !!");
        try {
            cyclicBarrier.await();
            System.out.println("Worker " + workerId + " continuing with post waiting work !!");
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
