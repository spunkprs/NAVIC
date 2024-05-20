package multithreading.intrinsiclocks;

public class ProducerConsumerProblem {

    /*
    Have come up with this variation of P&C problem where Producer will generate a number and will not generate
    further number unless it is consumed by the Consumer, Consumer will keep on waiting for producer to generate
    number and for producer it will not regenerate any number unless earlier generated number is not consumed by the
    consumer
    * */
    public static void main(String[] args) {
        Processor processor = new Processor();
        Thread producer = new Thread(new Producer(processor));
        Thread consumer = new Thread(new Consumer(processor));
        producer.start();
        consumer.start();
    }

    static class Producer implements Runnable {
        private Processor processor;

        public Producer(Processor processor) {
            this.processor = processor;
        }

        @Override
        public void run() {
            int maxCounter = 10;
            int i = 1;
            while (i <= maxCounter) {
                    processor.produce(i);
                    i++;
            }
        }
    }

    static class Consumer implements Runnable {
        private Processor processor;

        public Consumer(Processor processor) {
            this.processor = processor;
        }

        @Override
        public void run() {
            int maxCounter = 10;
            int i = 1;
            while (i <= maxCounter) {
                    processor.consume();
                    i++;
            }
        }
    }

    static class Processor {

        private int value;
        private boolean flag;

        //Another way to make use of intrinsic locks/monitor, here in this case object of class Processor is the monitor
        /*public void test() {
            synchronized (this) {

            }
        }*/

        //Call to method notify() inside Object class doesn't relinquish lock/monitor owned by current thread,
        //in this specific case it gets relinquished once the flow moves out of synchronized block because lock is owned
        //once you enter synchronized block && it's relinquished once you move out of it
        synchronized public void produce(int num) {
            while (flag != false) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Producer printing value :: " + num);
            value = num;
            flag = true;
            notify();
        }

        synchronized public void consume() {
            while (flag != true) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Consumer consuming value :: " + value);
            flag = false;
            notify();
        }
    }
}
