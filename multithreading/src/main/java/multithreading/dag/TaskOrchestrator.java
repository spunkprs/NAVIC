package multithreading.dag;

import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ExecutorService;

public class TaskOrchestrator {

    private Job job;
    private ExecutorService executorService;

    private ConcurrentSkipListSet<Node> exploredTasks;
    private ConcurrentSkipListSet<Node> unExploredTasks;

    public void orchestrateTasks() {
        fetchInitialDependentTasks();
    }

    private List<Task> fetchInitialDependentTasks() {
        return null;
    }
}
