package multithreading.problems.diningphilospher.variant1;

import java.util.concurrent.Semaphore;

public class DiningPhilospher implements Runnable {

    private int philospherNumber;
    private long contemplationTime;
    private Semaphore[] forks;
    private Semaphore maxAllowedPhislopers;

    public DiningPhilospher(int philospherNumber, long contemplationTime, Semaphore[] forks, Semaphore maxAllowedPhislopers) {
        this.philospherNumber = philospherNumber;
        this.contemplationTime = contemplationTime;
        this.forks = forks;
        this.maxAllowedPhislopers = maxAllowedPhislopers;
    }

    @Override
    public void run() {
        while (true) {
            contemplate();
            eat();
        }
    }

    private void contemplate() {
        try {
            Thread.sleep(contemplationTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void eat() {
        try {
            maxAllowedPhislopers.acquire();
            //Pick left fork first
            forks[this.philospherNumber - 1].acquire();

            //Pick right fork second
            forks[(this.philospherNumber - 1 + forks.length - 1) % forks.length].acquire();
            System.out.println("Philospher number " + philospherNumber + " has started eating !!");
            Thread.sleep(1000);

            //Release left fork
            forks[this.philospherNumber - 1].release();
            //Release right fork
            forks[(this.philospherNumber - 1 + forks.length - 1) % forks.length].release();

            System.out.println("Philospher number " + philospherNumber + " done with eating !!");
            maxAllowedPhislopers.release();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
