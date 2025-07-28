package heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 You are given two 0-indexed integer arrays jobs and workers of equal length,
 where jobs[i] is the amount of time needed to complete the ith job,
 and workers[j] is the amount of time the jth worker can work each day.

 Each job should be assigned to exactly one worker, such that each worker completes exactly one job.

 Return the minimum number of days needed to complete all the jobs after assignment.

 Constraints:

 1.) n == jobs.length == workers.length
 2.) 1 <= n <= pow(10,5)
 3.) 1 <= jobs[i], workers[i] <= pow(10,5)

 Source : Leetcode

 * */

public class FindMinimumTimeToFinishAllJobs {

    public static void main(String ar[]) {
        FindMinimumTimeToFinishAllJobs unit = new FindMinimumTimeToFinishAllJobs();
        int jobs[] = {3,18,15,9};
        int workers[] = {6,5,1,3};

        System.out.println("Minimum time required to finish all jobs : " + unit.minimumTime(jobs, workers));
    }

    public int minimumTime(int[] jobs, int[] workers) {

        int minimumTime = 0;

        PriorityQueue<Integer> maxHeapOne = new PriorityQueue<>(new JobComparator());
        PriorityQueue<Integer> maxHeapTwo = new PriorityQueue<>(new JobComparator());

        populateHeap(maxHeapOne, jobs);
        populateHeap(maxHeapTwo, workers);

        while (!maxHeapOne.isEmpty()) {
            int job = maxHeapOne.peek();
            int workerHours = maxHeapTwo.peek();

            if (workerHours >= job) {
                minimumTime = updateMinimumTime(minimumTime, 1);
            } else {
                int remainder = job % workerHours;
                if (remainder == 0) {
                    minimumTime = updateMinimumTime(minimumTime, job / workerHours);
                } else {
                    minimumTime = updateMinimumTime(minimumTime, (job / workerHours) + 1);
                }
            }
            maxHeapOne.poll();
            maxHeapTwo.poll();
        }
        return minimumTime;
    }

    private int updateMinimumTime(int minimumTime, int days) {
        return days > minimumTime ? days : minimumTime;
    }

    private void populateHeap(PriorityQueue<Integer> heap, int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            heap.add(arr[i]);
        }
    }

    static class JobComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2.intValue() - o1.intValue();
        }
    }
}
