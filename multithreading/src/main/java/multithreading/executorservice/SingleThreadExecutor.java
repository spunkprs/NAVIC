package multithreading.executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SingleThreadExecutor {

    /*
    Single worker thread will be responsible for running submitted tasks to the executor service
    Tasks will be executed in sequential manner && no more than 1 task will be active at any point of time
    In case of failure of single thread worker prior to shutdown then new one will take it's place if needed
    to execute subsequent tasks
    * */
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 1; i <= 5; i++) {
            executorService.execute(new Task(i));
        }

        //Shutting down the executor service
        executorService.shutdown();
    }

    static class Task implements Runnable {

        private int taskId;

        public Task(int taskId) {
            this.taskId = taskId;
        }

        @Override
        public void run() {
            System.out.println("Task with id " + this.taskId + " is running and have thread id " + Thread.currentThread().getName());
            //long sleepDuration = (long)Math.random() * 8;
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
