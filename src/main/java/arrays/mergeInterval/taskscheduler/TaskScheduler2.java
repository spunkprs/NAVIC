package arrays.mergeInterval.taskscheduler;

import java.util.HashMap;
import java.util.Map;

/**
 You are given a 0-indexed array of positive integers tasks, representing tasks that need to be completed in order,
 where tasks[i] represents the type of the ith task.

 You are also given a positive integer space, which represents the minimum number of days that must pass after the
 completion of a task before another task of the same type can be performed.

 Each day, until all tasks have been completed, you must either:

 a.) Complete the next task from tasks, or
 b.) Take a break.
 Return the minimum number of days needed to complete all tasks.


 Example 1:

 Input: tasks = [1,2,1,2,3,1], space = 3
 Output: 9
 Explanation:
 One way to complete all tasks in 9 days is as follows:
 Day 1: Complete the 0th task
 Day 2: Complete the 1st task
 Day 3: Take a break
 Day 4: Take a break
 Day 5: Complete the 2nd task
 Day 6: Complete the 3rd task
 Day 7: Complete the 4th task
 Day 8: Take a break
 Day 9: Complete the 5th task
 It can be shown that the tasks cannot be completed in less than 9 days


 Example 2:

 Input: tasks = [5,8,8,5], space = 2
 Output: 6
 Explanation:
 One way to complete all tasks in 6 days is as follows:
 Day 1: Complete the 0th task
 Day 2: Complete the 1st task
 Day 3: Take a break
 Day 4: Take a break
 Day 5: Complete the 2nd task
 Day 6: Complete the 3rd task
 It can be shown that the tasks cannot be completed in less than 6 days


References --> https://leetcode.com/problems/task-scheduler-ii/description/
Credits --> LeetCode
 * */

public class TaskScheduler2 {

    public static void main(String ar[]) {
        TaskScheduler2 unit = new TaskScheduler2();
        /*int tasks[] = {5, 8, 8, 5};
        System.out.println("Minimum number of days needed to complete all tasks is " + unit.taskSchedulerII(tasks, 2));*/

        int tasks[] = {1, 2, 1, 2, 3, 1};
        System.out.println("Minimum number of days needed to complete all tasks is " + unit.taskSchedulerII(tasks, 3));
    }

    public long taskSchedulerII(int[] tasks, int space) {
        return processToComputeAllTask(tasks, space);
    }

    /**
    Logic to compute minimum number of days required to complete all tasks
    * */
    private long processToComputeAllTask(int[] tasks, int space) {
        long minimumDays = 0;
        Map<Integer, Long> cache = new HashMap<>();

        for (int i = 0; i < tasks.length; i++) {
            if (!cache.containsKey(tasks[i])) {
                cache.put(tasks[i], minimumDays + 1);
                minimumDays = updateMinimumDays(minimumDays, minimumDays + 1);
            } else {
                long day = cache.get(tasks[i]);
                long nextOccurenceDay = day + space + 1;
                if (minimumDays + 1 >= nextOccurenceDay) {
                    cache.put(tasks[i], minimumDays + 1);
                    minimumDays = updateMinimumDays(minimumDays, minimumDays + 1);
                } else {
                    cache.put(tasks[i], nextOccurenceDay);
                    minimumDays = updateMinimumDays(minimumDays, nextOccurenceDay);
                }
            }
        }
        return minimumDays;
    }

    private long updateMinimumDays(long minimumDays, long days) {
        return days > minimumDays ? days : minimumDays;
    }
}
