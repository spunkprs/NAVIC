package multithreading.basics.atomics.atomicreference;

import java.util.concurrent.atomic.AtomicMarkableReference;


/*
This class aims at the demonstration of AtomicMarkableReference

References -->
a.) https://www.educative.io/courses/java-multithreading-for-senior-engineering-interviews/atomicmarkablereference

Credits --> Educative
* */

public class AtomicMarkableReferenceDemonstration {

        public static void main( String args[] ) {

            Long initialValue = new Long(5);
            Long finalValue = new Long(7);
            AtomicMarkableReference<Long> atomicMarkableReference = new AtomicMarkableReference<>(initialValue, false);

            // attempt to change the long value with the wrong expected mark
            boolean wasSuccess = atomicMarkableReference.compareAndSet(initialValue, finalValue, true, true);
            System.out.println("compare and set succeeded " + wasSuccess + " current value " + atomicMarkableReference.getReference());

            // attempt to change the long value with the right expected mark
            wasSuccess = atomicMarkableReference.compareAndSet(initialValue, finalValue, false, true);
            System.out.println("compare and set succeeded " + wasSuccess + " current value " + atomicMarkableReference.getReference());
        }

}
