package multithreading.latch;

import multithreading.executorservice.SingleThreadExecutor;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
* Useful when x number of independent needs to be triggered independently {breaking down a large problem into multiple
* smaller problems that can be taken care of independently}, catch is all those dependent tasks are being governed by a
* driver which can't be marked as completed until all the dependent tasks are over && dependent tasks shall not start
* until driver asks them to start !!
*
* Useful links -->
* a.) https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CountDownLatch.html
* b.) https://stackoverflow.com/questions/25729249/thread-becomes-disabled-for-thread-scheduling-purposes-what-does-that-mean
*
* */
public class CountDownLatchApplication {

    /*
    * Following possibilities are possible depending on latches's count value -->
    * a.) count == number of dependent tasks --> Driver && dependent tasks will behave as expected
    * b.) count < number of dependent tasks --> Driver will keep on waiting for count value to reach zero because
    * dependent tasks are less than count initial value, count value will never reach zero hence driver will be in keep
    * on waiting state
    * c.) count > number of dependent tasks --> All the driver related pending work will be done once count value is reached zero,
    * call to countDown() method by (number of dependent tasks-count) threads will see no impact because it's value is already
    * changed to zero by prior threads.
    * In this example main thread gets completed post child threads are over but subsequent calls to countDownLatchTwo.await()
    * in the main method will be done and subsequent of the main thread's statements will be executed before pending child/dependent
    * threads
    *
    * */
    public static void main(String ar[]) {
        CountDownLatch countDownLatchOne = new CountDownLatch(1);
        CountDownLatch countDownLatchTwo = new CountDownLatch(5);

        System.out.println("Driver is going to command dependent workers to initiate their relevant tasks independently");
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            executorService.execute(new Worker(i, countDownLatchOne, countDownLatchTwo));
        }
        System.out.println("Though dependent threads are started but it's execution wont' start !!");
        System.out.println("BLAH...BLAH...BLAH...BLAH.. !!");
        System.out.println("Now threads will start with their execution");
        countDownLatchOne.countDown();
        try {
            System.out.println("Driver waiting for dependent tasks to be over !!");
            countDownLatchTwo.await();
            System.out.println("Driver is getting completed post dependent tasks are over !!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }
}
