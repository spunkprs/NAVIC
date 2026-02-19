package multithreading.problems.future;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
Clear demonstration of Future and how future.get() call is blocking
Code below is self explanatory
 * */

public class FutureUsageDemonstration {

    public static void main(String ar[]) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Future<Integer> futureResultOne = executorService.submit(() -> {
            Thread.sleep(5000);
            return 50 * 50;
        });

        Future<Integer> futureResultTwo = executorService.submit(() -> {
            Thread.sleep(1000);
            return 10 * 10;
        });

        try {
            System.out.println("Result fetched from futureResultOne is " + futureResultOne.get());
            System.out.println("Result fetched from futureResultTwo is " + futureResultTwo.get());
        } catch (Exception e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }
}
