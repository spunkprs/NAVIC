package multithreading.problems.customthreadpoolexecution;

public class Worker implements Runnable {

    private Runnable taskAllocated;
    private Thread runningThread;
    private String workerName;
    private CustomThreadPool.NodeUtility nodeUtility;

    public Worker(Runnable taskAllocated, String workerName, CustomThreadPool.NodeUtility nodeUtility) {
        this.taskAllocated = taskAllocated;
        this.workerName = workerName;
        this.runningThread = new Thread(this, this.workerName);
        this.nodeUtility = nodeUtility;
    }

    public Thread getRunningThread() {
        return runningThread;
    }

    @Override
    public void run() {
        boolean stoppedAbruptly = false;

        while (taskAllocated != null || (taskAllocated = nodeUtility.removeNode().getTask()) != null) {
            System.out.println(this.workerName + " going to trigger task !!");
            try {
                taskAllocated.run();
            } catch (Exception e) {
                System.out.println("Exception during execution of the task by worker " + this.workerName);
                stoppedAbruptly = true;
                throw e;
            } finally {
                taskAllocated = null;
            }
        }
    }
}
