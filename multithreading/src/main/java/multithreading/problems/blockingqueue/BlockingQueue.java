package multithreading.problems.blockingqueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
This class aims at presenting or coming up with producer/consumer problem solution, here are the following important points around it :-
a.) Here we have Blocking Queue which will be blocked for inserts once it reaches it's maximum size && it's blocked for pops once it's size is zero
b.) Have made use of singly linked list as an internal data structure {inserts are made at tail && removal from the head}
c.) Operations on BlockingQueue are mutually exclusive
d.) Only two operations {push && pop} are allowed
* */

public class BlockingQueue <T> {

    private int capacity;
    private int sizeOfTheQueue;
    private Node<T> head;
    private Node<T> tail;
    private Lock lock;
    private Condition isFull;
    private Condition isEmpty;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
        lock = new ReentrantLock();
        isFull = lock.newCondition();
        isEmpty = lock.newCondition();
    }

    public T enqueue(T item) {
        try {
            lock.lock();
            while (sizeOfTheQueue == capacity) {
                System.out.println(Thread.currentThread() + " Waiting for one of the consumer process to pull data from the queue before any further push");
                isFull.await();
                //lock.lock();
            }
            if (head == null) {
                head = new Node<T>(item);
                tail = head;
            } else {
                Node<T> node = new Node<>(item);
                tail.next = node;
                tail = node;
            }
            sizeOfTheQueue++;
            isEmpty.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            return item;
        }
    }

    public T dequeue() {
        Node nodeToBeDeleted = null;
        try {
            lock.lock();
            while (sizeOfTheQueue == 0) {
                System.out.println(Thread.currentThread() +" Waiting for one of the producer process to push data into the queue before any further pull");
                isEmpty.await();

                /*
                * Below 2 lines are very important to understand how explicit locking works using Reentrant lock internally
                a.) For the debugging purpose only I added hold count of lock just to confirm what's happening internally as every lock needs an unlock too
                otherwise other threads will be in the state of forever waiting
                b.) I was under impression that post wait() is called when thread reenters for CPU scheduling will have to take lock again then only we shall
                proceed for further processing to handle inconsistencies that pops up during critical sections
                But that's not the case whenever thread is coming back{post it receives signal} no need to explicitly lock again, lock will be taken care of
                automatically but don't forget to unlock in the finally section

                Important thing to notice --> As soon as I got rid of another lock {that's commented out now} program that was once stuck ran smoothly,
                it was stuck because of following reasons ->
                1.) As soon as thread was signalled to come back it took the lock
                2.) Post the lock mentioned in point 1, I made the mistake of taking lock explicitly once again but in the finally section unlock was
                called only once hence holdCount was not zero disabling other threads to acquire the lock
                * */
                //System.out.println("Lock hold count " + lock.getHoldCount());
                //lock.lock();
            }
            if (sizeOfTheQueue > 0) {
                nodeToBeDeleted = head;
                head = head.next;
                sizeOfTheQueue--;
                isFull.signalAll();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            return (T)nodeToBeDeleted.item;
        }

    }

    static class Node<T> {
        T item;
        Node<T> next;

        Node(T item) {
            this.item = item;
        }
    }
}
