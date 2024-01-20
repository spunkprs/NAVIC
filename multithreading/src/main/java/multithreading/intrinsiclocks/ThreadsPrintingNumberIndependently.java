package multithreading.intrinsiclocks;

import java.util.HashMap;
import java.util.Map;

public class ThreadsPrintingNumberIndependently {

    /*
    Have come up with a problem where there are three threads t1, t2, t3 where each one will be generating natural numbers
    i.e t1 will generate 1, 4, 7, 10 etc && t2 will generate 2, 5, 8 etc && t3 will generate 3, 6, 9 etc
    Important thing to notice here is t1 will first start it's process followed by t2 then t3 until max number is not achieved
    by any of the thread
    * */
    public static void main(String[] args) {

        Processor processor = new Processor(1, 10);
        Map<String, Integer> map = new HashMap<>();
        map.put("t1", 1);
        map.put("t2", 2);
        map.put("t3", 3);

        Thread tOne = new Thread(new Runnable() {
            @Override
            public void run() {
                processor.generateValue("t1", map);
            }
        });

        Thread tTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                processor.generateValue("t2", map);
            }
        });

        Thread tThree = new Thread(new Runnable() {
            @Override
            public void run() {
                processor.generateValue("t3", map);
            }
        });
        tOne.start();
        tTwo.start();
        tThree.start();
    }

    static class Processor {

        private int maxValue;
        private int initialValue;

        public Processor(int initialValue, int maxValue) {
            this.maxValue = maxValue;
            this.initialValue = initialValue;
        }

        synchronized public void generateValue(String threadName, Map<String, Integer> map) {
            while (initialValue <= maxValue) {
                if (threadName.equals("t1")) {
                    if (map.get("t1") == initialValue) {
                        printDataAndUpdateValue(threadName);
                        map.put("t1", map.get("t1") + 3);
                        notifyAll();
                    } else {
                        relinquishIntrinsicLock();
                    }
                } else if (threadName.equals("t2")) {
                    if (map.get("t2") == initialValue) {
                        printDataAndUpdateValue(threadName);
                        map.put("t2", map.get("t2") + 3);
                        notifyAll();
                    } else {
                        relinquishIntrinsicLock();
                    }
                } else if (threadName.equals("t3")) {
                    if (map.get("t3") == initialValue) {
                        printDataAndUpdateValue(threadName);
                        map.put("t3", map.get("t3") + 3);
                        notifyAll();
                    } else {
                        relinquishIntrinsicLock();
                    }
                }
            }
        }

        private void printDataAndUpdateValue(String threadName) {
            System.out.println(initialValue + " printed by thread " + threadName);
            initialValue += 1;
        }

        private void relinquishIntrinsicLock() {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
