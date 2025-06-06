package arrays.mergeInterval.taskscheduler;

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
        return 0;
    }
}
