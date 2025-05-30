package multithreading.basics.daemonthread;

/**
This class aims to demonstrate usage of daemon thread

Brief detailing about daemon threads -->
A daemon thread runs in the background but as soon as the main application thread exits, all daemon threads are killed by the JVM

Note that in case a spawned thread isn’t marked as a daemon then even if the main thread finishes execution,
JVM will wait for the spawned thread to finish before tearing down the process

References -->
a.) https://www.educative.io/courses/java-multithreading-for-senior-engineering-interviews/basic-thread-handling
Credits --> Educative
* */

public class DaemonThreadDemonstration {

        public static void main( String args[] ) throws InterruptedException {

            PawnThread executeMe = new PawnThread();
            Thread innerThread = new Thread(executeMe);
            innerThread.setDaemon(true);
            innerThread.start();

            /*
            If we uncomment this line main thread will be waiting for the inner thread till it's completion, without calling join() method
            programme will be terminated by JVM as soon as main thread completes it's execution irrespective of the fact that other thread is
            still running in the background{because daemon thread is of least priority}

            However inner thread being non daemon thread, JVM will not shut the application post main thread completion too
            * */
            //innerThread.join();
        }


    static class PawnThread implements Runnable {

        public void run() {
            while (true) {
                System.out.println("Say Hello over and over again !!");
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ie) {
                    // swallow interrupted exception
                }
            }
        }
    }
}
