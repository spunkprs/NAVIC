package multithreading.basics.cme.multithread;

/*
This class is the demonstration against running into ConcurrentModificationException while working in the multi
threaded environment when map is getting altered by one thread while traversal is being done by another
* */

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CmeDemonstrationInMultiThreadEnvironment {

    public static void main( String args[] ) throws Exception {

        HashMap<String, Integer> map = new HashMap<>();
        ExecutorService es = Executors.newFixedThreadPool(5);

        try {
            // create a task that slowly reads from the map.
            Runnable reader = new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ie) { /*ignore*/ }

                    for (Map.Entry<String, Integer> entry : map.entrySet()) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ie) { /*ignore*/ }
                        System.out.println("key " + entry.getKey() + " value " + entry.getValue());
                    }
                }
            };

            // create a task to write to the map a little faster than the reader
            Runnable writer = new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 100; i++) {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException ie) { /*ignore*/ }
                        map.put("key-" + i, i);
                    }
                }
            };

            // submit the task twice
            Future future1 = es.submit(writer);
            Future future2 = es.submit(reader);

            // wait for the threads to finish
            future1.get();
            future2.get();
        } finally {
            // We know the code will generate a ConcurrentModificationException so
            // remember to shutdown the executor service in a finally block
            es.shutdown();
        }

    }
}
