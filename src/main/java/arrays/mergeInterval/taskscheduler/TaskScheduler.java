package arrays.mergeInterval.taskscheduler;

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

        /**
         Case fulfilling condition --> days + 1 == nodePulled.upcomingDay
         * */
        /*String command = "AAABBCC";
        int coolingPeriod = 1;*/


        /**
         Case fulfilling condition --> days + 1 < nodePulled.upcomingDay
         * */
        /*String command = "ACABDB";
        int coolingPeriod = 1;*/


        /**
         Case fulfilling condition --> days + 1 > nodePulled.upcomingDay
        * */
        /*String command = "ABAABC";
        int coolingPeriod = 3;*/

        String command = "BCDAAAAG";
        int coolingPeriod = 1;

        TaskScheduler taskScheduler = new TaskScheduler();
        int result = taskScheduler.leastInterval(command.toCharArray(), coolingPeriod);
        System.out.println("Minimum number of CPU intervals to run commands " + command +
                " having cooling off period for each task of " + coolingPeriod + " is " + result);
    }

    /**
     Logic to find least interval
     */
    public int leastInterval(char[] tasks, int n) {
        if (n > 0) {
            Map<Character, Node> cache = findFrequencyOfEachTask(tasks);
            PriorityQueue<Node> maxHeap = new PriorityQueue<>(new NodeComparator());
            pushElementsInsideMaxHeap(cache, maxHeap);

            return logicToFindLeastInterval(maxHeap, n);
        } else {
            return tasks.length;
        }
    }

    /**
     Core logic to find least interval
    */
    private int logicToFindLeastInterval(PriorityQueue<Node> maxHeap, int space) {
        int days = 0;
        Queue<Node> queue = new LinkedList<>();
        while (!maxHeap.isEmpty() || !queue.isEmpty()) {
            Node nodePulled = null;
            if (!maxHeap.isEmpty()) {
                if (queue.isEmpty()) {
                    nodePulled = maxHeap.poll();
                    days++;
                    nodePulled.upcomingDay = days + space + 1;
                    nodePulled.taskFrequency--;
                    if (nodePulled.taskFrequency >= 1) {
                        queue.add(nodePulled);
                    }
                } else {
                    nodePulled = maxHeap.peek();
                    Node nodeFromQueue = queue.peek();
                    if (days + 1 >= nodeFromQueue.upcomingDay && nodeFromQueue.taskFrequency > nodePulled.taskFrequency) {
                        nodeFromQueue = queue.poll();
                        maxHeap.add(nodeFromQueue);
                    }
                    nodePulled = maxHeap.poll();
                    days++;
                    nodePulled.upcomingDay = days + space + 1;
                    nodePulled.taskFrequency--;
                    if (nodePulled.taskFrequency >= 1) {
                        queue.add(nodePulled);
                    }
                }
            } else {
                nodePulled = queue.peek();
                if (days + 1 >= nodePulled.upcomingDay) {
                    nodePulled = queue.poll();
                    maxHeap.add(nodePulled);
                } else if (days + 1 < nodePulled.upcomingDay) {
                    days = nodePulled.upcomingDay - 1;
                }
            }
        }
        return days;
    }

    private void pushElementsInsideMaxHeap(Map<Character, Node> cache, PriorityQueue<Node> maxHeap) {
        for (Character key : cache.keySet()) {
            maxHeap.add(cache.get(key));
        }
    }

    private Map<Character, Node> findFrequencyOfEachTask(char[] tasks) {
        Map<Character, Node> cache = new HashMap<>();
        for (int i = 0; i < tasks.length; i++) {
            if (!cache.containsKey(tasks[i])) {
                cache.put(tasks[i], new Node(tasks[i], 1));
            } else {
                Node existingTask = cache.get(tasks[i]);
                existingTask.taskFrequency = existingTask.taskFrequency + 1;
            }
        }
        return cache;
    }

    static class Node {
        private Character task;
        private int taskFrequency;
        private int upcomingDay;

        public Node(Character task, int taskFrequency) {
            this.task = task;
            this.taskFrequency = taskFrequency;
        }

    }

    static class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o2.taskFrequency - o1.taskFrequency;
        }
    }
}
