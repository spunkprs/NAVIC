package multithreading.intrinsiclocks;

/*
Information about object monitor :
https://docs.oracle.com/cd/E17802_01/j2se/j2se/1.5.0/jcp/beta1/apidiffs/java/lang/Object.html
https://docs.oracle.com/javase/tutorial/essential/concurrency/locksync.html
* */
public class IncrementingValueSimultaneouslyGivingUnreliableOutput {

    static int counter = 0;
    /*
    Both the threads have critical section present that takes care of updating value counter that's present in heap space,
    both threads with time to get accessed by CPU, ideally final value of counter shall be 150 but because both the threads
    accessing counter and other one accessing while the updated value from first is not written back leads to inconsistency
    * */
    public static void main(String[] args) {
        process();
        System.out.println("Final value of counter :: " + counter);
    }

    private static void process() {
        Runnable runnableOne = () -> {
            for (int i = 1; i<= 100; i++) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                counter++;
            }
        };

        Runnable runnableTwo = () -> {
            for (int i = 1; i<= 50; i++) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                counter++;
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
