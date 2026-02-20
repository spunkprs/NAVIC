package multithreading.problems.completableFuture.forkJoinPool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

/**
What Is Happening Internally
When you call:
CompletableFuture.supplyAsync(...)

Internally it does:-
a.) ForkJoinPool.commonPool().submit(task);
b.) Each task becomes a ForkJoinTask.

ForkJoinPool:-
a.) Has worker threads equal to ~ CPU cores
b.) Each thread has its own deque
c.) Uses work-stealing to balance load

Important Insight:-
Only these methods use ForkJoinPool by default:-

a.) supplyAsync()
b.) runAsync()
c.) thenApplyAsync()
d.) thenAcceptAsync()
e.) thenComposeAsync()

Non-async versions (thenApply) do NOT use a pool.
 * */

public class ForkJoinPoolExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        System.out.println(ForkJoinPool.commonPool());  //Parallelism ≈ number of CPU cores - 1

        System.out.println("Main Thread: " + Thread.currentThread().getName());

        CompletableFuture<Integer> future1 =
                CompletableFuture.supplyAsync(() -> {
                    System.out.println("Task 1 running on: " + Thread.currentThread().getName());
                    return 10;
                });

        CompletableFuture<Integer> future2 =
                CompletableFuture.supplyAsync(() -> {
                    System.out.println("Task 2 running on: " + Thread.currentThread().getName());
                    return 20;
                });

        CompletableFuture<Integer> combined =
                future1.thenCombine(future2, (a, b) -> {
                    System.out.println("Combining on: " + Thread.currentThread().getName());
                    return a + b;
                });

        System.out.println("Final Result: " + combined.get());
    }
}
