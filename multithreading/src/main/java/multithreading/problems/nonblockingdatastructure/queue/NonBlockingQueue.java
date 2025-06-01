package multithreading.problems.nonblockingdatastructure.queue;

import java.util.concurrent.atomic.AtomicReference;

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
               if (referenceForHead.get() == null && referenceForTail.get() == null) {
                   referenceForTail = new AtomicReference<>(newTail);
                   referenceForHead = new AtomicReference<>(newTail);
                   return;
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
                } else if (referenceForHead.get() != null && referenceForHead.get().getNext() == null) {
                    referenceForTail = new AtomicReference<>();
                }
            }
            head = referenceForHead.get();
            newHead = head.getNext();
        } while(!referenceForHead.compareAndSet(head, newHead));
    }
}
