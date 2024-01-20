package multithreading.explicitlocking;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ModificationOfCommonVariableAcrossMultipleThreads {


    private static int counter = 0;
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        Runnable rOne = () -> {
          lock.lock();
          try {
              for (int i = 1; i <= 1000; i++) {
                  counter++;
              }
          } finally {
              lock.unlock();
          }
        };

        Runnable rTwo = () -> {
            lock.lock();
            try {
                for (int i = 1; i <= 2000; i++) {
                    counter++;
                }
            } finally {
                lock.unlock();
            }
        };

        Thread threadOne = new Thread(rOne);
        Thread threadTwo = new Thread(rTwo);

        threadOne.start();
        threadTwo.start();

        try {
            threadOne.join();
            threadTwo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final value of variable counter :: " + counter);
    }


}
