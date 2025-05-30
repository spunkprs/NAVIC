package multithreading.basics.interupptedexception;


/**
This class presents the simulation of catching InterruptedException in the multithreaded environment.
Basically once the interrupted status of a thread is set, either by calling interrupt on itself or some other threads call the same on it,
interrupt status will be cleared only when this thread sleeps OR waiting on the lock/monitor of some object OR any of the overloaded join()
methods have been called upon it, status got cleared along with raising the InterruptedException

In this example specifically we would be presenting following scenarios :-
a.) Some other thread calls interrupt() on this thread i.e interrupts this thread when this thread is sleeping
b.) Thread itself calls interrupts on itself but nothing happens immediately rather IE will be raised only when the same thread starts waiting on
the lock/monitor of an object

Hence raising IE by waiting on the lock/monitor of some object && sleeping thread getting interrupted are covered, important thing to notice here is
interrupt method on this thread could have been called by itself or some other thread
*
* */

public class InterupptedExceptionSimulationOne {

        public static void main(String args[]) throws InterruptedException {
            InterruptExample.example();
        }

    static class InterruptExample {

            public static void example() throws InterruptedException {

            System.out.println("Main thread getting executed !!");

            final Thread secondaryThread = new Thread(new Runnable() {

                private Object lock = new Object();

                public void run() {
                    try {
                        System.out.println("Sleep operation triggered on secondaryThread");
                        Thread.sleep(1000 * 60 * 60);
                    } catch (InterruptedException e1) {
                        try {
                            e1.printStackTrace();
                            System.out.println("Exception got caught because secondaryThread while sleeping got interrupted by main thread");
                            System.out.println("The interrupt flag against secondaryThread is cleared : " + Thread.interrupted() + " " + Thread.currentThread().isInterrupted());
                            Thread.currentThread().interrupt();
                            synchronized (lock) {
                                /*
                                * No impact by the waiting time because interrupted status was already set to true hence as soon as wait on the lock/monitor object
                                * is called by the same thread immediately interrupted exception will be thrown
                                * */
                                lock.wait(10000);
                            }
                        } catch (InterruptedException e2) {
                            e2.printStackTrace();
                            System.out.println("Exception got caught because secondaryThread which was waiting on lock monitor got interrupted by itself");
                        }
                    }
                }
            });

            secondaryThread.start();

            System.out.println("About to wake up secondaryThread");
            secondaryThread.interrupt();
            System.out.println("Woke up secondaryThread");

            System.out.println("The interrupt flag against secondaryThread is cleared : " + secondaryThread.isInterrupted());

            secondaryThread.join();
        }
    }
}
