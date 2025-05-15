package multithreading.basics.atomics;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
Here we instantiate several threads and have them race to change a shared boolean variable’s won value to true.
The first one to do so is considered to have won the race

If we run the above program enough times especially on a machine with multiple processes we will observe multiple threads printing the winning statement.
The widget above may not show the faulty run since the VM executing the code may have a single vCPU. The volatile variable doesn’t prevent multiple threads
from accessing and then updating the variable won.
A possible sequence resulting in multiple threads declaring themselves as winners can be:-

a.) Thread A reads the value of won which is false.
b.) Thread B reads the value of won which is false.
c.) Thread A changes the value of won to true and the variable is updated in main memory for all threads to see.
d.) Thread B doesn’t know Thread A had read the value of the variable won before Thread B accessed it.
Thread B too updates the value of won to true
* */

public class VolatileNotEquivalentToAtomics {

        static volatile boolean won = false;

        public static void main( String args[] ) throws Exception {
            ExecutorService es = Executors.newFixedThreadPool(25);
            try {
                int numThreads = 15;
                Runnable[] racers = new Runnable[numThreads];
                Future[] futures = new Future[numThreads];

                // create thread tasks
                for (int i = 0; i < numThreads; i++) {
                    racers[i] = new Runnable() {
                        @Override
                        public void run() {
                            race();
                        }
                    };
                }

                // submit threads for execution
                for (int i = 0; i < numThreads; i++) {
                    futures[i] = es.submit(racers[i]);
                }

                // wait for threads to finish
                for (int i = 0; i < numThreads; i++) {
                    futures[i].get();
                }
            } finally {
                es.shutdown();
            }
        }

        /*
        Variable won being volatile reading from processor cache && register is not allowed hence all the threads{running on same or different processors}
        will be reading from writing to main memory but atomicity of operation is not guaranteed.
        We can clearly see here multiple threads can enter into critical section simultaneously && alteration to won's variable value by one thread is
        not being communicated to another hence possibilities of multiple threads claiming to win the race would be there
        * */

        static void race() {
            if (!won) {
                won = true;
                System.out.println(Thread.currentThread().getName() + " won the race.");
            } else {
                System.out.println(Thread.currentThread().getName() + " lost.");
            }
        }
}
