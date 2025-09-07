package graph;

import java.util.*;

/**
 Link : https://leetcode.com/explore/interview/card/facebook/52/trees-and-graphs/3026/

 You are given an m x n grid grid of values 0, 1, or 2, where:

 1.) each 0 marks an empty land that you can pass by freely,
 2.) each 1 marks a building that you cannot pass through, and
 3.) each 2 marks an obstacle that you cannot pass through.

 You want to build a house on an empty land that reaches all buildings in the shortest total travel distance.
 You can only move up, down, left, and right.

 Return the shortest travel distance for such a house. If it is not possible to build such a house according
 to the above rules, return -1.

 The total travel distance is the sum of the distances between the houses of the friends and the meeting point.


 Constraints:

 1.) m == grid.length
 2.) n == grid[i].length
 3.) 1 <= m, n <= 50
 4.) grid[i][j] is either 0, 1, or 2.
 5.) There will be at least one building in the grid.

 Source : LeetCode

 Time Complexity : O(m * n * k), where k being number of 1's in the grid
 Space Complexity : O(m * n)

 * */
public class ShortestDistanceFromAllBuildingsLeetCode {

    public static void main(String ar[]) {
        int matrix[][] = {{1, 0, 2, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}};
        //int matrix [][] = {{0,2,1},{1,0,2},{0,1,0}};
        ShortestDistanceFromAllBuildingsLeetCode unit = new ShortestDistanceFromAllBuildingsLeetCode();
        System.out.println("Shortest distance from all buildings " + unit.shortestDistanceOne(matrix));
    }

    static class NodeInfo {
        private int x;
        private int y;
        private int distance;

        public NodeInfo(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            NodeInfo nodeInfo = (NodeInfo) o;
            return x == nodeInfo.x && y == nodeInfo.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    static class Pair {
        private int totalDistance;
        private int totalHomes;
    }

    public int shortestDistanceOne(int[][] grid) {
        Map<NodeInfo, Pair> map = new HashMap<>();
        int countOfHomes = fetchNumberOfHomes(grid);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    NodeInfo nodeInfo = new NodeInfo(i, j, 0);
                    processToComputeShortestDistance(nodeInfo, grid, map);
                }
            }
        }
        return map.size() == 0 ? -1 : processToFetchLeastDistance(map, countOfHomes);
    }

    private int fetchNumberOfHomes(int[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    private int processToFetchLeastDistance(Map<NodeInfo, Pair> map, int countOfHomes) {
        int minDistance = Integer.MAX_VALUE;
        for (NodeInfo key : map.keySet()) {
            if (map.get(key).totalHomes == countOfHomes) {
                minDistance = map.get(key).totalDistance < minDistance ? map.get(key).totalDistance : minDistance;
            }
        }
      return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }

    private void processToComputeShortestDistance(NodeInfo nodeInfo, int[][] grid, Map<NodeInfo, Pair> map) {
        Queue<NodeInfo> queue = new LinkedList<>();
        queue.add(nodeInfo);
        Set<NodeInfo> exploredNodes = new HashSet<>();
        while (!queue.isEmpty()) {
            for (NodeInfo child : fetchChildren(queue.peek(), grid, exploredNodes)) {
                queue.add(child);
                if (!map.containsKey(child)) {
                    Pair p = new Pair();
                    p.totalDistance = child.distance;
                    p.totalHomes = 1;
                    map.put(child, p);
                } else {
                    Pair p = map.get(child);
                    p.totalDistance += child.distance;
                    p.totalHomes += 1;
                }
            }
            queue.poll();
        }
    }

    private List<NodeInfo> fetchChildren(NodeInfo nodeInfo, int[][] grid, Set<NodeInfo> exploredNodes) {
        List<NodeInfo> children = new ArrayList();
        if (nodeInfo.x + 1 < grid.length && grid[nodeInfo.x + 1][nodeInfo.y] == 0) {
            NodeInfo childNode = new NodeInfo(nodeInfo.x + 1, nodeInfo.y, nodeInfo.distance + 1);
            if (!exploredNodes.contains(childNode)) {
                children.add(childNode);
                exploredNodes.add(childNode);
            }
        }

        if (nodeInfo.x - 1 >= 0 && grid[nodeInfo.x - 1][nodeInfo.y] == 0) {
            NodeInfo childNode = new NodeInfo(nodeInfo.x - 1, nodeInfo.y, nodeInfo.distance + 1);
            if (!exploredNodes.contains(childNode)) {
                children.add(childNode);
                exploredNodes.add(childNode);
            }
        }

        if (nodeInfo.y - 1 >= 0 && grid[nodeInfo.x][nodeInfo.y - 1] == 0) {
            NodeInfo childNode = new NodeInfo(nodeInfo.x, nodeInfo.y - 1, nodeInfo.distance + 1);
            if (!exploredNodes.contains(childNode)) {
                children.add(childNode);
                exploredNodes.add(childNode);
            }
        }

        if (nodeInfo.y + 1 < grid[0].length && grid[nodeInfo.x][nodeInfo.y + 1] == 0) {
            NodeInfo childNode = new NodeInfo(nodeInfo.x, nodeInfo.y + 1, nodeInfo.distance + 1);
            if (!exploredNodes.contains(childNode)) {
                children.add(childNode);
                exploredNodes.add(childNode);
            }
        }
        return children;
    }
}
