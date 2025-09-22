package graph;

import java.util.*;

/**
 You are given an m x n integer matrix grid where each cell is either 0 (empty) or 1 (obstacle). You can move up, down, left,
 or right from and to an empty cell in one step.

 Return the minimum number of steps to walk from the upper left corner (0, 0) to the lower right corner (m - 1, n - 1)
 given that you can eliminate at most k obstacles.
 If it is not possible to find such walk return -1.


Constraints:

1.) m == grid.length
2.) n == grid[i].length
3.) 1 <= m, n <= 40
4.) 1 <= k <= m * n
5.) grid[i][j] is either 0 or 1.
6.) grid[0][0] == grid[m - 1][n - 1] == 0

Source : LeetCode
Level : HARD

 * */

public class ShortestPathInGridWithObstaclesElimination {

    public static void main(String ar[]) {
        ShortestPathInGridWithObstaclesElimination unit = new ShortestPathInGridWithObstaclesElimination();
        int grid [][] = {{0,0,0},{1,1,0},{0,0,0},{0,1,1},{0,0,0}};
        int k = 1;

        System.out.print("Shortest distance from source to destination with obstacles removal count " + k + " allowed is " +
                unit.shortestPath(grid, k));
    }

    public int shortestPath(int[][] grid, int k) {
        int minDistanceToDestination = Integer.MAX_VALUE;
        Set<Node> visitedNodes = new HashSet<>();
        Node startNode = new Node(0, 0);

        Queue<Node> queue = new LinkedList<>();
        queue.add(startNode);
        visitedNodes.add(startNode);

        while (!queue.isEmpty()) {
            Node peekedNode = queue.peek();
            if (peekedNode.xCoordinate == grid.length - 1 && peekedNode.yCoordinate == grid[0].length - 1) {
                minDistanceToDestination = peekedNode.distanceCovered < minDistanceToDestination ?
                        peekedNode.distanceCovered : minDistanceToDestination;
            }
            for (Node childNode : fetchChildNodes(peekedNode, visitedNodes, grid, k)) {
                visitedNodes.add(childNode);
                queue.add(childNode);
            }
            queue.poll();
        }
        return minDistanceToDestination == Integer.MAX_VALUE ? -1 : minDistanceToDestination;
    }

    private List<Node> fetchChildNodes(Node peekedNode, Set<Node> visitedNodes, int[][] grid, int allowedObstacles) {
        int xCoordinate = peekedNode.xCoordinate;
        int yCoordinate = peekedNode.yCoordinate;
        List<Node> childNodes = new ArrayList<>();

        if (xCoordinate - 1 >= 0) {
            if (grid[xCoordinate - 1][yCoordinate] == 0) {
                Node childNode = new Node(xCoordinate - 1, yCoordinate);
                childNode.distanceCovered = peekedNode.distanceCovered + 1;
                childNode.obstaclesPassed = peekedNode.obstaclesPassed;
                if (!visitedNodes.contains(childNode)) {
                    childNodes.add(childNode);
                }
            } else {
                if (peekedNode.obstaclesPassed + 1 <= allowedObstacles) {
                    Node childNode = new Node(xCoordinate - 1, yCoordinate);
                    childNode.distanceCovered = peekedNode.distanceCovered + 1;
                    childNode.obstaclesPassed = peekedNode.obstaclesPassed + 1;
                    if (!visitedNodes.contains(childNode)) {
                        childNodes.add(childNode);
                    }
                }
            }
        }

        if (yCoordinate - 1 >= 0) {
            if (grid[xCoordinate][yCoordinate - 1] == 0) {
                Node childNode = new Node(xCoordinate, yCoordinate - 1);
                childNode.distanceCovered = peekedNode.distanceCovered + 1;
                childNode.obstaclesPassed = peekedNode.obstaclesPassed;
                if (!visitedNodes.contains(childNode)) {
                    childNodes.add(childNode);
                }
            } else {
                if (peekedNode.obstaclesPassed + 1 <= allowedObstacles) {
                    Node childNode = new Node(xCoordinate, yCoordinate - 1);
                    childNode.distanceCovered = peekedNode.distanceCovered + 1;
                    childNode.obstaclesPassed = peekedNode.obstaclesPassed + 1;
                    if (!visitedNodes.contains(childNode)) {
                        childNodes.add(childNode);
                    }
                }
            }
        }

        if (xCoordinate + 1 < grid.length) {
            if (grid[xCoordinate + 1][yCoordinate] == 0) {
                Node childNode = new Node(xCoordinate + 1, yCoordinate);
                childNode.distanceCovered = peekedNode.distanceCovered + 1;
                childNode.obstaclesPassed = peekedNode.obstaclesPassed;
                if (!visitedNodes.contains(childNode)) {
                    childNodes.add(childNode);
                }
            } else {
                if (peekedNode.obstaclesPassed + 1 <= allowedObstacles) {
                    Node childNode = new Node(xCoordinate + 1, yCoordinate);
                    childNode.distanceCovered = peekedNode.distanceCovered + 1;
                    childNode.obstaclesPassed = peekedNode.obstaclesPassed + 1;
                    if (!visitedNodes.contains(childNode)) {
                        childNodes.add(childNode);
                    }
                }
            }
        }

        if (yCoordinate + 1 < grid[0].length) {
            if (grid[xCoordinate][yCoordinate + 1] == 0) {
                Node childNode = new Node(xCoordinate, yCoordinate + 1);
                childNode.distanceCovered = peekedNode.distanceCovered + 1;
                childNode.obstaclesPassed = peekedNode.obstaclesPassed;
                if (!visitedNodes.contains(childNode)) {
                    childNodes.add(childNode);
                }
            } else {
                if (peekedNode.obstaclesPassed + 1 <= allowedObstacles) {
                    Node childNode = new Node(xCoordinate, yCoordinate + 1);
                    childNode.distanceCovered = peekedNode.distanceCovered + 1;
                    childNode.obstaclesPassed = peekedNode.obstaclesPassed + 1;
                    if (!visitedNodes.contains(childNode)) {
                        childNodes.add(childNode);
                    }
                }
            }
        }
        return childNodes;
    }

    static class Node {
        private int xCoordinate;
        private int yCoordinate;
        private int distanceCovered;
        private int obstaclesPassed;

        public Node(int xCoordinate, int yCoordinate) {
            this.xCoordinate = xCoordinate;
            this.yCoordinate = yCoordinate;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return xCoordinate == node.xCoordinate && yCoordinate == node.yCoordinate && obstaclesPassed == node.obstaclesPassed;
        }

        @Override
        public int hashCode() {
            return Objects.hash(xCoordinate, yCoordinate, obstaclesPassed);
        }
    }
}
