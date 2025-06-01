package multithreading.problems.nonblockingdatastructure.queue;

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

 * */

public class NonBlockingQueueDemonstration {

    public static void main(String ar[]) {

    }

}
