package microsoft.topologicalSort;

import java.util.*;

public class SingleThreadedCpu {

    public static void main(String ar[]) {
        SingleThreadedCpu unit = new SingleThreadedCpu();
        //int tasks[][] = {{1,2},{2,4},{3,2},{4,1}};

        //int tasks[][] = {{7,10},{7,12},{7,5},{7,4},{7,2}};

        int tasks[][] = {{19,13},{16,9},{21,10},{32,25},{37,4},{49,24},{2,15},{38,41},{37,34},{33,6},{45,4},{18,18},{46,39},{12,24}};

        int order[] = unit.getOrderOne(tasks);
        System.out.print("Order of tasks will be ");
        for (int num : order) {
            System.out.println(num);
        }
    }

    public int[] getOrderOne(int[][] tasks) {

        int result[] = new int[tasks.length];
        int index = 0;

        TreeSet<TaskOne> treeSet = new TreeSet<>((a, b) -> {
            return a.enqueueTime < b.enqueueTime ? -1 : a.enqueueTime > b.enqueueTime ? 1 :
                    a.processingTime < b.processingTime ? -1 : a.processingTime > b.processingTime ? 1 :
                            a.index < b.index ? -1 : a.index > b.index ? 1 : 0;
        });


        for (int i = 0; i < tasks.length; i++) {
            treeSet.add(new TaskOne(i, tasks[i][0], tasks[i][1]));
        }

        TaskOne firstTask = treeSet.first();

        result[index] = firstTask.index;
        index++;

        treeSet.remove(firstTask);

        processToComputeOrderOne(firstTask, treeSet, result, index);
        return result;
    }

    private void processToComputeOrderOne(TaskOne parentTask, TreeSet<TaskOne> treeSet, int[] result, int index) {

        PriorityQueue<TaskOne> minHeap = new PriorityQueue<>((a, b) -> {
            return a.processingTime < b.processingTime ? -1 : a.processingTime > b.processingTime ? 1 :
                            a.index < b.index ? -1 : a.index > b.index ? 1 : 0;
        });

        long currentTime = parentTask.enqueueTime + parentTask.processingTime;

        TaskOne dummyTask = new TaskOne(Integer.MAX_VALUE, currentTime, Integer.MAX_VALUE);
        SortedSet<TaskOne> headSet = treeSet.headSet(dummyTask);

        Set<TaskOne> addedTasks = new HashSet<>();

        for (TaskOne task : headSet) {
            if (!addedTasks.contains(task)) {
                minHeap.add(task);
                addedTasks.add(task);
            }
        }

        while (!minHeap.isEmpty()) {
            TaskOne peekedTask = minHeap.peek();
            result[index] = peekedTask.index;
            index++;
            minHeap.poll();
            treeSet.remove(peekedTask);

            TaskOne dummy = new TaskOne(Integer.MAX_VALUE, currentTime + peekedTask.processingTime, Integer.MAX_VALUE);

            currentTime += peekedTask.processingTime;

            for (TaskOne task : treeSet.headSet(dummy)) {
                if (!addedTasks.contains(task)) {
                    minHeap.add(task);
                    addedTasks.add(task);
                }
            }
        }
    }

    static class TaskOne {
        private int index;
        private long enqueueTime;
        private long processingTime;

        public TaskOne(int index, long enqueueTime, long processingTime) {
            this.index = index;
            this.enqueueTime = enqueueTime;
            this.processingTime = processingTime;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TaskOne task = (TaskOne) o;
            return index == task.index;
        }

        @Override
        public int hashCode() {
            return Objects.hash(index);
        }
    }
}
