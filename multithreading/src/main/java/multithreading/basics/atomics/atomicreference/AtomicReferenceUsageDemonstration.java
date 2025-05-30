package multithreading.basics.atomics.atomicreference;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
This class aims at validating the atomic behaviour of class AtomicReference, it validates both the essential things as part of multithreading when
multiple threads enter into critical section which are following :-
a.) Changes to the shared variable is atomic
b.) Memory visibility to fetch latest reads && writes

Like Atomic scalars this also follows path of optimistic locking which means in the context of multithreaded environment multiple threads can
enter into critical section but changes to the shared variable will be done successfully by one thread only at a time

Reference to the object {be it of any type} is of type volatile && optimistic locking is being taken care of by method compareAndSet(expected, updated)

Detailing of the method is as follows :-

Atomically sets the value to the given updated value if the current value == the expected value.
Params:
expect – the expected value
update – the new value
Returns:
true if successful. False return indicates that the actual value was not equal to the expected value.

public final boolean compareAndSet(V expect, V update) {
        return unsafe.compareAndSwapObject(this, valueOffset, expect, update);
    }


References :-
a.) https://jenkov.com/tutorials/java-util-concurrent/atomicreference.html
b.) https://www.educative.io/courses/java-multithreading-for-senior-engineering-interviews/atomicreference

Credits --> Educative

* */

public class AtomicReferenceUsageDemonstration {

    static AtomicReference<Thread> atomicReference = new AtomicReference<>();

        public static void main( String args[] ) throws Exception {

            ExecutorService executor = Executors.newFixedThreadPool(50);
            try {
                for (int i = 0; i < 25; i++) {
                    executor.submit(new Runnable() {
                        @Override
                        public void run() {

                            if (atomicReference.get() == null) {
                                if (atomicReference.compareAndSet(null, Thread.currentThread())) {
                                    System.out.println(Thread.currentThread().getName() + " takes first turn");
                                }
                            }
                        }
                    });
                }
            } finally {
                executor.shutdown();
                executor.awaitTermination(1, TimeUnit.HOURS);
            }
        }
}
