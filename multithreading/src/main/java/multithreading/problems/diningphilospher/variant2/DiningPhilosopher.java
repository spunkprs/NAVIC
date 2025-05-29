package multithreading.problems.diningphilospher.variant2;

import java.util.concurrent.Semaphore;

public class DiningPhilosopher implements Runnable {

    private int philosopherNumber;
    private long contemplationTime;
    private Semaphore[] forks;
    private int philosopherWithDifferentOrdering;

    public DiningPhilosopher(int philosopherNumber, long contemplationTime, Semaphore[] forks, int philosopherWithDifferentOrdering) {
        this.philosopherNumber = philosopherNumber;
        this.contemplationTime = contemplationTime;
        this.forks = forks;
        this.philosopherWithDifferentOrdering = philosopherWithDifferentOrdering;
    }

    @Override
    public void run() {
        while (true) {
            contemplate();
            eat(this.philosopherWithDifferentOrdering);
        }
    }

    private void contemplate() {
        try {
            Thread.sleep(contemplationTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void eat(int philosopherWithDifferentOrdering) {
            if (this.philosopherNumber != philosopherWithDifferentOrdering) {
                proceedWithLeftPickingFirst();
            } else {
                proceedWithRightPickingFirst();
            }
    }

    private void proceedWithLeftPickingFirst() {
        try {
            //Pick left fork first
            forks[this.philosopherNumber - 1].acquire();

            //Pick right fork second
            forks[(this.philosopherNumber - 1 + forks.length - 1) % forks.length].acquire();

            System.out.println("Philosopher number " + philosopherNumber + " who follows left picking of fork first has started eating !!");
            Thread.sleep(1000);

            //Release left fork
            forks[this.philosopherNumber - 1].release();
            //Release right fork
            forks[(this.philosopherNumber - 1 + forks.length - 1) % forks.length].release();

            System.out.println("Philosopher number " + philosopherNumber + " who follows left picking of fork first done with eating !!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void proceedWithRightPickingFirst() {
        try {

            //Pick right fork first
            forks[(this.philosopherNumber - 1 + forks.length - 1) % forks.length].acquire();

            //Pick left fork second
            forks[this.philosopherNumber - 1].acquire();


            System.out.println("Philosopher number " + philosopherNumber + " who follows right picking of fork first has started eating !!");
            Thread.sleep(1000);

            //Release right fork
            forks[(this.philosopherNumber - 1 + forks.length - 1) % forks.length].release();

            //Release left fork
            forks[this.philosopherNumber - 1].release();

            System.out.println("Philosopher number " + philosopherNumber + " who follows right picking of fork first done with eating !!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
