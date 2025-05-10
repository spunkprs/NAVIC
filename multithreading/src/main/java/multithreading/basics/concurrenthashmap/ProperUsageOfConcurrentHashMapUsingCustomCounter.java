package multithreading.basics.concurrenthashmap;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
In this class two threads tries to increment values against a key by 100, ideally if everything goes well
we shall have 200 as the value of the key, but this is the expected behaviour which will happen in all the cases as opposite
to what was demonstrated in ImproperUsageOfConcurrentHashMap

We are pretty clear that in ConcurrentHashMap put operation is thread safe but get operation is not hence
we took care of it using lock/monitor defined against class Counter that will guarantee no inconsistencies are there
*
*
* */

public class ProperUsageOfConcurrentHashMapUsingCustomCounter {

        // Class to keep track of vote count
        static class Counter {
            private int count = 0;

            void increment() {
                count++;
            }

            int getCount() {
                return count;
            }
        }

        public static void main( String args[] ) throws Exception {
            ConcurrentHashMap<String, Counter> map = new ConcurrentHashMap<>();
            map.put("Chandler", new Counter());

            ExecutorService es = Executors.newFixedThreadPool(5);

            // create a task to increment the vote count
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 100; i++) {
                        Counter mc = map.get("Chandler");

                        // explicit synchronization
                        synchronized (mc) {
                            mc.increment();
                        }
                    }
                }
            };

            // submit the task twice
            Future future1 = es.submit(task);
            Future future2 = es.submit(task);

            // wait for the threads to finish
            future1.get();
            future2.get();

            // shutdown the executor service
            es.shutdown();

            System.out.println("votes for Chandler = " + map.get("Chandler").getCount());
        }
}
