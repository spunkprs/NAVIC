package multithreading.basics.synchronizedcollection;

import java.util.ArrayList;
import java.util.List;

/*
* This simulation is corresponding to the the usage of synchronized collection that we get by making use of
* Collections.synchronizedCollection(), though all the individual operation/methods of any synchronized collection
* is atomic but still chances of getting inconsistent results or exception are there in case client locking is not enabled
*
* But here inside SynchronizedListConsistent we have enabled client locking hence intermittent IndexOutOfBoundsException
* which we were seeing in the previous case is not seen here
*
* */

public class ConsistentSynchronizedListSimulation {

    public static void main(String ar[]) {
        ConsistentSynchronizedListSimulation unit = new ConsistentSynchronizedListSimulation();
        try {
            for (int i = 1; i < 10000; i++) {
                unit.triggerSimulation();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void triggerSimulation() {
        List<String> words = new ArrayList<>();
        words.add("10");
        words.add("20");
        words.add("30");

        SynchronizedListConsistent synchronizedList = new SynchronizedListConsistent(words);

        //This thread will try to remove last element
        Thread threadOne = new Thread(() -> {
            synchronizedList.removeLast();
        });

        //This thread will try to fetch last element
        Thread threadTwo = new Thread(() -> {
            synchronizedList.getLast();
        });

        //Starting both the threads
        threadOne.start();
        threadTwo.start();
    }
}
