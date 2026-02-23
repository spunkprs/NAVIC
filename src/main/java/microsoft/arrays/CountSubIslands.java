package microsoft.arrays;

import java.util.*;

public class CountSubIslands {

    private int subIslandCount = 0;
    private boolean flag = true;

    public static void main(String ar[]) {
        CountSubIslands unit = new CountSubIslands();
        int gridOne[][] = {{1,1,1,0,0}, {0,1,1,1,1}, {0,0,0,0,0}, {1,0,0,0,0}, {1,1,0,1,1}};
        int gridTwo[][] = {{1,1,1,0,0},{0,0,1,1,1},{0,1,0,0,0},{1,0,1,1,0},{0,1,0,1,0}};

        System.out.print("Number of sub islands is " + unit.countSubIslands(gridOne, gridTwo));
    }

    public int countSubIslands(int[][] grid1, int[][] grid2) {
        Set<Pair> visitedNodes = new HashSet<>();
        processToFetchIslands(grid1, grid2, visitedNodes);
        return subIslandCount;
    }

    private void processToFetchIslands(int[][] gridOne, int[][] gridTwo, Set<Pair> visitedNodes) {
        for (int i = 0; i < gridTwo.length; i++) {
            for (int j = 0; j < gridTwo[i].length; j++) {
                Pair p = new Pair(i, j);
                if (gridTwo[i][j] == 1 && !visitedNodes.contains(p)) {
                    visitedNodes.add(p);
                    process(p, visitedNodes, gridTwo, gridOne);
                    if (flag) {
                        subIslandCount++;
                    } else {
                        flag = true;
                    }
                }
            }
        }
    }

    private void process(Pair p, Set<Pair> visitedNodes, int[][] gridTwo, int gridOne[][]) {
        if (gridOne[p.indexX][p.indexY] == 0) {
            flag = false;
        }
        List<Pair> children = fetchChild(p, gridTwo, visitedNodes);
        for (Pair child : children) {
            process(child, visitedNodes, gridTwo, gridOne);
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
}
