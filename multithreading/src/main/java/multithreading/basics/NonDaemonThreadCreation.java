package multithreading.basics;

public class NonDaemonThreadCreation {

    public static void main(String[] args) {
        Thread nonDaemonThread = new Thread(new NonDaemonThread());
        nonDaemonThread.start();
        int counter = 1;
        while (true) {
            System.out.println("Main Thread " + counter);
            counter++;
        }
    }
}
