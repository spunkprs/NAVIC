package multithreading.problems.nonblockingdatastructure.queue;

import java.util.concurrent.atomic.AtomicReference;

/**
 Here are the salient features of this limited non blocking queue :-
 a.) Makes use of two AtomicReference{because insertion is happening at tail && removal is happening at head} that is non blocking for
 both push && pop operation
 b.) Won't say it is fully non blocking because had to use explicit locking up to some extent to handle trivial edge cases which was
 not required in the case of stack because both push && pop operations are happening at head
 * */

public class NonBlockingQueue<T> {

    private AtomicReference<QueueNode<T>> referenceForHead;
    private AtomicReference<QueueNode<T>> referenceForTail;

    private Object lock = new Object();

    public NonBlockingQueue() {
        this.referenceForHead = new AtomicReference<>();
        this.referenceForTail = new AtomicReference<>();
    }

    public void push(T item) {
        QueueNode<T> tail = null;
        QueueNode<T> newTail = null;
        do {
            newTail = new QueueNode<>(item);

           synchronized (lock) {
               /**
               This condition is required to set both head && tail to the new node for the first successful push in the queue
               * */
               if (referenceForHead.get() == null && referenceForTail.get() == null) {
                   referenceForTail = new AtomicReference<>(newTail);
                   referenceForHead = new AtomicReference<>(newTail);
                   return;

               /**
               This condition is written specifically to cater the scenario when the length of queue is 1 && lock is released from the pop operation
                but the head has not been set to null yet && context switch happened && push operation acquires the lock
                Setting of head to null will be taken care of by the thread performing pop independently
               * */
               } else if (referenceForHead.get() != null && referenceForTail.get() == null) {
                   while (referenceForHead.get() != null);
                   referenceForTail = new AtomicReference<>(newTail);
                   referenceForHead = new AtomicReference<>(newTail);
                   return;
               }
           }
           tail = referenceForTail.get();
           tail.setNext(newTail);
        } while (!referenceForTail.compareAndSet(tail, newTail));
    }

    public void pop() {
        QueueNode<T> head = null;
        QueueNode<T> newHead = null;
        do {
            synchronized (lock) {
                if (referenceForHead.get() == null) {
                    return;

                /**
                * This condition is required to handle scenario when the length of queue is 1 && priority is given to pop instead of push
                 in case both the operations are there
                * */
                } else if (referenceForHead.get() != null && referenceForHead.get().getNext() == null) {
                    referenceForTail = new AtomicReference<>();
                }
            }
            head = referenceForHead.get();
            newHead = head.getNext();
        } while(!referenceForHead.compareAndSet(head, newHead));
    }

    /**
     Reference to head will be eventually consistent
    * */

    public AtomicReference<QueueNode<T>> getReferenceForHead() {
        return referenceForHead;
    }

    /**
     Reference to tail will be eventually consistent
     * */

    public AtomicReference<QueueNode<T>> getReferenceForTail() {
        return referenceForTail;
    }
}
