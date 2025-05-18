package multithreading.basics.atomics.atomicreference;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AtomicDeleteInconsistentResult {

        public static void main( String args[] ) throws Exception {
            SimpleNode nodeC = new SimpleNode(3, null);
            SimpleNode nodeB = new SimpleNode(2, nodeC);
            SimpleNode nodeA = new SimpleNode(1, nodeB);

            Lock lock = new ReentrantLock();

            // NodeA --> NodeB --> NodeC

            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {

                    // deleting NodeB
                    SimpleNode expected = (SimpleNode) nodeA.next.get();
                    SimpleNode next = (SimpleNode) nodeB.next.get();

                    // thread goes to sleep
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException ie) {
                        // ignore
                    }

                    // thread wakes-up and successfully updates referece
                    nodeA.next.compareAndSet(expected, next);
                }
            });

            thread1.start();
            Thread.sleep(2000);

            Thread thread2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    // deleting NodeC
                    SimpleNode expected = (SimpleNode) nodeB.next.get();
                    SimpleNode next = (SimpleNode) nodeC.next.get();
                    nodeB.next.compareAndSet(expected, next);
                }
            });

            thread2.start();

            thread1.join();
            thread2.join();

            SimpleNode start = nodeA;
            while (start != null) {
                System.out.println(start.value + " ");
                start = (SimpleNode) start.next.get();
            }
        }

        static class SimpleNode extends AtomicReference {
            private SimpleNode next;
            private int value;

            public SimpleNode(int value, SimpleNode next) {
                this.next = next;
                this.value = value;
            }
        }

}
