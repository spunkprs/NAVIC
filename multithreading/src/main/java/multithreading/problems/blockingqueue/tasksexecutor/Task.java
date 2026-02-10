package multithreading.problems.blockingqueue.tasksexecutor;

public class Task {

    private String taskId;
    private String taskName;
    private long delayInExecution;

    public Task(String taskId, String taskName, long delayInExecution) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.delayInExecution = delayInExecution;
    }

    public void execute() {
        try {
            Thread.sleep(this.delayInExecution);
            System.out.print("Task id " + this.taskId + " with name " + this.taskName + " is getting executed !!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
