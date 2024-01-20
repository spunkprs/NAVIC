package multithreading.basics;

public class DaemonThreadCreation {

    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();
        Thread daemonThread = new Thread(new DaemonThread());
        daemonThread.setDaemon(true);
        daemonThread.start();
        System.out.println("Priority of daemon thread " + daemonThread.getPriority());
        System.out.println("Priority of main thread " + mainThread.getPriority());
        try {
            Thread.sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
