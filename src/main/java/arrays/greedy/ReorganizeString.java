package arrays.greedy;


import arrays.mergeInterval.taskscheduler.TaskScheduler;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.

 Return any possible rearrangement of s or return "" if not possible.

 Example 1:
 Input: s = "aab"
 Output: "aba"

 Example 2:
 Input: s = "aaab"
 Output: ""

 Example 3:
 Input: s = "abbccbb"
 Output: "bcbcbab"

 Constraints:-

 a.) 1 <= s.length <= 500
 b.) s consists of lowercase English letters.

 * */

public class ReorganizeString {

    public static void main(String ar[]) {
        ReorganizeString unit = new ReorganizeString();
        String inputString = "aaaba";
        System.out.println("Reorganized string such that no two adjacent characters are same for input string " + inputString
                + " is " + unit.reorganizeString(inputString));
    }

    public String reorganizeString(String s) {

        char tasks[] = s.toCharArray();

        if (tasks.length == 1) {
            return s;
        }

        Map<Character, Node> cache = findFrequencyOfEachTask(tasks);

        PriorityQueue<Node> maxHeap = new PriorityQueue<>(new NodeComparator());
        pushElementsInsideMaxHeap(cache, maxHeap);

        Node taskWithHighestFrequency = maxHeap.peek();

        if (tasks.length % 2 == 0 && taskWithHighestFrequency.taskFrequency > (tasks.length / 2)) {
            return "";
        } else if (tasks.length % 2 != 0 && taskWithHighestFrequency.taskFrequency > ((tasks.length / 2) + 1)) {
            return "";
        } else {

        }

        return null;
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
