package multithreading.jobscheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.PriorityBlockingQueue;

/**
It's major responsibility is to assign those tasks to corresponding workers whose time matches current timestamp
with a configurable allowed delay

Will make use of cache thread pool to run background tasks present inside PriorityBlockingQueue
 * */

public class TaskOrchestrator {

    private PriorityBlockingQueue<Node> priorityBlockingQueue;
    private ExecutorService executorService;
    private long deltaInMillis;

    public TaskOrchestrator(PriorityBlockingQueue<Node> priorityBlockingQueue,
                            ExecutorService executorService, long deltaInMillis) {
        this.priorityBlockingQueue = priorityBlockingQueue;
        this.executorService = executorService;
        this.deltaInMillis = deltaInMillis;
    }

    /**
    This method is responsible for tasks orchestration
     * */
    public void orchestrateTasks() {
            Node peekedNode = null;
            while ((peekedNode = priorityBlockingQueue.peek()) != null) {
                long currentTimeStamp = System.currentTimeMillis();
                if (currentTimeStamp > peekedNode.getTimestamp() &&
                        currentTimeStamp - peekedNode.getTimestamp() <= this.deltaInMillis) {
                    for (Task task : peekedNode.getAssociatedTasks()) {
                        if (!task.isMarkedForRemoval()) {
                            executorService.submit(task);
                            if (task.getNextTask() != null) {
                                Task recurringTask = task.getNextTask();
                                recurringTask.setTaskType(TaskType.RECURRING);
                                Node node = new Node(recurringTask.getRunTimestamp());

                                //Adding recurring task to the blocking queue
                                List<Task> associatedTasks = new ArrayList<>();
                                associatedTasks.add(recurringTask);
                                node.setAssociatedTasks(associatedTasks);
                                priorityBlockingQueue.add(node);
                            }
                        }
                    }
                    priorityBlockingQueue.poll();
                } else if (currentTimeStamp > peekedNode.getTimestamp() &&
                        currentTimeStamp - peekedNode.getTimestamp() > this.deltaInMillis) {
                    //Handle this case by registering an event that this task got delayed
                } else {
                    continue;
                }
            }
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public PriorityBlockingQueue<Node> getPriorityBlockingQueue() {
        return priorityBlockingQueue;
    }
}
