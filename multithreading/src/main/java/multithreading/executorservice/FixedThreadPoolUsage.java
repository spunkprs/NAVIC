package multithreading.executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FixedThreadPoolUsage {

    /*
    Multiple worker threads will be responsible for running submitted tasks to the executor service
    Tasks will be executed in concurrent manner && number of concurrent tasks running at any given time
    is equal to the size that we provide while instantiating the thread pool
    Important thing to notice here is Worker threads{the one executing the underlying tasks} are getting reused,
    same worker thread id is getting reused multiple once it performs underlying task
    In case more detailing is required go through the source code, we will be able to find out that internally
    Worker threads are getting spawned up to handle underlying task/thread
    * */
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 1; i <= 10; i++) {
            executorService.execute(new SingleThreadExecutor.Task(i));
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
            System.out.println("Task with id " + this.taskId + " is running and have thread id " + Thread.currentThread().getId());
            //long sleepDuration = (long)Math.random() * 8;
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
