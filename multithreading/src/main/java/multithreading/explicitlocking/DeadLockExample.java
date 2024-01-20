package multithreading.explicitlocking;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockExample {

    /*
    Classic articulation of deadlock because of acquiring of locks in cyclic fashion, here Worker1 tries to acquire
    lockOne --> lockTwo && Worker2 tries to acquire lockTwo --> lockOne
    Both Worker1/2 will be in BLOCKED state forever because Worker1 will be in blocked state waiting for lockTwo &&
    Worker2 will be in BLOCKED state waiting for lockOne
    * */
    public static void main(String[] args) {
        Lock lockOne = new ReentrantLock();
        Lock lockTwo = new ReentrantLock();

        Runnable rOne = () -> {
          lockOne.lock();
          System.out.println("Worker1 has acquired lock lockOne");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            lockTwo.lock();
            System.out.println("Worker1 has acquired lock lockTwo");

            lockTwo.unlock();
            lockOne.unlock();
        };

        Runnable rTwo = () -> {
            lockTwo.lock();
            System.out.println("Worker2 has acquired lock lockTwo");

            lockOne.lock();
            System.out.println("Worker2 has acquired lock lockOne");

            lockOne.unlock();
            lockTwo.unlock();
        };

        Thread threadOne = new Thread(rOne);
        Thread threadTwo = new Thread(rTwo);

        threadOne.start();
        threadTwo.start();
    }



}
