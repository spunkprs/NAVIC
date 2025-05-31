package multithreading.problems.nonblockingdatastructure.stack.variant2;

import multithreading.problems.nonblockingdatastructure.stack.variant1.NonBlockingStack;

public class InsertThread<T> implements Runnable {

    private String threadName;
    private T itemToBeInserted;
    private NonBlockingStack<T> nonBlockingStack;
    private long sleepTime;

    public InsertThread(String threadName, T itemToBeInserted, NonBlockingStack<T> nonBlockingStack, long sleepTime) {
        this.threadName = threadName;
        this.itemToBeInserted = itemToBeInserted;
        this.nonBlockingStack = nonBlockingStack;
        this.sleepTime = sleepTime;
    }

    @Override
    public void run() {
        System.out.println(threadName + " proceeding with insertion of of element " + itemToBeInserted + " to the head of non blocking stack !!");
        try {
            Thread.sleep(sleepTime);
            nonBlockingStack.push(itemToBeInserted);
            System.out.println(threadName + " inserted element " +  itemToBeInserted + " to the head of non blocking stack successfully !!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
