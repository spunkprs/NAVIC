package multithreading.problems.diningphilospher.variant2;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class DiningPhilosopherDemonstration {

    public static void main(String ar[]) {

        int maxPhilosophersCount = 5;

        int philosopherIdWithDifferentOrdering = new Random().nextInt(maxPhilosophersCount + 1);

        philosopherIdWithDifferentOrdering = philosopherIdWithDifferentOrdering == 0 ? 1 : philosopherIdWithDifferentOrdering;

        Semaphore[] forks = prepareSemaphores(maxPhilosophersCount);

        Runnable[] philosophers = preparePhilosophers(forks, forks.length, 1000l, philosopherIdWithDifferentOrdering);

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

    private static Semaphore[] prepareSemaphores(int maxPhilosophersCount) {
        Semaphore[] forks = new Semaphore[maxPhilosophersCount];
        for (int i = 0; i < maxPhilosophersCount; i++) {
            forks[i] = new Semaphore(1);
        }
        return forks;
    }
}
