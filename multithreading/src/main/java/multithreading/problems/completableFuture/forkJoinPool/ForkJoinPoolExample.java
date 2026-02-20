package multithreading.problems.completableFuture.forkJoinPool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

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
