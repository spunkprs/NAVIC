package multithreading.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreApplication {

    /*
    This example aims at mimicing behaviour of semaphore, it talks about a scenario where multiple concurrent threads
    tries to download content from the same web server but due to provide a check on the number of allowed concurrent access
    only limited web requests will be able to download the content at a given time

    Semaphore constructor accepts two following parameters :-
    a.) Number of permits --> Number of allowed concurrent access post which thread trying to access it would go into
    blocking state
    b.) Fairness --> Longest waiting thread would get to access semaphore if it's set to true
    * */
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3, true);

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                downloadData(semaphore);
            });
        }

        executorService.shutdown();
    }

    private static void downloadData(Semaphore semaphore) {
        try {
            semaphore.acquire();
            System.out.println("Going to download data from the web !!");
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }
}
