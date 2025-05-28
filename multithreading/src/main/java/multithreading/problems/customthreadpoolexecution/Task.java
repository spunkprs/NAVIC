package multithreading.problems.customthreadpoolexecution;

public class Task implements Runnable {

    private String taskName;
    private long executionTime;

    public Task(String taskName, long executionTime) {
        this.taskName = taskName;
        this.executionTime = executionTime;
    }

    public String getTaskName() {
        return taskName;
    }

    @Override
    public void run() {
        System.out.println("Task with name " + this.taskName + " going to get started !!");
        try {
            Thread.sleep(executionTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Task with name " + this.taskName + " executed successfully !!");
    }
}
