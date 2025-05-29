package multithreading.problems.diningphilospher.variant2;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class DiningPhilosopherDemonstration {

    public static void main(String ar[]) {

        int maxPhilosophersCount = 5;

        int phislosopherIdWithDifferentOrdering = new Random().nextInt(maxPhilosophersCount + 1);

        phislosopherIdWithDifferentOrdering = phislosopherIdWithDifferentOrdering == 0 ? 1 : phislosopherIdWithDifferentOrdering;

        Semaphore[] forks = prepareSemaphores(maxPhilosophersCount);

        Runnable[] philosophers = preparePhilosophers(forks, forks.length, 1000l, phislosopherIdWithDifferentOrdering);

        for (int i = 0; i < philosophers.length; i++) {
            Thread thread = new Thread(philosophers[i]);
            thread.start();
        }
    }

    private static Runnable[] preparePhilosophers(Semaphore[] forks, int maxAllowedPhislosphersCount, long contemplationTime,
                                                  int philosopherIdWithDifferentOrdering) {

        Runnable [] diningPhilosophers = new DiningPhilosopher[forks.length];

        for (int i = 1; i <= maxAllowedPhislosphersCount; i++) {
            diningPhilosophers[i - 1] = new DiningPhilosopher(i, contemplationTime, forks, philosopherIdWithDifferentOrdering);
        }
        return  diningPhilosophers;
    }

    private static Semaphore[] prepareSemaphores(int maxPhilosphersCount) {
        Semaphore[] forks = new Semaphore[maxPhilosphersCount];
        for (int i = 0; i < maxPhilosphersCount; i++) {
            forks[i] = new Semaphore(1);
        }
        return forks;
    }
}
