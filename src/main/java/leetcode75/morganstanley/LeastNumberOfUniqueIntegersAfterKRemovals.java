package leetcode75.morganstanley;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
Problem : 1481
Given an array of integers arr and an integer k. Find the least number of unique integers after removing exactly k elements

Constraints:-

a.) 1 <= arr.length <= 10^5
b.) 1 <= arr[i] <= 10^9
c.) 0 <= k <= arr.length

Time Complexity = O(NLog(N)), where N being number of elements in the array
Space Complexity = O(N)

 * */

public class LeastNumberOfUniqueIntegersAfterKRemovals {

    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        PriorityQueue<Node> priorityQueue = prepareNodes(arr);
        int counter = k;

        while (counter > 0 && !priorityQueue.isEmpty()) {
            Node tpNode = priorityQueue.peek();
            int frequency = tpNode.frequency;

            counter -= frequency;
            if (counter >= 0) {
                priorityQueue.poll();
            }
        }
        return priorityQueue.size();
    }

    private PriorityQueue<Node> prepareNodes(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (!map.containsKey(arr[i])) {
                map.put(arr[i], 1);
            } else {
                map.put(arr[i], map.get(arr[i]) + 1);
            }
        }

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>((a, b) -> {
            return a.frequency < b.frequency ? -1 : a.frequency > b.frequency ? 1 : 0;
        });

        for (int key : map.keySet()) {
            Node node = new Node(map.get(key), key);
            priorityQueue.add(node);
        }
        return priorityQueue;
    }

    static class Node {
        private int frequency;
        private int num;

        public Node(int frequency, int num) {
            this.frequency = frequency;
            this.num = num;
        }
    }
}
