package multithreading.problems.completableFuture.forkJoinPool;

import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

/**
Here it is clearly seen that submitted tasks are being taken care of using ForkJoinPool threads

 ForkJoinPool:-
 a.) Has worker threads equal to ~ CPU cores
 b.) Each thread has its own deque
 c.) Uses work-stealing to balance load

 CompletableFuture defaults to ForkJoinPool.commonPool()

 It’s optimized for:-
 a.) Small async tasks
 b.) Chained computations
 c.) Work-stealing parallelism
 * */

public class ForkJoinPoolParallelThreadsSubmissionDemonstration {

    public static void main(String[] args) {

        CompletableFuture<?>[] futures = IntStream.range(1, 10)
                .mapToObj(i ->
                        CompletableFuture.supplyAsync(() -> {
                            System.out.println("Task " + i +
                                    " running on: " + Thread.currentThread().getName());
                            try { Thread.sleep(1000); } catch (Exception e) {}
                            return i * 10;
                        })
                ).toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(futures).join();

        System.out.println("All tasks completed");
    }
}
