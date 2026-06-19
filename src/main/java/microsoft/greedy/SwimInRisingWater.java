package microsoft.greedy;

import java.util.*;

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
