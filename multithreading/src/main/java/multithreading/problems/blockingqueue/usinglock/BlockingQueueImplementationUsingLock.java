package multithreading.problems.blockingqueue.usinglock;

/*
Driver class for the simulation of Bounded Buffer problem -->

Important points to notice that are following :-
a.) Have kept size of buffer smaller than the available producer && consumer threads {though it's not imperative}
b.) Velocity of consumption i.e consumer threads is more compared to producer, hence consumer threads would be waiting individually
for the producer threads to produce something that can be consumed by the consumer threads
* */

public class BlockingQueueImplementationUsingLock {

    public static void main(String ar[]) {
        BlockingQueue<String> blockingQueue = new BlockingQueue<>(3);

        Thread producerThreadOne = new Thread(new ProducerThread<String>("1", 1, 100, blockingQueue));
        Thread producerThreadTwo = new Thread(new ProducerThread<String>("2", 2, 200, blockingQueue));
        Thread producerThreadThree = new Thread(new ProducerThread<String>("3", 3, 300, blockingQueue));
        Thread producerThreadFour = new Thread(new ProducerThread<String>("4", 4, 400, blockingQueue));
        Thread producerThreadFive = new Thread(new ProducerThread<String>("5", 5, 500, blockingQueue));

        producerThreadOne.start();
        producerThreadTwo.start();
        producerThreadThree.start();
        producerThreadFour.start();
        producerThreadFive.start();

        Thread consumerThreadOne = new Thread(new ConsumerThread<String>(1, 0, blockingQueue));
        Thread consumerThreadTwo = new Thread(new ConsumerThread<String>(2, 0, blockingQueue));
        Thread consumerThreadThree = new Thread(new ConsumerThread<String>(3, 0, blockingQueue));
        Thread consumerThreadFour = new Thread(new ConsumerThread<String>(4, 100, blockingQueue));
        Thread consumerThreadFive = new Thread(new ConsumerThread<String>(5, 200, blockingQueue));

        consumerThreadOne.start();
        consumerThreadTwo.start();
        consumerThreadThree.start();
        consumerThreadFour.start();
        consumerThreadFive.start();
    }


}
