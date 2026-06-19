package microsoft.arrays;

import java.util.*;

/**
Problem : 1631
Link : https://leetcode.com/problems/path-with-minimum-effort/description/

You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns, where heights[row][col] represents
the height of cell (row, col). You are situated in the top-left cell, (0, 0), and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed). You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.

A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.

Return the minimum effort required to travel from the top-left cell to the bottom-right cell.


Constraints:-
a.) rows == heights.length
b.) columns == heights[i].length
c.) 1 <= rows, columns <= 100
d.) 1 <= heights[i][j] <= 10^6

Information : Classic problem on the lines of application of Dijkstra's Algorithm
Time Complexity = O(E*log(V)) --> O(r*c*log(r*c))
Space Complexity = O(V) --> O(r*c)

 * */

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
