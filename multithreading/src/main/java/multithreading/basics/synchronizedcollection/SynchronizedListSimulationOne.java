package multithreading.basics.synchronizedcollection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/*
* This simulation is corresponding to the the usage of synchronized collection that we get by making use of
* Collections.synchronizedCollection(), though all the individual operation/methods of any synchronized collection
* is atomic but still chances of getting inconsistent results or exception are there in case client locking is not enabled
*
* Below is the exception we got intermittently in the multithreaded environment where one thread is trying to pull last element
* from the synchronized list where as another one is deleting the last element in the same synchronized list
*
* Exception in thread "Thread-1" java.lang.IndexOutOfBoundsException: Index: 2, Size: 2
	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
	at java.util.ArrayList.get(ArrayList.java:435)
	at java.util.Collections$SynchronizedList.get(Collections.java:2419)
	at multithreading.basics.synchronizedcollection.SynchronizedList.getLast(SynchronizedList.java:15)
	at multithreading.basics.synchronizedcollection.SynchronizedListSimulationOne.lambda$main$1(SynchronizedListSimulationOne.java:27)
	at java.lang.Thread.run(Thread.java:748)
*
* */

public class SynchronizedListSimulationOne {


    public static void main(String ar[]) {
        List<String> words = new ArrayList<>();
        words.add("10");
        words.add("20");
        words.add("30");

        words = Collections.synchronizedList(words);

        SynchronizedListInconsistent synchronizedList = new SynchronizedListInconsistent(words);

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
