package multithreading.problems.nonblockingdatastructure.stack.variant1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
a.) Makes use of AtomicReference that is non blocking for both push && pop operation
b.) Makes use of Atomic Scalar i.e AtomicInteger to maintain count of records in the stack
* */

public class NonBlockingStack<T> {

    private AtomicReference<StackNode<T>> atomicReference;
    private AtomicInteger size;

    public NonBlockingStack() {
        this.atomicReference = new AtomicReference<>();
        size = new AtomicInteger(0);
    }

    public void push(T item) {
        StackNode<T> existingHead = null;
        StackNode<T> newHead = null;
        do {
            existingHead = atomicReference.get();
            newHead = new StackNode<>(item);
            newHead.setNext(existingHead);
        } while (!atomicReference.compareAndSet(existingHead, newHead));

        int sizeOfStack = 0;
        do {
            sizeOfStack = size.get();
        } while (!size.compareAndSet(sizeOfStack, sizeOfStack + 1));
    }

    public void pop() {
        StackNode<T> existingHead = null;
        StackNode<T> newHead = null;
        do {
            existingHead = atomicReference.get();
            if (existingHead == null) {
                return;
            }
            newHead = existingHead.getNext();
        } while (!atomicReference.compareAndSet(existingHead, newHead));

        int sizeOfStack = 0;
        do {
            sizeOfStack = size.get();
        } while (!size.compareAndSet(sizeOfStack, sizeOfStack - 1));
    }


    /**
     This method will give the result that will eventually be consistent
    * */
    public int getSize() {
        return size.get();
    }
}
