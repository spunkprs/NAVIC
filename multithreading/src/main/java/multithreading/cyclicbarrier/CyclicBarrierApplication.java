package multithreading.cyclicbarrier;


import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
* Useful when x number of independent needs to be triggered independently {breaking down a large problem into multiple
* smaller problems that can be taken care of independently}, catch is all those dependent tasks can perform some pre-waiting
* work, post that they need to wait for each other && once all dependent tasks have accomplished that pre waiting work, barrier
* is tripped && post that all the dependent task can continue with their remaining work at their own pace
*
* Useful links -->
* a.) https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CyclicBarrier.html
* */
public class CyclicBarrierApplication {

    /*
    * If you look closely at the o/p generated then barrier action will be performed just before all the waiting
    * tasks are done with waiting && the barrier is tripped
    * */
    public static void main(String ar[]) {
        CyclicBarrier barrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("Barrier action is going to perform it's work before all the waiting threads " +
                        "get started with their work !!");
            }
        });

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            executorService.execute(new Worker(i + 1, barrier));
        }
        executorService.shutdown();
    }
}
