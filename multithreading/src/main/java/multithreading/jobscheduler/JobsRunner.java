package multithreading.jobscheduler;

/**
This class is solely responsible for adding/removing tasks && calling TasksOrchestrator
 * */

public class JobsRunner {
    private TaskOrchestrator taskOrchestrator;

    public JobsRunner(TaskOrchestrator taskOrchestrator) {
        this.taskOrchestrator = taskOrchestrator;
    }

    public void startProcess() {
        try {
            while (taskOrchestrator.getPriorityBlockingQueue().size() > 0) {
                taskOrchestrator.orchestrateTasks();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            taskOrchestrator.getExecutorService().shutdown();
        }
    }

}
