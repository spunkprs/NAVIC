package multithreading.problems.completableFuture;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureUsageDemonstration {

    public static void main(String ar[]) {
        CompletableFuture<Integer> cfOne = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
                System.out.println("Execution from CompletableFuture 1 is in progress !!");
            } catch (InterruptedException e) {}
            return 10 * 10;
        });

        CompletableFuture<Integer> cfTwo = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("Execution from CompletableFuture 2 is in progress !!");
            } catch (InterruptedException e) {}
            return 100 * 100;
        });

        cfOne.thenAcceptAsync(result ->
                System.out.println("Result from cfOne : " + result)
        );

        cfTwo.thenAcceptAsync(result ->
                System.out.println("Result from cfTwo : " + result)
        );

        System.out.println("Execution getting done by main thread !! ");

        cfOne.join(); //Required for the main thread to wait for the completion of the task assigned to cfOne
        cfTwo.join(); //Required for the main thread to wait for the completion of the task assigned to cfTwo
    }
}
