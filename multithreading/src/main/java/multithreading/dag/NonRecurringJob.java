package multithreading.dag;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NonRecurringJob extends Job {

    public NonRecurringJob(int jobId, long runTimestamp) {
        super(jobId, runTimestamp);
    }

    @Override
    public void runAssociatedTasks() {
        List<Task> dependentTasks = super.getDependentTasks();
        int attainSize = dependentTasks.size();
        boolean shallContinue = true;
        int index = 0;
        Set<Integer> visitedTaskIds = new HashSet<>();
        while (index < attainSize && shallContinue) {
            for (Task dependentTask : dependentTasks) {
                if (!visitedTaskIds.contains(dependentTask.getTaskId()) &&
                        dependentTask.getState().equals(TaskState.SUCCESS)) {
                    visitedTaskIds.add(dependentTask.getTaskId());
                    index++;
                } else if (!visitedTaskIds.contains(dependentTask.getTaskId()) &&
                        dependentTask.getState().equals(TaskState.FAILURE)) {
                    shallContinue = false;
                    System.out.println("Job Id " + super.getJobId() + " execution will be stopped because dependent task with id " +
                            dependentTask.getTaskId() + " has failed !!");
                }
            }
        }
        if (shallContinue) {
            System.out.println("Job Id " + super.getJobId() + " has been executed successfully !!");
        }
    }

    @Override
    public Job fetchNextJob() {
        return null;
    }
}
