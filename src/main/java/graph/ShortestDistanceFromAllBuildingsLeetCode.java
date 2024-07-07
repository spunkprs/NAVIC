package graph;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

/*
This problem aims at finding shortest distance from all buildings
Link : https://leetcode.com/explore/interview/card/facebook/52/trees-and-graphs/3026/
Somehow 76/85 test cases are only passing, not sure about it
* */
public class ShortestDistanceFromAllBuildingsLeetCode {
    private int shortestDistanceFromAllBuildings = Integer.MAX_VALUE;

    static class Index {
        private int x;
        private int y;

        public Index(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int hashCode() {
            return this.x * this.y;
        }

        public boolean equals(Object obj) {
            Index index = (Index)obj;
            return (this.x == index.x && this.y == index.y) ? true : false;
        }
    }

    static class Node {
        Index index;
        Node next;

        public Node(Index index) {
            this.index = index;
        }
    }

    public static void main(String ar[]) {
        int matrix[][] = {{1, 0, 2, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}};
        ShortestDistanceFromAllBuildingsLeetCode unit = new ShortestDistanceFromAllBuildingsLeetCode();
        System.out.println("Shortest distance from all buildings " + unit.shortestDistance(matrix));
    }

    public int shortestDistance(int[][] grid) {
        Set<Index> buildingLocations = new HashSet();
        Set<Index> exploredIndexes = new HashSet();
        prefillBuildingLocations(grid, buildingLocations);

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                Index indexNode = new Index(i, j);
                performLevelOrderTraversal(indexNode, buildingLocations, grid, exploredIndexes);
            }
        }
        return shortestDistanceFromAllBuildings == Integer.MAX_VALUE ? -1 : shortestDistanceFromAllBuildings;
    }

    private void performLevelOrderTraversal(Index indexNode, Set<Index> buildingLocations, int [][] grid, Set<Index> exploredIndexes) {
        int reachableBuildingCount = 0;
        Set<Index> exploredBuildingIndexes = new HashSet();
        Set<Index> exploredChildIndexes = new HashSet();
        if (grid[indexNode.x][indexNode.y] == 0 && !exploredIndexes.contains(indexNode)) {
            Node node = new Node(indexNode);
            Node tail = node;
            Node head = tail;
            exploredIndexes.add(indexNode);
            exploredChildIndexes.add(indexNode);
            while (head != null) {
                List<Index> children = fetchImmediateChildren(head.index, grid, exploredIndexes);
                for (Index child : children) {
                    if (grid[child.x][child.y] == 0 && !exploredIndexes.contains(child) &&  !exploredChildIndexes.contains(child)) {
                        exploredIndexes.add(child);
                        exploredChildIndexes.add(child);
                        Node n = new Node(child);
                        tail.next = n;
                        tail = tail.next;
                    } else if (grid[child.x][child.y] == 1 && !exploredBuildingIndexes.contains(child)) {
                        reachableBuildingCount++;
                        exploredBuildingIndexes.add(child);
                    }
                }
                head = head.next;
            }
            if (buildingLocations.size() == reachableBuildingCount) {
                updateShortestDistancePath(exploredChildIndexes, exploredBuildingIndexes);
            }
        }
    }

    private void updateShortestDistancePath(Set<Index> exploredChildIndexes, Set<Index> buildingLocations) {
        int distance = 0;
        for (Index indexNode : exploredChildIndexes) {
            for (Index buildingLocation : buildingLocations) {
                distance += Math.abs(indexNode.x - buildingLocation.x) + Math.abs(indexNode.y - buildingLocation.y);
            }
            shortestDistanceFromAllBuildings = distance < shortestDistanceFromAllBuildings ? distance : shortestDistanceFromAllBuildings;
            distance = 0;
        }
    }

    private List<Index> fetchImmediateChildren(Index indexNode, int [][] grid, Set<Index> exploredIndexes) {
        List<Index> children = new ArrayList();
        if (indexNode.x + 1 < grid.length) {
            Index child = new Index(indexNode.x + 1, indexNode.y);
            if (!exploredIndexes.contains(child)) {
                children.add(child);
            }
        }

        if (indexNode.x - 1 >= 0) {
            Index child = new Index(indexNode.x - 1, indexNode.y);
            if (!exploredIndexes.contains(child)) {
                children.add(child);
            }
        }

        if (indexNode.y + 1 < grid[0].length) {
            Index child = new Index(indexNode.x, indexNode.y + 1);
            if (!exploredIndexes.contains(child)) {
                children.add(child);
            }
        }

        if (indexNode.y - 1 >= 0) {
            Index child = new Index(indexNode.x, indexNode.y - 1);
            if (!exploredIndexes.contains(child)) {
                children.add(child);
            }
        }
        return children;
    }

    private void prefillBuildingLocations(int [][] grid, Set<Index> buildingLocations) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    Index indexNode = new Index(i, j);
                    if (!buildingLocations.contains(indexNode)) {
                        buildingLocations.add(indexNode);
                    }
                }
            }
        }
    }
}
