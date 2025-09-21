package graph;

import java.util.*;

/**
 You are starving and you want to eat food as quickly as possible. You want to find the shortest path to arrive at any food cell.

 You are given an m x n character matrix, grid, of these different types of cells:

 '*' is your location. There is exactly one '*' cell.
 '#' is a food cell. There may be multiple food cells.
 'O' is free space, and you can travel through these cells.
 'X' is an obstacle, and you cannot travel through these cells.
 You can travel to any adjacent cell north, east, south, or west of your current location if there is not an obstacle.

 Return the length of the shortest path for you to reach any food cell. If there is no path for you to reach food, return -1.

 Constraints:

1.) m == grid.length
2.) n == grid[i].length
3.) 1 <= m, n <= 200
4.) grid[row][col] is '*', 'X', 'O', or '#'
5.) The grid contains exactly one '*'

Source : LeetCode
 * */

public class ShortestPathToGetFood {

    public static void main(String ar[]) {
        ShortestPathToGetFood unit = new ShortestPathToGetFood();
    }

    public int getFood(char[][] grid) {
        int minDistance = Integer.MAX_VALUE;
        //Find starting point
        Node startNode = fetchStartingPoint(grid);
        boolean shallContinue = false;
        Set<Node> visitedNodes = new HashSet<>();
        visitedNodes.add(startNode);

        Queue<Node> queue = new LinkedList<>();
        queue.add(startNode);

        while (!queue.isEmpty() && !shallContinue) {
            Node peekedNode = queue.peek();
            for (Node childNode : fetchChildNodes(peekedNode, grid, visitedNodes)) {
                if (grid[childNode.coordinateX][childNode.coordinateY] == '#') {
                    shallContinue = true;
                    minDistance = childNode.distance;
                    break;
                } else {
                    queue.add(childNode);
                    visitedNodes.add(childNode);
                }
            }
            queue.poll();
        }
        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }

    private List<Node> fetchChildNodes(Node peekedNode, char[][] grid, Set<Node> visitedNodes) {
        int parentX = peekedNode.coordinateX;
        int parentY = peekedNode.coordinateY;
        List<Node> childNodes = new ArrayList<>();

        if (parentX - 1 >= 0 && grid[parentX - 1][parentY] != 'X') {
            Node childNode = new Node(parentX - 1, parentY);
            childNode.distance = peekedNode.distance + 1;
            if (!visitedNodes.contains(childNode)) {
                childNodes.add(childNode);
            }
        }

        if (parentY - 1 >= 0 && grid[parentX][parentY - 1] != 'X') {
            Node childNode = new Node(parentX, parentY - 1);
            childNode.distance = peekedNode.distance + 1;
            if (!visitedNodes.contains(childNode)) {
                childNodes.add(childNode);
            }
        }

        if (parentX + 1 < grid.length && grid[parentX + 1][parentY] != 'X') {
            Node childNode = new Node(parentX + 1, parentY);
            childNode.distance = peekedNode.distance + 1;
            if (!visitedNodes.contains(childNode)) {
                childNodes.add(childNode);
            }
        }

        if (parentY + 1 < grid[0].length && grid[parentX][parentY + 1] != 'X') {
            Node childNode = new Node(parentX, parentY + 1);
            childNode.distance = peekedNode.distance + 1;
            if (!visitedNodes.contains(childNode)) {
                childNodes.add(childNode);
            }
        }

        return childNodes;
    }

    private Node fetchStartingPoint(char[][] grid) {
        Node result = null;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '*') {
                    result = new Node(i, j);
                    return result;
                }
            }
        }
        return result;
    }

    static class Node {
        private int coordinateX;
        private int coordinateY;
        private int distance;

        public Node(int coordinateX, int coordinateY) {
            this.coordinateX = coordinateX;
            this.coordinateY = coordinateY;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return coordinateX == node.coordinateX && coordinateY == node.coordinateY;
        }

        @Override
        public int hashCode() {
            return Objects.hash(coordinateX, coordinateY);
        }
    }


}
