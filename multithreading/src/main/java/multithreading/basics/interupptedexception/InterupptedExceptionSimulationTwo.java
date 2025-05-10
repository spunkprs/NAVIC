package multithreading.basics.interupptedexception;

/*
This class presents the simulation of catching InterruptedException in the multithreaded environment.
Basically once the interrupted status of a thread is set, either by calling interrupt on itself or some other threads call the same on it,
interrupt status will be cleared only when this thread sleeps OR waiting on the lock/monitor of some object OR any of the overloaded join()
methods have been called upon it along with raising the InterruptedException

In this example specifically we would be presenting scenario when oen of the thread is sleeping and following two scenarios occurs :-
a.) Some other thread calls interrupt() on this thread i.e interrupts this thread
b.) Thread itself calls interrupts on itself but nothing happens immediately rather IE will be raised only when the same thread calls sleep on itself
*
* */

public class InterupptedExceptionSimulationTwo {

    public static void main(String args[]) throws InterruptedException {
        InterupptedExceptionSimulationTwo.InterruptExample.example();
    }

    static class InterruptExample {

        public static void example() throws InterruptedException {

            System.out.println("Main thread getting executed !!");

            final Thread sleepyThread = new Thread(new Runnable() {

                public void run() {
                    try {
                        System.out.println("Sleep operation triggered on sleepy thread");
                        Thread.sleep(1000 * 60 * 60);
                    } catch (InterruptedException e1) {
                            e1.printStackTrace();
                            System.out.println("Exception got caught because sleepy thread while sleeping got interrupted by main thread");
                            System.out.println("The interrupt flag is cleared : " + Thread.currentThread().isInterrupted() + " " + Thread.interrupted());
                            Thread.currentThread().interrupt();
                        try {
                            Thread.sleep(60 * 6);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            System.out.println("Exception got caught because sleepy thread while sleeping got interrupted by sleepy thread itself");
                        }
                    }
                }
            });

            sleepyThread.start();

            System.out.println("About to wake up the sleepy thread ...");
            sleepyThread.interrupt();
            System.out.println("Woke up sleepy thread ...");
            sleepyThread.join();
        }
    }
}
