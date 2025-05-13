package multithreading.basics.semaphoreusage;

/*
This class presents an example of handling semaphore that's not getting released in one thread can cause unlimited waiting to acquire on the same semaphore for
other threads

Unlimited waiting for other threads is already presented/showcased in the example InCorrectSemaphoreExampleDemonstration

Credits --> Educative
* */

import java.util.concurrent.Semaphore;

public class CorrectSemaphoreExampleDemonstration {

    public static void main(String args[]) throws InterruptedException {
        CorrectSemaphoreExample.example();
    }

    static class CorrectSemaphoreExample {

        public static void example() throws InterruptedException {

            final Semaphore semaphore = new Semaphore(1);

            Thread badThread = new Thread(new Runnable() {

                public void run() {

                    while (true) {

                        try {
                            semaphore.acquire();
                            try {
                                throw new RuntimeException("");
                            } catch (Exception e) {
                                // handle any program logic exception and exit the function
                                return;
                            } finally {
                                //Follow the practice of releasing the semaphore in the event fo any exception
                                System.out.println("Bad thread releasing semahore.");
                                semaphore.release();
                            }

                        } catch (InterruptedException ie) {
                            // handle thread interruption
                        }
                    }
                }
            });

            badThread.start();

            // Wait for the bad thread to go belly-up
            Thread.sleep(1000);

            final Thread goodThread = new Thread(new Runnable() {

                public void run() {
                    System.out.println("Good thread patiently waiting to be signalled.");
                    try {
                        semaphore.acquire();
                    } catch (InterruptedException ie) {
                        // handle thread interruption
                    }
                }
            });

            goodThread.start();
            badThread.join();
            goodThread.join();
            System.out.println("Exiting Program");
        }
    }


}
