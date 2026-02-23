package microsoft.arrays;

import java.util.*;

/**
You are given an m x n binary matrix grid. An island is a group of 1's (representing land)
connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

An island is considered to be the same as another if and only if one island can be translated (and not rotated or reflected) to equal the other.

Return the number of distinct islands.
 * */

public class NumberOfDistinctIslands {

    public static void main(String ar[]) {
        NumberOfDistinctIslands unit = new NumberOfDistinctIslands();
        int grid[][] = {{1,1,0,1,1}, {1,0,0,0,0}, {0,0,0,0,1}, {1,1,0,1,1}};
        System.out.println("Number of unique islands is " + unit.numDistinctIslands(grid));
    }

    public int numDistinctIslands(int[][] grid) {
        Set<Pair> visitedNodes = new HashSet<>();
        Set<Node> distinctIslands = new HashSet<>();
        processToFetchIslands(grid, visitedNodes, distinctIslands);
        return distinctIslands.size();
    }

    private void processToFetchIslands(int[][] grid, Set<Pair> visitedNodes, Set<Node> distinctIslands) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                Pair p = new Pair(i, j);
                if (grid[i][j] == 1 && !visitedNodes.contains(p)) {
                    visitedNodes.add(p);
                    Node createdNode = createNode(p);
                    process(p, visitedNodes, grid, createdNode);
                    distinctIslands.add(createdNode);
                }
            }
        }
    }

    private Node createNode(Pair p) {
        Map<Integer, Integer> resultMap = new HashMap<>();
        Map<Integer, Integer> pairMap = new HashMap<>();
        Set<Integer> intermittentSet = new HashSet<>();
        Node node = new Node(resultMap, pairMap, intermittentSet);
        if (intermittentSet.isEmpty()) {
            intermittentSet.add(p.indexX);
            pairMap.put(p.indexX, 1);
            resultMap.put(1, 1);
        }
        return node;
    }

    private void process(Pair p, Set<Pair> visitedNodes, int[][] grid, Node createdNode) {
        List<Pair> children = fetchChild(p, grid, visitedNodes);
        for (Pair child : children) {
            if (createdNode.intermittentSet.contains(child.indexX)) {
                int providedRowNum = createdNode.pairMap.get(child.indexX);
                createdNode.resultMap.put(providedRowNum, createdNode.resultMap.get(providedRowNum) + 1);
            } else {
                int providedRowNum = createdNode.pairMap.get(p.indexX);
                createdNode.pairMap.put(child.indexX, child.indexX > p.indexX ? providedRowNum + 1 : providedRowNum - 1);
                createdNode.resultMap.put(child.indexX > p.indexX ? providedRowNum + 1 : providedRowNum - 1, 1);
                createdNode.intermittentSet.add(child.indexX);
            }
            process(child, visitedNodes, grid, createdNode);
        }
    }

    private List<Pair> fetchChild(Pair p, int[][] grid, Set<Pair> visitedNodes) {
        List<Pair> children = new ArrayList<>();
        if (p.indexY + 1 < grid[0].length) {
            Pair child = new Pair(p.indexX, p.indexY + 1);
            if (grid[p.indexX][p.indexY + 1] == 1 && !visitedNodes.contains(child)) {
                children.add(child);
                visitedNodes.add(child);
            }
        }

        if (p.indexY - 1 >= 0) {
            Pair child = new Pair(p.indexX, p.indexY - 1);
            if (grid[p.indexX][p.indexY - 1] == 1 && !visitedNodes.contains(child)) {
                children.add(child);
                visitedNodes.add(child);
            }
        }

        if (p.indexX + 1 < grid.length) {
            Pair child = new Pair(p.indexX + 1, p.indexY);
            if (grid[p.indexX + 1][p.indexY] == 1 && !visitedNodes.contains(child)) {
                children.add(child);
                visitedNodes.add(child);
            }
        }

        if (p.indexX - 1 >= 0) {
            Pair child = new Pair(p.indexX - 1, p.indexY);
            if (grid[p.indexX - 1][p.indexY] == 1 && !visitedNodes.contains(child)) {
                children.add(child);
                visitedNodes.add(child);
            }
        }
        return children;
    }

    static class Pair {
        private int indexX;
        private int indexY;

        public Pair(int indexX, int indexY) {
            this.indexX = indexX;
            this.indexY = indexY;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return indexX == pair.indexX && indexY == pair.indexY;
        }

        @Override
        public int hashCode() {
            return Objects.hash(indexX, indexY);
        }
    }

    static class Node {
        private Map<Integer, Integer> resultMap;
        private Map<Integer, Integer> pairMap;
        private Set<Integer> intermittentSet;


        public Node(Map<Integer, Integer> resultMap, Map<Integer, Integer> pairMap, Set<Integer> intermittentSet) {
            this.resultMap = resultMap;
            this.pairMap = pairMap;
            this.intermittentSet = intermittentSet;
        }

        @Override
        public boolean equals(Object o) {
            Node externalNode = (Node)o;
            if (this.resultMap.size() != externalNode.resultMap.size()) {
                return false;
            } else {
                boolean result = true;
                for (Integer key : externalNode.resultMap.keySet()) {
                    if (!this.resultMap.containsKey(key)) {
                        result = false;
                        break;
                    } else if (this.resultMap.get(key) != externalNode.resultMap.get(key)) {
                        result = false;
                        break;
                    }
                }
                return result;
            }
        }

        @Override
        public int hashCode() {
            return Objects.hash(resultMap.size());
        }
    }
}
