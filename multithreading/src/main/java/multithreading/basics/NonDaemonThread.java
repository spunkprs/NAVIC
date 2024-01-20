package multithreading.basics;

public class NonDaemonThread implements Runnable {
        @Override
        public void run() {
            int counter = 1;
            while (true) {
                System.out.println("Non Daemon Thread " + counter);
                counter++;
            }
        }
}
