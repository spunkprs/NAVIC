package multithreading.problems.nonblockingdatastructure.stack.variant2;

import multithreading.problems.nonblockingdatastructure.stack.variant1.NonBlockingStack;

/**
 Skeleton for thread that handles removals from the stack
 * */

public class RemovalThread<T> implements Runnable {

    private String threadName;
    private NonBlockingStack<T> nonBlockingStack;
    private long sleepTime;

    public RemovalThread(String threadName, NonBlockingStack<T> nonBlockingStack, long sleepTime) {
        this.threadName = threadName;
        this.nonBlockingStack = nonBlockingStack;
        this.sleepTime = sleepTime;
    }

    @Override
    public void run() {
        System.out.println(threadName + " proceeding with removal of head element from the non blocking stack !!");
        try {
            Thread.sleep(sleepTime);
            nonBlockingStack.pop();
            System.out.println(threadName + " removed head element from the non blocking stack successfully !!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
