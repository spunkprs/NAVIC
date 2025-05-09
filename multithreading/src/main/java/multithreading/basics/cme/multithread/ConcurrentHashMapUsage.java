package multithreading.basics.cme.multithread;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
This class is the demonstration of usage of ConcurrentHashMap where read of the map is happening in one thread
where as write to the map is happening in another thread without witnessing ConcurrentModificationException

Another important point to note is though writer thread is trying to push 100 records in the map but reader
thread seeing way less records than that because reader is working on the snapshot of the map that's being taken
when iterator on the map is initialised
* */

public class ConcurrentHashMapUsage {

        public static void main( String args[] ) throws Exception {

            ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
            ExecutorService es = Executors.newFixedThreadPool(5);

            try {
                // create a reader that slowly reads from the map.
                Runnable reader = new Runnable() {
                    @Override
                    public void run() {

                        // wait for writer to put in some entries in the map
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException ie) { /*ignore*/ }

                        int seen = 0;
                        for (Map.Entry<String, Integer> entry : map.entrySet()) {
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException ie) { /*ignore*/ }
                            entry.getValue();
                            seen++;
                        }

                        System.out.println("Number of entries seen by reader thread : " + seen);
                    }
                };

                // create a writer that inputs 100 entries in the map
                Runnable writer = new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 100; i++) {
                            try {
                                Thread.sleep(3);
                            } catch (InterruptedException ie) { /*ignore*/ }
                            map.put("key-" + i, i);
                        }
                        System.out.println("Writer thread finished.");
                    }
                };

                // submit the two tasks
                Future future1 = es.submit(writer);
                Future future2 = es.submit(reader);

                // wait for the threads to finish
                future1.get();
                future2.get();
            } finally {
                es.shutdown();
            }
        }

}
