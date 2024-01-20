package multithreading.basics;

public class DaemonThread implements Runnable {
        @Override
        public void run() {
            int counter = 1;
            while (true) {
                System.out.println("Daemon Thread " + counter);
                counter++;
            }
        }
}
