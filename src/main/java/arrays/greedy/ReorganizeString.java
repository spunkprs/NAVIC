package arrays.greedy;


import arrays.mergeInterval.taskscheduler.TaskScheduler;

import java.util.*;

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

        //String inputString = "aaaba";

        String inputString = "kkkkzrkatkwpkkkktrq";

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


        /**
         Case when length of the string is even && the max frequent element's frequency is greater than half of the string in that case
         there is no chance we can build the required string
         * */
        if (tasks.length % 2 == 0 && taskWithHighestFrequency.taskFrequency > (tasks.length / 2)) {
            return "";
        } else if (tasks.length % 2 != 0 && taskWithHighestFrequency.taskFrequency > ((tasks.length / 2) + 1)) {
            return "";
        } else {
            return processToFindReorganizedString(maxHeap);
        }
    }

    /**
     Heart of the logic resides here where we are typically making use of greedy algorithm though both the heaps will be storing nodes of same types
     and both of them are used interchangeably
     Making use of two max heaps -->
     * */

    private String processToFindReorganizedString(PriorityQueue<Node> maxHeap) {
        StringBuilder resultantString = new StringBuilder();
        Character currentNode = null;

        PriorityQueue<Node> maxHeap2 = new PriorityQueue<>(new NodeComparator());

        while (!maxHeap.isEmpty() || !maxHeap2.isEmpty()) {
            Node nodePulled = null;
            if (maxHeap2.isEmpty()) {
                nodePulled = maxHeap.poll();
                currentNode = nodePulled.task;
                resultantString.append(nodePulled.task);
                nodePulled.taskFrequency--;
                if (nodePulled.taskFrequency >= 1) {
                    maxHeap2.add(nodePulled);
                }
            } else if (!maxHeap.isEmpty() && !maxHeap2.isEmpty()) {
                nodePulled = maxHeap.peek();
                if (maxHeap2.peek().task.compareTo(currentNode) != 0) {
                    if (maxHeap2.peek().taskFrequency > nodePulled.taskFrequency) {
                        nodePulled = maxHeap2.poll();
                        maxHeap.add(nodePulled);
                    } else {
                        nodePulled = maxHeap.poll();
                        currentNode = nodePulled.task;
                        resultantString.append(nodePulled.task);
                        nodePulled.taskFrequency--;
                        if (nodePulled.taskFrequency >= 1) {
                            maxHeap2.add(nodePulled);
                        }
                    }
                } else if (maxHeap2.peek().task.compareTo(currentNode) == 0) {
                    nodePulled = maxHeap.poll();
                    currentNode = nodePulled.task;
                    resultantString.append(nodePulled.task);
                    nodePulled.taskFrequency--;
                    if (nodePulled.taskFrequency >= 1) {
                        maxHeap2.add(nodePulled);
                    }
                }
            } else if (maxHeap.isEmpty() && !maxHeap2.isEmpty()) {
                if (maxHeap2.peek().task.compareTo(currentNode) != 0) {
                    nodePulled = maxHeap2.poll();
                    maxHeap.add(nodePulled);
                } else if (maxHeap2.peek().task.compareTo(currentNode) == 0 && maxHeap2.size() == 1) {
                    resultantString = new StringBuilder();
                    break;
                } else if (maxHeap2.peek().task.compareTo(currentNode) == 0 && maxHeap2.size() > 1) {
                    nodePulled = maxHeap2.poll();
                    maxHeap = maxHeap2;
                    maxHeap2 = new PriorityQueue<>(new NodeComparator());
                    maxHeap2.add(nodePulled);
                }
            }
        }
        return resultantString.toString();
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
