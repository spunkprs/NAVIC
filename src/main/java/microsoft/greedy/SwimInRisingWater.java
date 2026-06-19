package microsoft.greedy;

import java.util.*;

/**
Problem : 778
Link : https://leetcode.com/problems/swim-in-rising-water/description/
You are given an n x n integer matrix grid where each value grid[i][j] represents the elevation at that point (i, j).

It starts raining, and water gradually rises over time. At time t, the water level is t, meaning any cell with elevation less
than equal to t is submerged or reachable.

You can swim from a square to another 4-directionally adjacent square if and only if the elevation of both squares individually are at most t.
You can swim infinite distances in zero time. Of course, you must stay within the boundaries of the grid during your swim.

Return the minimum time until you can reach the bottom right square (n - 1, n - 1) if you start at the top left square (0, 0).


Constraints:-

a.) n == grid.length
b.) n == grid[i].length
c.) 1 <= n <= 50
d.) 0 <= grid[i][j] < n2
e.) Each value grid[i][j] is unique.
 * */

public class SwimInRisingWater {

    public static void main(String ar[]) {
        SwimInRisingWater unit = new SwimInRisingWater();

        int grid[][] = {{0, 1}, {2, 1}};

        System.out.println("Minimum time required is " + unit.swimInWater(grid));
    }

    public int swimInWater(int[][] grid) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>((a, b) -> a.value < b.value ? -1 : a.value > b.value ? 1: 0);
        Set<Node> visitedNodes = new HashSet<>();
        return processToFindMinimumTime(priorityQueue, visitedNodes, grid);
    }

    private int processToFindMinimumTime(PriorityQueue<Node> priorityQueue, Set<Node> visitedNodes, int grid[][]) {
        int minTime = Integer.MAX_VALUE;
        int timeRequired = 0;
        Node startNode = new Node(0, 0, grid[0][0]);
        timeRequired = startNode.value;

        priorityQueue.add(startNode);

        while (!priorityQueue.isEmpty()) {
            Node peekedNode = priorityQueue.peek();
            priorityQueue.poll();
            visitedNodes.add(peekedNode);

            timeRequired = Math.max(timeRequired, peekedNode.value);

            if (peekedNode.xValue == grid.length - 1 && peekedNode.yValue == grid[0].length - 1) {
                minTime = Math.min(minTime, timeRequired);
            } else {
                for (Node childNode : fetchPossibleChildren(peekedNode, grid, visitedNodes)) {
                    priorityQueue.add(childNode);
                }
            }
        }
        return minTime;
    }

    private List<Node> fetchPossibleChildren(Node peekedNode, int[][] grid, Set<Node> visitedNodes) {
        List<Node> childNodes = new ArrayList<>();
        if (peekedNode.xValue + 1 < grid.length) {
            Node childNode = new Node(peekedNode.xValue + 1, peekedNode.yValue, grid[peekedNode.xValue + 1][peekedNode.yValue]);
            if (!visitedNodes.contains(childNode)) {
                childNodes.add(childNode);
            }
        }

        if (peekedNode.xValue - 1 >= 0) {
            Node childNode = new Node(peekedNode.xValue - 1, peekedNode.yValue, grid[peekedNode.xValue - 1][peekedNode.yValue]);
            if (!visitedNodes.contains(childNode)) {
                childNodes.add(childNode);
            }
        }

        if (peekedNode.yValue - 1 >= 0) {
            Node childNode = new Node(peekedNode.xValue, peekedNode.yValue - 1, grid[peekedNode.xValue][peekedNode.yValue - 1]);
            if (!visitedNodes.contains(childNode)) {
                childNodes.add(childNode);
            }
        }

        if (peekedNode.yValue + 1 < grid[0].length) {
            Node childNode = new Node(peekedNode.xValue, peekedNode.yValue + 1, grid[peekedNode.xValue][peekedNode.yValue + 1]);
            if (!visitedNodes.contains(childNode)) {
                childNodes.add(childNode);
            }
        }
        return childNodes;
    }

    static class Node {
        private int xValue;
        private int yValue;
        private int value;

        public Node(int xValue, int yValue, int value) {
            this.xValue = xValue;
            this.yValue = yValue;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return xValue == node.xValue && yValue == node.yValue;
        }

        @Override
        public int hashCode() {
            return Objects.hash(xValue, yValue);
        }
    }
}
