package multithreading.problems.nonblockingdatastructure.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 This class aims at demonstrating the simulation of a non blocking queue i.e where during multiple push && multiple pop operations can happen
 simultaneously{another thread will be keep on trying to perform it's action in the loop instead of getting blocked}, have made use of
 AtomicReference instead of any intrinsic or explicit locking methodologies, here are the following features of the queue :-

 a.) Supports push operation in the multithreaded environment and guarantees no inconsistency
 b.) Supports pop operation in the multithreaded environment and guarantees no inconsistency
 c.) Non blocking class i.e AtomicReference is being used for both push && pop operations
 d.) This queue supports both pop && push operation in the multithreaded environment && makes sure no two pop OR push operations will be happening
 simultaneously that can later lead to inconsistencies && this behaviour is shall be tested using multiple threads doing push operation simultaneously &&
 then when all the push is being done multiple threads doing pop operation simultaneously && at the end both head && tail shall be null
 e.) Did not want to make use of any size/counter to track size of the queue as it would unnecessarily complicate the logic

 * */

public class NonBlockingQueueDemonstrationVariantOne {

    public static void main(String ar[]) {
        NonBlockingQueue<Integer> queue = new NonBlockingQueue<>();

        int threadCount = 5;
        List<Runnable> insertionThreads = prepareInsertionThreads(threadCount, queue);
        List<Runnable> removalThreads = prepareRemovalThreads(threadCount, queue);

        //Start insertion threads
        for (int i = 1; i <= threadCount; i++) {
            Thread insertionThread = new Thread(insertionThreads.get(i - 1));
            insertionThread.start();
        }

        /**
        Did not want to clutter code using CyclicBarrier so put a sleep of 5 sec just to make sure all the push operations are done
        before pop operations begins, could have done it using join as well
        * */
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Start removal threads
        for (int i = 1; i <= threadCount; i++) {
            Thread removalThread = new Thread(removalThreads.get(i - 1));
            removalThread.start();
        }


        /**
         Did not want to clutter code using CyclicBarrier so put a sleep of 5 sec just to make sure all the pop operations are done
         before head && tail evaluations are done, could have done it using join as well
         * */
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Head of the queue after all push && pop is " + queue.getReferenceForHead().get());
        System.out.println("Tail of the queue after all push && pop is " + queue.getReferenceForTail().get());
    }

    private static List<Runnable> prepareRemovalThreads(int threadCount, NonBlockingQueue<Integer> queue) {
        List<Runnable> removalThreads = new ArrayList<>();
        for (int i = 1 ; i <= threadCount; i++) {
            removalThreads.add(new RemovalThread<Integer>("removal thread - " + i, queue, new Random().nextInt(1200)));
        }
        return removalThreads;
    }

    private static List<Runnable> prepareInsertionThreads(int threadCount, NonBlockingQueue<Integer> queue) {
        List<Runnable> insertionThreads = new ArrayList<>();
        for (int i = 1 ; i <= threadCount; i++) {
            insertionThreads.add(new InsertThread<Integer>("insertion thread - " + i, i, queue, new Random().nextInt(1200)));
        }
        return insertionThreads;
    }

}
