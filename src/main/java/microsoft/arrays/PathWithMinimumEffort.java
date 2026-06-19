package microsoft.arrays;

import java.util.*;

public class PathWithMinimumEffort {

    public static void main(String ar[]) {
        PathWithMinimumEffort unit = new PathWithMinimumEffort();
        int matrix[][] = {{1,2,1,1,1},{1,2,1,2,1},{1,2,1,2,1},{1,2,1,2,1},{1,1,1,2,1}};
        //int matrix[][] = {{1,10,6,7,9,10,4,9}};
        //int matrix [][] = {{10,8},{10,8},{1,2},{10,3},{1,3},{6,3},{5,2}};

        System.out.print("Minimum effort path is " + unit.minimumEffortPath(matrix));
    }

    public int minimumEffortPath(int[][] heights) {
        return processToComputeMinimumEffortOne(heights);
    }

    private int processToComputeMinimumEffortOne(int[][] heights) {
        int minResult = Integer.MAX_VALUE;
        Set<Node> visitedNodes = new HashSet<>();
        PriorityQueue<Node> queue = new PriorityQueue<>((a, b) -> a.computedValue < b.computedValue ? -1 : a.computedValue > b.computedValue ? 1 : 0);

        Node initialNode = new Node(0, 0, 0);
        queue.add(initialNode);

        while (!queue.isEmpty()) {
            Node peekedNode = queue.peek();
            List<Node> childNodes = fetchChildNodesOne(peekedNode, heights, visitedNodes);
            queue.poll();
            visitedNodes.add(peekedNode);

            for (Node childNode : childNodes) {
                if (childNode.xValue != heights.length - 1 || childNode.yValue != heights[0].length - 1) {
                    queue.add(childNode);
                } else if (childNode.xValue == heights.length - 1 && childNode.yValue == heights[0].length - 1) {
                    minResult = childNode.computedValue < minResult ? childNode.computedValue : minResult;
                }
            }
        }

        return minResult == Integer.MAX_VALUE ? 0 : minResult;
    }

    private List<Node> fetchChildNodesOne(Node peekedNode, int[][] heights, Set<Node> visitedNodes) {
        List<Node> childNodes = new ArrayList<>();
        if (peekedNode.xValue + 1 < heights.length) {
            Node childNode = new Node(peekedNode.xValue + 1, peekedNode.yValue,
                    Math.abs(heights[peekedNode.xValue][peekedNode.yValue] - heights[peekedNode.xValue + 1][peekedNode.yValue]));
            if (!visitedNodes.contains(childNode)) {
                int computedValue = fetchComputedValue(peekedNode.computedValue,
                        Math.abs(heights[peekedNode.xValue][peekedNode.yValue] - heights[peekedNode.xValue + 1][peekedNode.yValue]));
                childNode.computedValue = computedValue;
                childNodes.add(childNode);
            }
        }

        if (peekedNode.xValue - 1 >= 0) {
            Node childNode = new Node(peekedNode.xValue - 1, peekedNode.yValue,
                    Math.abs(heights[peekedNode.xValue][peekedNode.yValue] - heights[peekedNode.xValue - 1][peekedNode.yValue]));
            if (!visitedNodes.contains(childNode)) {
                int computedValue = fetchComputedValue(peekedNode.computedValue,
                        Math.abs(heights[peekedNode.xValue][peekedNode.yValue] - heights[peekedNode.xValue - 1][peekedNode.yValue]));
                childNode.computedValue = computedValue;
                childNodes.add(childNode);
            }
        }

        if (peekedNode.yValue - 1 >= 0) {
            Node childNode = new Node(peekedNode.xValue, peekedNode.yValue - 1,
                    Math.abs(heights[peekedNode.xValue][peekedNode.yValue] - heights[peekedNode.xValue][peekedNode.yValue - 1]));
            if (!visitedNodes.contains(childNode)) {
                int computedValue = fetchComputedValue(peekedNode.computedValue,
                        Math.abs(heights[peekedNode.xValue][peekedNode.yValue] - heights[peekedNode.xValue][peekedNode.yValue - 1]));
                childNode.computedValue = computedValue;
                childNodes.add(childNode);
            }
        }

        if (peekedNode.yValue + 1 < heights[0].length) {
            Node childNode = new Node(peekedNode.xValue, peekedNode.yValue + 1,
                    Math.abs(heights[peekedNode.xValue][peekedNode.yValue] - heights[peekedNode.xValue][peekedNode.yValue + 1]));
            if (!visitedNodes.contains(childNode)) {
                int computedValue = fetchComputedValue(peekedNode.computedValue,
                        Math.abs(heights[peekedNode.xValue][peekedNode.yValue] - heights[peekedNode.xValue][peekedNode.yValue + 1]));
                childNode.computedValue = computedValue;
                childNodes.add(childNode);
            }
        }

        return childNodes;
    }

    private int fetchComputedValue(int computedValueOne, int computedValueTwo) {
        return computedValueOne > computedValueTwo ? computedValueOne : computedValueTwo;
    }

    static class Node {
        private int xValue;
        private int yValue;
        private int computedValue;

        public Node(int xValue, int yValue, int computedValue) {
            this.xValue = xValue;
            this.yValue = yValue;
            this.computedValue = computedValue;
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
