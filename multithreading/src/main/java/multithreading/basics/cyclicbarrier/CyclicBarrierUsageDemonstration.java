package multithreading.basics.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

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
