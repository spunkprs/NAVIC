package arrays.mergeInterval;

import java.util.*;

/**
 You are given an array of CPU tasks represented by uppercase letters (A to Z) and an integer n, which denotes the cooling period
 required between any two identical tasks. Each task takes exactly one CPU interval to execute. Therefore, each CPU interval can
 either perform a task or remain idle. Tasks can be executed in any order, but the same task must be separated by at least n intervals.

 Determine the minimum number of CPU intervals required to complete all tasks.

 Constraints:

 a.) 1≤ tasks.length ≤1000
 b.) 0 ≤ n ≤100

 tasks consists of uppercase English letters

 References --> https://www.educative.io/interview-prep/coding/task-scheduler
 Credits --> Educative
* */

public class TaskScheduler {

    public static void main(String ar[]) {
        String command = "AAABBCC";
        int coolingPeriod = 1;

        TaskScheduler taskScheduler = new TaskScheduler();
        int result = taskScheduler.leastInterval(command.toCharArray(), coolingPeriod);
        System.out.println("Minimum number of CPU intervals to run commands " + command +
                " having cooling off period for each task of " + coolingPeriod + " is " + result);

    }

    public int leastInterval(char[] tasks, int n) {
        Map<Character, Task> map = new HashMap<>();
        Set<Task> exploredTasks = new HashSet<>();
        List<Task> intermediateList = new ArrayList<>();

        //iterate over tasks provided && set task start && end time
        for (int i = 0; i < tasks.length; i++) {
            if (!map.containsKey(tasks[i])) {
                if (map.size() == 0) {
                    Task newAssignedTask = new Task(1, 2);
                    map.put(tasks[i], newAssignedTask);
                    intermediateList.add(newAssignedTask);
                    exploredTasks.add(newAssignedTask);
                } else {
                    int sizeOfMap = map.size();
                    Task newAssignedTask = new Task(sizeOfMap + 1, sizeOfMap + 2);
                    generateLatestTask(newAssignedTask, map, intermediateList, exploredTasks, tasks[i]);
                }
            } else {
                if (n != 0) {
                    Task assignedTask = map.get(tasks[i]);
                    Task newAssignedTask = new Task(assignedTask.endTime + n, assignedTask.endTime + n + 1);
                    map.put(tasks[i], newAssignedTask);
                    intermediateList.add(newAssignedTask);
                    exploredTasks.add(newAssignedTask);
                } else {
                    Task lastInsertedTask = intermediateList.get(intermediateList.size() - 1);
                    Task newAssignedTask = new Task(lastInsertedTask.endTime , lastInsertedTask.endTime + 1);
                    map.put(tasks[i], newAssignedTask);
                    intermediateList.add(newAssignedTask);
                    exploredTasks.add(newAssignedTask);
                }
            }
        }

        // Sort Task in ascending order by startTime of the task
        Collections.sort(intermediateList);
        return intermediateList.size() + findGapsInTheTaskScheduling(intermediateList);
    }

    private int findGapsInTheTaskScheduling(List<Task> intermediateList) {
        int result = 0;
        for (int i = 0; i < intermediateList.size() - 1; i++) {
            if (intermediateList.get(i).endTime != intermediateList.get(i + 1).startTime) {
                result += intermediateList.get(i + 1).startTime - intermediateList.get(i).endTime;
            }
        }
        return result;
    }

    private void generateLatestTask(Task newAssignedTask, Map<Character, Task> map, List<Task> intermediateList,
                                    Set<Task> exploredTasks, Character command) {
        while (exploredTasks.contains(newAssignedTask)) {
            newAssignedTask = new Task(newAssignedTask.startTime + 1, newAssignedTask.endTime + 1);
        }
        map.put(command, newAssignedTask);
        intermediateList.add(newAssignedTask);
        exploredTasks.add(newAssignedTask);
    }

    static class Task implements Comparable<Task> {
        private int startTime;
        private int endTime;

        public Task(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Task task = (Task) o;
            return startTime == task.startTime && endTime == task.endTime;
        }

        @Override
        public int hashCode() {
            return Objects.hash(startTime, endTime);
        }

        @Override
        public int compareTo(Task object) {
            return this.startTime - object.startTime;
        }
    }
}
