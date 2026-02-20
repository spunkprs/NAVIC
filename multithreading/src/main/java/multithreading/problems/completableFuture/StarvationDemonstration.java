package multithreading.problems.completableFuture;

import java.util.concurrent.CompletableFuture;

/**
At a higher level it seems like that this program will block forever because outer CF's are waiting for inner CF's result &&
number of available processors are way less than the submitted tasks

But sorry to disappoint this doesn't happens because ForkJoinPool is smart enough to detect blocking and with time it issues
compensating threads to handle inner tasks

Insightful information :--

Why You’re Seeing worker-10, worker-11

Because ForkJoinPool can: Create compensation threads

When it detects blocking via: ForkJoinPool.managedBlock()

It may temporarily create extra threads beyond parallelism.

This is called: Compensating for blocking

So what’s happening:-

a.) Worker thread blocks (e.g., join, IO)
b.) Pool detects blocking
c.) Pool creates new worker
d.) New worker gets next number (worker-10, worker-11)

That does NOT mean:-
a.) 11 threads running simultaneously
b.) Or pool size permanently increased

It means: Threads were created temporarily
 * */

public class StarvationDemonstration {

    public static void main(String ar[]) {
        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println("Available processors: " + cores);

        CompletableFuture<Integer> completableFutures[] = new CompletableFuture[40];

        for (int i = 1; i <= 40; i++) {
            completableFutures[i - 1] = CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Outer running on: " + Thread.currentThread().getName());

                // Nested async task
                CompletableFuture<Integer> innerFuture =
                        CompletableFuture.supplyAsync(() -> {
                            System.out.println("Inner running on: " + Thread.currentThread().getName());
                            return 100;
                        });

                return innerFuture.join(); //Blocking call because outer CF is waiting for inner CF result
            });
        }

        CompletableFuture.allOf(completableFutures).join();

        System.out.println("Process Completed !!");
    }
}
