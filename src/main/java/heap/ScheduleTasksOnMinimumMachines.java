package heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class ScheduleTasksOnMinimumMachines {

    public static void main(String ar[]) {
        ScheduleTasksOnMinimumMachines unit = new ScheduleTasksOnMinimumMachines();
    }

    public int minimumMachines(int[][] tasks) {
        List<Integer> startTimes = new ArrayList<>();
        PriorityQueue<Integer> priorityQueueForEndTimes = new PriorityQueue<>();

        for (int i = 0; i < tasks.length; i++) {
            startTimes.add(tasks[i][0]);
            priorityQueueForEndTimes.add(tasks[i][1]);
        }

        Collections.sort(startTimes);

        return processToComputeMinimumMachines(startTimes, priorityQueueForEndTimes);
    }

    private int processToComputeMinimumMachines(List<Integer> startTimes, PriorityQueue<Integer> priorityQueueForEndTimes) {
        int startIndex = 0;
        int minimumMachinesNeeded = 0;
        int machinesNeeded = 0;

        while (startIndex < startTimes.size()) {
            int minEndTime = priorityQueueForEndTimes.peek();

            if (startTimes.get(startIndex) < minEndTime) {
                ++machinesNeeded;
                minimumMachinesNeeded = machinesNeeded > minimumMachinesNeeded ? machinesNeeded : minimumMachinesNeeded;
                startIndex++;
            } else if (startTimes.get(startIndex) >= minEndTime) {
                priorityQueueForEndTimes.poll();
                --machinesNeeded;
            }
        }
        return minimumMachinesNeeded;
    }
}
