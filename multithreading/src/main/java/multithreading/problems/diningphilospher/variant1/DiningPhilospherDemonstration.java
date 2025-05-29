package multithreading.problems.diningphilospher.variant1;

/*
This solution aims at solving dining philospher problem, considering the solution doesn't end up in a deadlock
Following are the points that have been taken into consideration while solving the problem :-

1.) Depending on the number of the fork count, we try to pull max number of philosophers that can eat simultaneously, rest of
them needs to wait till currently engaged ones are done
2.) Order of picking && order of releasing forks for all the philosophers are same
3.) In this set up any philosopher can eat multiple times but every time he/she eats 2 forks will be needed, hence process of contemplation && eating
is put inside infinite loop catering the points this problem is looking for


References -->
a.) https://www.educative.io/courses/java-multithreading-for-senior-engineering-interviews/dining-philosophers

Credits --> Educative
*/

import java.util.concurrent.Semaphore;

public class DiningPhilospherDemonstration {

    public static void main(String ar[]) {

        int maxPhilosphersCount = 5;

        Semaphore[] forks = prepareSemaphores(maxPhilosphersCount);

        /*
        This will ensure only these number of philosophers are allowed to eat at the same time,
        division by 2 is done because each philosopher requires 2 fork to start eating
        */
        Semaphore maxAllowedPhislosphers = new Semaphore(maxPhilosphersCount/2);

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
