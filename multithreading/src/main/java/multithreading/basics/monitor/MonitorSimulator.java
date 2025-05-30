package multithreading.basics.monitor;

/**
This class is responsible for the simulation of the usage of Java monitor pattern in the multithreaded environment
O/p of the statements from threads t1, t2 && t3 is unpredictable but it's pretty sure that the max value reached against
counter will be 3, where as minimum being zero
*
* */

public class MonitorSimulator {

    public static void main(String ar[]) {

        //Object has been initialised
        Counter counter = new Counter();

        Thread t1 = new Thread(() -> {
            System.out.println("Printing state of the counter inside thread 1 before increment :: " + counter.getValue());
            System.out.println("Printing state of the counter inside thread 1 post increment :: " + counter.incrementValue());
        });

        Thread t2 = new Thread(() -> {
            System.out.println("Printing state of the counter inside thread 2 before increment :: " + counter.getValue());
            System.out.println("Printing state of the counter inside thread 2 post increment :: " + counter.incrementValue());
        });

        Thread t3 = new Thread(() -> {
            System.out.println("Printing state of the counter inside thread 3 before increment :: " + counter.getValue());
            System.out.println("Printing state of the counter inside thread 3 post increment :: " + counter.incrementValue());
        });

        //Starting all the threads
        t1.start();
        t2.start();
        t3.start();
    }
}
