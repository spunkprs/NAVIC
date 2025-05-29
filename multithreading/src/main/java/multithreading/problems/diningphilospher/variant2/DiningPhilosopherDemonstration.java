package multithreading.problems.diningphilospher.variant2;

import java.util.Random;
import java.util.concurrent.Semaphore;

/*
This solution aims at solving dining philosopher problem, considering the solution doesn't end up in a deadlock
Following are the points that have been taken into consideration while solving the problem :-

1.) All of the philosophers sitting on the table will try to start eating simultaneously unlike variant 1 of the solution where only x number of
philosophers will start eating simultaneously
2.) Order of picking && order of releasing forks for all the philosophers are same except one who follows different pattern to avoid
getting into deadlock
3.) In this set up any philosopher can eat multiple times but every time he/she eats 2 forks will be needed, hence process of contemplation && eating
is put inside infinite loop catering the points this problem is looking for


References -->
a.) https://www.educative.io/courses/java-multithreading-for-senior-engineering-interviews/dining-philosophers

Credits --> Educative
*/

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
