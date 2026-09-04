package lld.jobScheduler.dispatcher;

import lld.jobScheduler.model.JobRun;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;

public class DispatcherProcess {

    private String processId;
    private DelayQueue<JobRun> delayQueue;
    private ExecutorService executorService;

    public DispatcherProcess(String processId, DelayQueue<JobRun> delayQueue,
                             ExecutorService executorService) {
        this.processId = processId;
        this.delayQueue = delayQueue;
        this.executorService = executorService;
    }

    public void start() {
        System.out.print("Dispatcher process with id " +  this.processId + " getting started");
        while (!Thread.currentThread().isInterrupted()) {
            try {
               JobRun polledTask = delayQueue.take();
               executorService.submit(polledTask.getAttachedPayload());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
