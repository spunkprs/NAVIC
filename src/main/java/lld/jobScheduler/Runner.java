package lld.jobScheduler;

import lld.jobScheduler.dispatcher.DispatcherProcess;
import lld.jobScheduler.model.JobRun;
import lld.jobScheduler.model.Payload;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
It's responsible for setting the input && dependencies && run the dispatcher process

For now JobScheduler can take care only of non recurring task but will extend it to include recurring task as well !!
 * */

public class Runner {

    public static void main(String ar[]) {
        Payload payloadOne = new Payload("Task One");
        Payload payloadTwo = new Payload("Task Two");
        Payload payloadThree = new Payload("Task Three");
        Payload payloadFour = new Payload("Task Four");
        Payload payloadFive = new Payload("Task Five");
        Payload payloadSix = new Payload("Task Six");

        JobRun jobRunOne = new JobRun("1",
                System.currentTimeMillis() + 100000, payloadOne);

        JobRun jobRunTwo = new JobRun("2",
                System.currentTimeMillis() + 101000, payloadTwo);

        JobRun jobRunThree = new JobRun("3",
                System.currentTimeMillis() + 100100, payloadThree);

        JobRun jobRunFour = new JobRun("4",
                System.currentTimeMillis() + 100100, payloadFour);

        JobRun jobRunFive = new JobRun("5",
                System.currentTimeMillis() + 100100, payloadFive);

        JobRun jobRunSix = new JobRun("6",
                System.currentTimeMillis() + 100110, payloadSix);

        List<JobRun> jobsList = Arrays.asList(jobRunOne, jobRunTwo, jobRunThree,
                jobRunFour, jobRunFive, jobRunSix);

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        DelayQueue<JobRun> delayQueue = new DelayQueue<>();
        delayQueue.addAll(jobsList);

        DispatcherProcess dispatcherProcess = new DispatcherProcess("DP1",
                delayQueue,
                executorService);

        dispatcherProcess.start();

    }
}
