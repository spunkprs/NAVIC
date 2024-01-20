package multithreading.intrinsiclocks;


/*
Information about object monitor :
https://docs.oracle.com/cd/E17802_01/j2se/j2se/1.5.0/jcp/beta1/apidiffs/java/lang/Object.html
https://docs.oracle.com/javase/tutorial/essential/concurrency/locksync.html
* */
public class IncrementingValueSimultaneouslyGivingReliableOutput {

    static int counter = 0;

    public static void main(String[] args) {
        process();
        System.out.println("Final value of counter :: " + counter);
    }

    private static void process() {
        Runnable runnableOne = () -> {
                for (int i = 1; i <= 100; i++) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (IncrementingValueSimultaneouslyGivingReliableOutput.class) {
                        counter++;
                    }
            }
        };

        Runnable runnableTwo = () -> {
                for (int i = 1; i<= 50; i++) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (IncrementingValueSimultaneouslyGivingReliableOutput.class) {
                        counter++;
                    }
            }
        };

        Thread threadOne = new Thread(runnableOne);
        Thread threadTwo = new Thread(runnableTwo);

        threadOne.start();
        threadTwo.start();

        try {
            threadOne.join();
            threadTwo.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
