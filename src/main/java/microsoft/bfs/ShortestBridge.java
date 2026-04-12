package microsoft.bfs;

import java.util.*;

/**
Problem : 934
You are given an n x n binary matrix grid where 1 represents land and 0 represents water.

An island is a 4-directionally connected group of 1's not connected to any other 1's. There are exactly two islands in grid.

You may change 0's to 1's to connect the two islands to form one island.

Return the smallest number of 0's you must flip to connect the two islands.


 * */

public class ShortestBridge {

    private Map<NodeOne, Integer> mapOne = new HashMap<>();
    private Set<NodeTwo> set = new HashSet<>();
    private boolean doneWithPopulating = false;
    private boolean shallContinue = true;
    private int minDistance = Integer.MAX_VALUE;

    public static void main(String ar[]) {
        ShortestBridge unit = new ShortestBridge();
        int grid[][] = {{0, 1, 0}, {0, 0, 0}, {0,0,1}};
        System.out.println("Shortest bridge length is " + unit.shortestBridge(grid));
    }

    public int shortestBridge(int[][] grid) {
        prepareElementsFromIslandOne(grid);
        processToFetchShortestBride(grid);
        return minDistance;
    }

    private void processToFetchShortestBride(int[][] grid) {
        Queue<NodeTwo> queue = new LinkedList<>();
        for (NodeOne node : mapOne.keySet()) {
            NodeTwo visitedNode = new NodeTwo(node.x, node.y, 0);
            queue.add(visitedNode);
            set.add(visitedNode);
        }

        while (!queue.isEmpty() && shallContinue) {
            NodeTwo peekedNode = queue.peek();
            for (NodeTwo child : fetchChildrenTwo(peekedNode.x, peekedNode.y, grid, peekedNode)) {
                if (grid[child.x][child.y] == 1) {
                    minDistance = child.distance - 1 < minDistance ? child.distance - 1: minDistance;
                    shallContinue = false;
                    break;
                } else {
                    queue.add(child);
                    set.add(child);
                }
            }
            queue.poll();
        }
    }

    private void prepareElementsFromIslandOne(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    mapOne.put(new NodeOne(i, j), 1);
                    processToPrepareElementsFromIslandOne(i, j, grid);
                    doneWithPopulating = true;
                    break;
                }
            }
            if (doneWithPopulating) {
                break;
            }
        }
    }

    private void processToPrepareElementsFromIslandOne(int x, int y, int[][] grid) {
        for (NodeOne child : fetchChildrenOne(x, y, grid)) {
            mapOne.put(child, 1);
            processToPrepareElementsFromIslandOne(child.x, child.y, grid);
        }
    }

    private List<NodeOne> fetchChildrenOne(int x, int y, int[][] grid) {
        List<NodeOne> children = new ArrayList<>();
        if (y + 1 < grid.length && grid[x][y + 1] == 1) {
            NodeOne child = new NodeOne(x, y + 1);
            if (!mapOne.containsKey(child)) {
                children.add(child);
            }
        }

        if (y - 1 >= 0 && grid[x][y - 1] == 1) {
            NodeOne child = new NodeOne(x, y - 1);
            if (!mapOne.containsKey(child)) {
                children.add(child);
            }
        }

        if (x + 1 < grid.length && grid[x + 1][y] == 1) {
            NodeOne child = new NodeOne(x + 1, y);
            if (!mapOne.containsKey(child)) {
                children.add(child);
            }
        }

        if (x - 1 >= 0 && grid[x - 1][y] == 1) {
            NodeOne child = new NodeOne(x - 1, y);
            if (!mapOne.containsKey(child)) {
                children.add(child);
            }
        }

        return children;
    }

    private List<NodeTwo> fetchChildrenTwo(int x, int y, int[][] grid, NodeTwo peekedNode) {
        List<NodeTwo> children = new ArrayList<>();
        if (y + 1 < grid.length && (grid[x][y + 1] == 0 || grid[x][y + 1] == 1)) {
            NodeTwo child = new NodeTwo(x, y + 1, peekedNode.distance + 1);
            if (!set.contains(child)) {
                children.add(child);
            }
        }

        if (y - 1 >= 0 && (grid[x][y - 1] == 0 || grid[x][y - 1] == 1)) {
            NodeTwo child = new NodeTwo(x, y - 1, peekedNode.distance + 1);
            if (!set.contains(child)) {
                children.add(child);
            }
        }

        if (x + 1 < grid.length && (grid[x + 1][y] == 0 || grid[x + 1][y] == 1)) {
            NodeTwo child = new NodeTwo(x + 1, y, peekedNode.distance + 1);
            if (!set.contains(child)) {
                children.add(child);
            }
        }

        if (x - 1 >= 0 && (grid[x - 1][y] == 0 || grid[x - 1][y] == 1)) {
            NodeTwo child = new NodeTwo(x - 1, y, peekedNode.distance + 1);
            if (!set.contains(child)) {
                children.add(child);
            }
        }

        return children;
    }

    static class NodeOne {
        private int x;
        private int y;

        public NodeOne(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            NodeOne nodeOne = (NodeOne) o;
            return x == nodeOne.x && y == nodeOne.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    static class NodeTwo {
        private int x;
        private int y;
        private int distance;

        public NodeTwo(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            NodeTwo nodeOne = (NodeTwo) o;
            return x == nodeOne.x && y == nodeOne.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }


}
