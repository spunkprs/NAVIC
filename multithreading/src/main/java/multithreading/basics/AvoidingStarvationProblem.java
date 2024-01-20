package multithreading.basics;

public class AvoidingStarvationProblem {

    public static void main(String[] args) {
        Thread nonDaemonThread = new Thread(new NonDaemonThread());
        nonDaemonThread.start();
        int counter = 1;
        while (true) {
            System.out.println("Main Thread " + counter);
            counter++;
            Thread.yield();
        }
    }
}
