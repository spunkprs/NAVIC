package microsoft.arrays;

import java.util.*;

public class NumberOfClosedIslands {

    private boolean isClosedIsland;
    private int islandCount;

    public static void main(String ar[]) {
        NumberOfClosedIslands unit = new NumberOfClosedIslands();
        int grid[][] = {{1,1,1,1,1,1,1,0},{1,0,0,0,0,1,1,0},{1,0,1,0,1,1,1,0},{1,0,0,0,0,1,0,1},{1,1,1,1,1,1,1,0}};

        System.out.println("Number of closed islands is " + unit.closedIsland(grid));
    }

    public int closedIsland(int[][] grid) {
        isClosedIsland = true;
        processToComputeClosedIslands(grid);
        return islandCount;
    }

    private void processToComputeClosedIslands(int[][] grid) {
        Set<Node> visitedNodes = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                Node node = new Node(i, j);
                if (grid[i][j] == 0 && !isBoundaryElement(i, j, grid)) {
                    if (!visitedNodes.contains(node)) {
                        process(node, visitedNodes, grid);
                        if (isClosedIsland) {
                            islandCount++;
                        } else {
                            isClosedIsland = true;
                        }
                    }
                }
            }
        }
    }

    private void process(Node parentNode, Set<Node> visitedNodes, int[][] grid) {
        visitedNodes.add(parentNode);
        for (Node childNode : fetchChildren(parentNode, visitedNodes, grid)) {
            process(childNode, visitedNodes, grid);
        }
    }

    private List<Node> fetchChildren(Node parentNode, Set<Node> visitedNodes, int[][] grid) {
        List<Node> childNodes = new ArrayList<>();
        if (parentNode.y + 1 < grid[0].length && grid[parentNode.x][parentNode.y + 1] == 0) {
            Node childNode = new Node(parentNode.x, parentNode.y + 1);
            if (!visitedNodes.contains(childNode)) {
                childNodes.add(childNode);
            }

            if (isBoundaryElement(childNode.x, childNode.y, grid)) {
                isClosedIsland = false;
            }
        }

        if (parentNode.y - 1 >= 0 && grid[parentNode.x][parentNode.y - 1] == 0) {
            Node childNode = new Node(parentNode.x, parentNode.y - 1);

            if (!visitedNodes.contains(childNode)) {
                childNodes.add(childNode);
            }

            if (isBoundaryElement(childNode.x, childNode.y, grid)) {
                isClosedIsland = false;
            }
        }

        if (parentNode.x + 1 < grid.length && grid[parentNode.x + 1][parentNode.y] == 0) {
            Node childNode = new Node(parentNode.x + 1, parentNode.y);

            if (!visitedNodes.contains(childNode)) {
                childNodes.add(childNode);
            }

            if (isBoundaryElement(childNode.x, childNode.y, grid)) {
                isClosedIsland = false;
            }
        }

        if (parentNode.x - 1 >= 0 && grid[parentNode.x - 1][parentNode.y] == 0) {
            Node childNode = new Node(parentNode.x - 1, parentNode.y);

            if (!visitedNodes.contains(childNode)) {
                childNodes.add(childNode);
            }

            if (isBoundaryElement(childNode.x, childNode.y, grid)) {
                isClosedIsland = false;
            }
        }
        return childNodes;
    }

    private boolean isBoundaryElement(int x, int y, int[][] grid) {
        if (x == 0 || x == grid.length - 1 || y == 0 || y == grid[0].length - 1) {
            return true;
        }
        return false;
    }

    static class Node {
        private int x;
        private int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int hashCode() {
            return Objects.hash(x, y);
        }

        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            } else {
                Node other = (Node)obj;
                return this.x == other.x && this.y == other.y;
            }
        }
    }
}
