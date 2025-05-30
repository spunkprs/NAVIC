package multithreading.basics.exceptionpropagation;

/**
This class aims at showcasing demonstration of exception propagation which doesn't happen in the world of multithreading
*/

public class ExceptionPropagationDemonstration {

    public static void main(String ar[]) {

        System.out.println(Thread.currentThread() + " started it's processing !!");

        Thread threadOne = new Thread(new Task("thread1", false));
        Thread threadTwo = new Thread(new Task("thread2", true));

        try {
            threadOne.start();
        } catch (Exception e) {
            System.out.println("Exception propagated from threadOne execution to main thread !!");
            e.printStackTrace();
        }

        /*
        Important thing to notice -->
        Each individual threads runs in their own designated stack hence exception raised from one thread won't travel to another
        Though threadTwo saw an exception but that exception won't propagate to main thread hence below print statement will never come into play
        */

        try {
            threadTwo.start();
        } catch (Exception e) {
            System.out.println("Exception propagated from threadTwo execution to main thread !!");
            e.printStackTrace();
        }

        try {
            threadOne.join();
            threadTwo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class Task implements Runnable {

        private String threadName;
        private boolean raiseException;

        public Task(String threadName, boolean raiseException) {
            this.threadName = threadName;
            this.raiseException = raiseException;
        }

        public String getThreadName() {
            return threadName;
        }

        @Override
        public void run() {
            if (raiseException) {
                throw new RuntimeException(getThreadName() + " met an exception !!");
            } else {
                System.out.println(getThreadName() + " processed successfully !!");
            }
        }
    }
}
