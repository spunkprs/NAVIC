package multithreading.problems.nonblockingdatastructure.stack.variant2;

import multithreading.problems.nonblockingdatastructure.stack.variant1.NonBlockingStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 This class aims at demonstrating the simulation of a non blocking stack i.e where during multiple push && multiple pop operations can happen
 simultaneously{another thread will be keep on trying to perform it's action in the loop instead of getting blocked}, have made use of
 AtomicReference instead of any intrinsic or explicit locking methodologies, here are the following features of the stack :-

 a.) Supports push operation in the multithreaded environment and guarantees no inconsistency
 b.) Supports pop operation in the multithreaded environment and guarantees no inconsistency
 c.) Non blocking class i.e AtomicReference is being used for both push && pop operations
 d.) Makes use of Atomic Scalar i.e AtomicInteger to maintain count of records in the stack
 e.) Have made use of CyclicBarrier too with the initial barrier count of 2, just to make sure both the threads will only proceed towards pop once push by
 both are done successfully
 f.) This stack supports both pop && push operation in the multithreaded environment && makes sure no two pop OR push operations will be happening
 simultaneously that can later lead to inconsistencies && this behaviour is tested && along with it simultaneous operations of push && pop by various threads
 is also not leading to any inconsistencies but the way of testing by putting equal number of push && pop operations && validating size of the stack to be 0
 at the end is not full proof because of the ordering of the operations i.e multiple removal operations on an empty stack will not decrease the size but
 any push operation will definitely increase the size hence you may end up seeing varied non zero result against size of the stack in different runs


 References --> https://www.educative.io/courses/java-multithreading-for-senior-engineering-interviews/nonblocking-stack
 Credits --> Educative

 * */

public class NonBlockingStackDemonstration {

    public static void main(String ar[]) {
        NonBlockingStack<Integer> stack = new NonBlockingStack<>();

        int threadCount = 5;
        List<Runnable> insertionThreads = prepareInsertionThreads(threadCount, stack);
        List<Runnable> removalThreads = prepareRemovalThreads(threadCount, stack);

        //Start insertion && removal threads
        for (int i = 1; i <= threadCount; i++) {
            Thread insertionThread = new Thread(insertionThreads.get(i - 1));
            Thread removalThread = new Thread(removalThreads.get(i - 1));

            insertionThread.start();
            removalThread.start();
        }

        /**
         Added this sleep just to make sure pop operation on the stack via different threads gets completed in this time
         * */

        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Number of elements in the stack = " + stack.getSize());
    }

    private static List<Runnable> prepareRemovalThreads(int threadCount, NonBlockingStack<Integer> stack) {
        List<Runnable> removalThreads = new ArrayList<>();
        for (int i = 1 ; i <= threadCount; i++) {
            removalThreads.add(new RemovalThread<Integer>("removal thread - " + i, stack, new Random().nextInt(1200)));
        }
        return removalThreads;
    }

    private static List<Runnable> prepareInsertionThreads(int threadCount, NonBlockingStack<Integer> stack) {
        List<Runnable> insertionThreads = new ArrayList<>();
        for (int i = 1 ; i <= threadCount; i++) {
            insertionThreads.add(new InsertThread<Integer>("insertion thread - " + i, i, stack, new Random().nextInt(1200)));
        }
        return insertionThreads;
    }
}
