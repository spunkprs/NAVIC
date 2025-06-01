package multithreading.problems.nonblockingdatastructure.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 This class aims at demonstrating the simulation of a non blocking queue i.e where during multiple push && multiple pop operations can happen
 simultaneously{another thread will be keep on trying to perform it's action in the loop instead of getting blocked}, have made use of
 AtomicReference along with some explicit locking{without explicit locking up to this extent is required here because in queue we are inserting at tail but
 removing from head unlike stack where both push && pop are happening at head}, here are the following features of the queue :-

 a.) Supports push operation in the multithreaded environment and guarantees no inconsistency
 b.) Supports pop operation in the multithreaded environment and guarantees no inconsistency
 c.) Non blocking class i.e AtomicReference is being used for both push && pop operations
 d.) This queue supports both pop && push operation in the multithreaded environment && makes sure no two pop OR push operations happen simultaneously,
 along with this, it also makes sure that independent push && pop operations will seem to be getting exercised simultaneously but extra caution is being
 taken care of when the size of queue is 0 OR 1, to handle that effectively had to introduce a bit of explicit locking too
 e.) Did not want to make use of any size/counter to track size of the queue as it would unnecessarily complicate the logic
 f.) No efficient way of testing it unlike what we have done in NonBlockingQueueDemonstrationVariantOne as here both push && pop are getting exercised
 simultaneously && the order of pop && push can be different hence chances of having non null head and tail are there which ahs been validated too

 * */

public class NonBlockingQueueDemonstrationVariantTwo {

    public static void main(String ar[]) {

        NonBlockingQueue<Integer> queue = new NonBlockingQueue<>();

        int threadCount = 5;
        List<Runnable> insertionThreads = prepareInsertionThreads(threadCount, queue);
        List<Runnable> removalThreads = prepareRemovalThreads(threadCount, queue);

        //Start insertion && removal threads
        for (int i = 1; i <= threadCount; i++) {
            Thread insertionThread = new Thread(insertionThreads.get(i - 1));
            Thread removalThread = new Thread(removalThreads.get(i - 1));

            insertionThread.start();
            removalThread.start();
        }

        /**
         Did not want to clutter code using CyclicBarrier so put a sleep of 5 sec just to make sure all the push && pop operations are done
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
