package multithreading.explicitlocking;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NotUnlocking {

    /*
    This is a clear example when one of the threads forgot to unlock the thread, though T1 acquires the lock completes it's execution but because of
    missing explicit call to unlock on the same lock, T2 will keep on waiting for the lock forever, clear example of deadlock
    This is one the major drawback we see while making use of explicit locking
    * */
    public static void main(String[] args) {

        Lock lock = new ReentrantLock();

        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread 1 execution started !!");
                lock.lock();
                System.out.println("Lock has been acquired by Thread 1");
                System.out.println("Thread 1 execution ended !!");
            }
        };

        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Thread 2 execution started !!");
                    Thread.sleep(1000);
                    lock.lock();
                    System.out.println("Lock has been acquired by Thread 2");
                    lock.unlock();
                    System.out.println("Thread 2 execution ended !!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread threadOne = new Thread(r1);
        Thread threadTwo = new Thread(r2);

        threadOne.start();
        threadTwo.start();
    }
}
