package graph;

import java.util.*;

/**
 You are given an m x n binary matrix grid. An island is a group of 1's (representing land)
 connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

 The area of an island is the number of cells with a value 1 in the island.

 Return the maximum area of an island in grid. If there is no island, return 0.

 * */

public class MaxAreaOfIsland {

    private int intermittentResult = 0;


    public static void main(String ar[]) {
        MaxAreaOfIsland unit = new MaxAreaOfIsland();
        int grid[][] = {{1, 1, 0, 1, 0}, {0, 0, 0, 1, 1}, {1, 1, 0, 1, 1}, {1, 1, 0, 0, 0}};
        System.out.println("Maximum area of an island is " + unit.maxAreaOfIsland(grid));
    }

    public int maxAreaOfIsland(int[][] grid) {
        Set<Node> visitedNodes = new HashSet<>();
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    Node node = new Node(i, j);
                    if (!visitedNodes.contains(node)) {
                        visitedNodes.add(node);
                        intermittentResult = 1;
                        processToFindMaxArea(node, grid, visitedNodes);
                        result = updateResult(result);
                        intermittentResult = 0;
                    }
                }
            }
        }
        return result;
    }

    private int updateResult(int result) {
        return result > intermittentResult ? result : intermittentResult;
    }

    private void processToFindMaxArea(Node node, int[][] grid, Set<Node> visitedNodes) {
        List<Node> childNodes = findChildren(node, grid, visitedNodes);
        intermittentResult += childNodes.size();
        for (Node childNode : childNodes) {
            processToFindMaxArea(childNode, grid, visitedNodes);
        }
    }

    private List<Node> findChildren(Node node, int[][] grid, Set<Node> visitedNodes) {
        List<Node> children = new ArrayList<>();
        if (node.x + 1 < grid.length && grid[node.x + 1][node.y] == 1) {
            Node nodeOne = new Node(node.x + 1, node.y);
            addToChildren(nodeOne, visitedNodes, children);
        }

        if (node.x - 1 >= 0 && grid[node.x - 1][node.y] == 1) {
            Node nodeOne = new Node(node.x - 1, node.y);
            addToChildren(nodeOne, visitedNodes, children);
        }

        if (node.y + 1 < grid[0].length && grid[node.x][node.y + 1] == 1) {
            Node nodeOne = new Node(node.x, node.y + 1);
            addToChildren(nodeOne, visitedNodes, children);
        }

        if (node.y - 1 >= 0 && grid[node.x][node.y - 1] == 1) {
            Node nodeOne = new Node(node.x, node.y - 1);
            addToChildren(nodeOne, visitedNodes, children);
        }
        return children;
    }

    private void addToChildren(Node node, Set<Node> visitedNodes, List<Node> children) {
        if (!visitedNodes.contains(node)) {
            children.add(node);
            visitedNodes.add(node);
        }
    }

    static class Node {
        private int x;
        private int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return x == node.x && y == node.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

}
