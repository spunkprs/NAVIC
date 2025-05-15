package multithreading.basics.atomics.atomicreference;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public class SingletonClassImplementation {

    private static int counter = 0;

    public static void main(String ar[]) {
        for (int i = 0; i < 10000; i++) {
            test();
        }

        if (counter == 0) {
            System.out.print("Singleton pattern implemented successfully using AtomicReference");
        } else {
            System.out.print("Failure in Singleton pattern implementation using AtomicReference");
        }
    }

    private static void test() {
        Set<Singleton> singletonSet = Collections.synchronizedSet(new HashSet<>());

        //Could have done using thread pool instead

        Thread t1 = new Thread(() -> {
            Singleton reference = Singleton.getObject();
            singletonSet.add(reference);
        });

        Thread t2 = new Thread(() -> {
            Singleton reference = Singleton.getObject();
            singletonSet.add(reference);
        });

        Thread t3 = new Thread(() -> {
            Singleton reference = Singleton.getObject();
            singletonSet.add(reference);
        });

        Thread t4 = new Thread(() -> {
            Singleton reference = Singleton.getObject();
            singletonSet.add(reference);
        });

        Thread t5 = new Thread(() -> {
            Singleton reference = Singleton.getObject();
            singletonSet.add(reference);
        });

        Thread t6 = new Thread(() -> {
            Singleton reference = Singleton.getObject();
            singletonSet.add(reference);
        });

        Thread t7 = new Thread(() -> {
            Singleton reference = Singleton.getObject();
            singletonSet.add(reference);
        });

        Thread t8 = new Thread(() -> {
            Singleton reference = Singleton.getObject();
            singletonSet.add(reference);
        });

        Thread t9 = new Thread(() -> {
            Singleton reference = Singleton.getObject();
            singletonSet.add(reference);
        });

        Thread t10 = new Thread(() -> {
            Singleton reference = Singleton.getObject();
            singletonSet.add(reference);
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();
        t10.start();


        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
            t6.join();
            t7.join();
            t8.join();
            t9.join();
            t10.join();

            if (singletonSet.size() > 1) {
                counter++;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    static class Singleton {
        private static AtomicReference<Singleton> singletonAtomicReference = new AtomicReference<>();

        public static Singleton getObject() {

            Singleton singletonObject = singletonAtomicReference.get();

            if (singletonObject != null) {
                return singletonObject;
            }

            singletonAtomicReference.compareAndSet(singletonObject, new Singleton());

            return singletonAtomicReference.get();
        }

        private Singleton() {

        }
    }
}
