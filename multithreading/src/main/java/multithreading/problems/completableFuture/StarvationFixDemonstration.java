package multithreading.problems.completableFuture;

import java.util.concurrent.CompletableFuture;

/**
Channeling output from one task to another, here order of execution is External --> Internal

Because of supplyAsync method being called upon CompletableFuture for both external && internal tasks are being taken care of by ForkJoinPool as we have
not provided custom executor to CompletableFuture

Another important thing to notice here is order of execution External --> Internal, hence all the external tasks to be done first then only internal tasks can be
picked up by pool of threads, that what is being dictated by method thenCompose(takes Function as method parameter) i.e when result from external task is available
then only internal task will be picked by the concerned thread from the pool

Another important thing to consider here is thenComposeAsync could have been used instead of thenCompose && here are difference between them :-

Internal Behavior Difference :--

1.) thenCompose() :-

a.) Adds a completion callback
b.) When Stage 1 completes:
    b.1) The same worker executes Stage 2 immediately

c.) No additional scheduling overhead

2.) thenComposeAsync() :-

    When Stage 1 completes:
        a.) Submits Stage 2 to executor
        b.) Executor queue receives new task
        c.) A worker later executes it


 What That Means in Practice :-

 Assume:-

 CompletableFuture.supplyAsync(() -> {
 System.out.println("Stage 1: " + Thread.currentThread().getName());
 return 10;
 })
 .thenCompose(result -> {
 System.out.println("Stage 2: " + Thread.currentThread().getName());
 return CompletableFuture.completedFuture(result * 2);
 });

 Possible output:
 Stage 1: ForkJoinPool.commonPool-worker-1
 Stage 2: ForkJoinPool.commonPool-worker-1

 No thread switch.


 Now with thenComposeAsync:-

 .thenComposeAsync(result -> {
 System.out.println("Stage 2: " + Thread.currentThread().getName());
 return CompletableFuture.completedFuture(result * 2);
 });


 Output:
 Stage 1: ForkJoinPool.commonPool-worker-1
 Stage 2: ForkJoinPool.commonPool-worker-3

 New task submitted. Possibly different thread.
 * */

public class StarvationFixDemonstration {

    public static void main(String ar[]) {

        CompletableFuture<Integer> completableFutures[] = new CompletableFuture[10];

        for (int i = 1; i <= 10; i++) {
            completableFutures[i - 1] = CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(500);
                    System.out.println("External thread running on: " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return 100;
            }).thenCompose( result -> {
                CompletableFuture<Integer> innerResult = CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(100);
                        System.out.println("Internal thread running on: " + Thread.currentThread().getName() + " pulling value from external thread " + result * 5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return result * 5;
                });
                return innerResult;
            });
        }

        CompletableFuture.allOf(completableFutures).join();
        System.out.println("Process Completed !!");
    }
}
