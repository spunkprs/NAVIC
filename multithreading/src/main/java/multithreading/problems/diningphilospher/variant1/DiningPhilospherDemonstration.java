package multithreading.problems.diningphilospher.variant1;

/*
This solution aims at solving dining philospher problem, considering the solution doesn't end up in a deadlock

References -->
a.) https://www.educative.io/courses/java-multithreading-for-senior-engineering-interviews/dining-philosophers

Credits --> Educative
*/

import java.util.concurrent.Semaphore;

public class DiningPhilospherDemonstration {

    public static void main(String ar[]) {

        int maxPhilosphersCount = 5;

        Semaphore[] forks = prepareSemaphores(maxPhilosphersCount);
        Semaphore maxAllowedPhislosphers = new Semaphore(forks.length);

        Runnable[] philosphers = preparePhilosphers(forks, forks.length, 1000l, maxAllowedPhislosphers);

        for (int i = 0; i < philosphers.length; i++) {
            Thread thread = new Thread(philosphers[i]);
            thread.start();
        }
    }

    private static Runnable[] preparePhilosphers(Semaphore[] forks, int maxAllowedPhislosphersCount, long contemplationTime,
                                                  Semaphore maxAllowedPhislosphers) {

        Runnable [] diningPhilosphers = new DiningPhilospher[forks.length];

        for (int i = 1; i <= maxAllowedPhislosphersCount; i++) {
            diningPhilosphers[i - 1] = new DiningPhilospher(i, contemplationTime, forks, maxAllowedPhislosphers);
        }
        return  diningPhilosphers;
    }

    private static Semaphore[] prepareSemaphores(int maxPhilosphersCount) {
        Semaphore[] forks = new Semaphore[maxPhilosphersCount];
        for (int i = 0; i < maxPhilosphersCount; i++) {
            forks[i] = new Semaphore(1);
        }
        return forks;
    }
}
