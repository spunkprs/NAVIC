package lld.jobScheduler.dispatcher;

import lld.jobScheduler.model.JobRun;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;

/**
This process is responsible for pulling task out of DelayedQueue && pushing it to ExecutorService where individual worker threads
will take care of running submitted tasks

Have made this dispatcher process light weight i.e could have multiple dispatcher process to poll data from DelayedQueue && get the polled
task run in it's thread but with that approach chances of a task getting delayed is pretty high because in case few payload execution takes time
in that case take() method call will be delayed hence task delay will be introduced

To avoid it responsibility of running individual task/payload is given to ExecutorService instead [chances of task/payload getting rejected is
definitely there in case tasks getting pushed to the ES is way more than worker threads spun by ES]
 * */

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
