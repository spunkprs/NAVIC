package multithreading.basics;

public class WaitingForDaemonThreadToComplete {

    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();
        Thread daemonThread = new Thread(new DaemonThread());
        daemonThread.setDaemon(true);
        daemonThread.start();
        try {
            //mainThread.join();
            daemonThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
