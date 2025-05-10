package multithreading.basics.interupptedexception;

public class InterupptedExceptionSimulationOne {

        public static void main(String args[]) throws InterruptedException {
            InterruptExample.example();
        }


    static class InterruptExample {

            public static void example() throws InterruptedException {

            System.out.println("Main thread getting executed !!");

            final Thread sleepyThread = new Thread(new Runnable() {

                private Object lock = new Object();

                public void run() {
                    try {
                        System.out.println("Sleep operation triggered on sleepy thread");
                        Thread.sleep(1000 * 60 * 60);
                    } catch (InterruptedException e1) {
                        try {
                            e1.printStackTrace();
                            System.out.println("Exception got caught because sleepy thread while sleeping got interrupted by main thread");
                            System.out.println("The interrupt flag is cleared : " + Thread.interrupted() + " " + Thread.currentThread().isInterrupted());
                            //Thread.currentThread().interrupt();
                            System.out.println("sleepy thread is awakened");
                            //System.out.println("The interrupt flag is set now : " + Thread.currentThread().isInterrupted() + " " + Thread.interrupted());
                            synchronized (lock) {
                                lock.wait();
                            }
                        } catch (InterruptedException e2) {
                            e2.printStackTrace();
                            System.out.println("Exception got caught because sleepy thread which was waiting on lock monitor got interrupted by main thread");
                        }

                    }
                }
            });

            sleepyThread.start();

            System.out.println("About to wake up the sleepy thread ...");
            sleepyThread.interrupt();
            System.out.println("Woke up sleepy thread ...");

            Thread.sleep(60 * 6);

            sleepyThread.interrupt();

            sleepyThread.join();
        }
    }


}
