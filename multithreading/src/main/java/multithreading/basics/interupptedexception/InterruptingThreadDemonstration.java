package multithreading.basics.interupptedexception;

/*
This class aims at showcasing the demonstration of main thread interrupting other running threads
* */

public class InterruptingThreadDemonstration {

        public static void main( String args[] ) throws InterruptedException {
            ToBeInterruptedThread toBeInterruptedThreadOne = new ToBeInterruptedThread("interruptedOne");
            ToBeInterruptedThread toBeInterruptedThreadTwo = new ToBeInterruptedThread("interruptedTwo");

            Thread innerThreadOne = new Thread(toBeInterruptedThreadOne);
            Thread innerThreadTwo = new Thread(toBeInterruptedThreadTwo);

            innerThreadOne.start();
            innerThreadTwo.start();

            // Interrupt innerThread after waiting for 5 seconds
            System.out.println("Main thread sleeping at " + +System.currentTimeMillis() / 1000);
            Thread.sleep(5000);

            innerThreadOne.interrupt();
            innerThreadTwo.interrupt();

            System.out.println("Main thread exiting at " + +System.currentTimeMillis() / 1000);
        }

        static class ToBeInterruptedThread implements Runnable {

            private String threadName;

            public ToBeInterruptedThread(String threadName) {
                this.threadName = threadName;
            }

            public String getThreadName() {
                return threadName;
            }

            public void run() {
                try {
                    System.out.println(getThreadName() + " goes to sleep at " + System.currentTimeMillis() / 1000);
                    Thread.sleep(1000 * 1000);
                } catch (InterruptedException ie) {
                    System.out.println(getThreadName() + " interrupted at " + System.currentTimeMillis() / 1000);
                }
            }
        }


}
