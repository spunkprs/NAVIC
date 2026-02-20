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

CompletableFuture defaults to ForkJoinPool.commonPool()

It’s optimized for:-
a.) Small async tasks
b.) Chained computations
c.) Work-stealing parallelism

Important Insight:-
Only these methods use ForkJoinPool by default:-

a.) supplyAsync() --> Takes Supplier functional interface as method parameter
b.) runAsync()
c.) thenApplyAsync() --> Takes Consumer functional interface as method parameter
d.) thenAcceptAsync()
e.) thenComposeAsync()

ForkJoinPool is used internally only when either of the above methods are used && considering custom executor instance is not provided to CompletableFuture

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
