package multithreading.problems.customthreadpoolexecution;

public class Worker implements Runnable {

    private Runnable taskAllocated;
    private Thread runningThread;
    private String workerName;
    private CustomThreadPool.NodeUtility nodeUtility;
    private CustomThreadPool customThreadPool;

    public Worker(Runnable taskAllocated, String workerName, CustomThreadPool.NodeUtility nodeUtility, CustomThreadPool customThreadPool) {
        this.taskAllocated = taskAllocated;
        this.workerName = workerName;
        this.runningThread = new Thread(this, this.workerName);
        this.nodeUtility = nodeUtility;
        this.customThreadPool = customThreadPool;
    }

    public Thread getRunningThread() {
        return runningThread;
    }

    @Override
    public void run() {
        boolean stoppedAbruptly = true;
        try {
            while (taskAllocated != null || (taskAllocated = nodeUtility.removeNode().getTask()) != null) {
                System.out.println(this.workerName + " going to trigger task !!");
                try {
                    taskAllocated.run();
                } catch (Exception e) {
                    System.out.println("Exception during execution of the task by worker " + this.workerName);
                    throw e;
                } finally {
                    taskAllocated = null;
                }
            }
            stoppedAbruptly = false;
        } finally {
            try {

            } catch (Exception e) {
                if (stoppedAbruptly)
                System.out.println(this.workerName + " stopped abruptly !!");
            }
            System.out.println(this.workerName + " heading towards exit !!");
            customThreadPool.removeWorker(this);
        }
    }
}
