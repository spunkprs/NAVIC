package multithreading.basics.atomics.atomicreference;

import java.util.concurrent.atomic.AtomicStampedReference;

/*
This class aims at demonstrating the usage of AtomicStampedReference class && how both typed reference && integer stamp is used to handle
A-B-A problem that is bound to happen in non blocking algorithms

As per java docs Pair class ins used internally in AtomicStampedReference -->

private static class Pair<T> {
        final T reference;
        final int stamp;
        private Pair(T reference, int stamp) {
            this.reference = reference;
            this.stamp = stamp;
        }
        static <T> Pair<T> of(T reference, int stamp) {
            return new Pair<T>(reference, stamp);
        }
    }

    private volatile Pair<V> pair;


References -->
a.) https://jenkov.com/tutorials/java-concurrency/non-blocking-algorithms.html
b.) https://jenkov.com/tutorials/java-util-concurrent/atomicstampedreference.html
c.) https://www.educative.io/courses/java-multithreading-for-senior-engineering-interviews/atomicstampedreference

Credits --> Educative
* */

public class AtomicStampedReferenceDemonstration {

        public static void main( String args[] ) {

            Long initialValue = new Long(3);
            Long finalValue = new Long(7);

            // set the initial stamp to 1 and reference to myLong
            AtomicStampedReference<Long> atomicStampedReference = new AtomicStampedReference<>(initialValue,1);

            // we attempt to change the object reference but use the incorrect stamp[it shall be 1 instead of 0] and the compareAndSet fails
            boolean result = atomicStampedReference.compareAndSet(initialValue, finalValue, 0, 1);
            System.out.println("Was compareAndSet() successful : " + result + " and object value is " + atomicStampedReference.getReference().toString());

            // we attempt compareAndSet again with the right expected stamp
            result = atomicStampedReference.compareAndSet(initialValue, finalValue, 1, 2);
            System.out.println("Was compareAndSet() successful : " + result + " and object value is " + atomicStampedReference.getReference().toString());

            // Retrieve the current stamp and reference using the get() method
            int[] currStamp = new int[1];
            Long reference = atomicStampedReference.get(currStamp);
            System.out.println("current stamp " + currStamp[0] + " reference value " + reference.toString());
        }

}
