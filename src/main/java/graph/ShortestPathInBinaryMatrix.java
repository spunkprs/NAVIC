package graph;

import java.util.*;

/**
 Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.

 A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:

 All the visited cells of the path are 0.
 All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).
 The length of a clear path is the number of visited cells of this path.

 Constraints:-

 1.) n == grid.length
 2.) n == grid[i].length
 3.) 1 <= n <= 100
 4.) grid[i][j] is 0 or 1

 Source : Leetcode

 * */

public class ShortestPathInBinaryMatrix {

    public static void main(String ar[]) {
        int grid[][] = {{0,0,0},{1,0,0},{1,0,0}};
        ShortestPathInBinaryMatrix unit = new ShortestPathInBinaryMatrix();
        System.out.print("Shortest path to reach end of matrix is : " + unit.shortestPathBinaryMatrix(grid));
    }

    public int shortestPathBinaryMatrix(int[][] grid) {

        boolean solutionFound = false;
        int minDepth = -1;

        if (grid[0][0] == 1) {
            return -1;
        } else if (grid.length == 1 && grid[0].length == 1 && grid[0][0] == 0){
            return 1;
        } else {
            Set<Node> visitedNodes = new HashSet<>();
            Node startingNode = new Node(0, 0, 1);
            Queue<Node> queue = new LinkedList<>();
            queue.add(startingNode);
            visitedNodes.add(startingNode);

            while (!queue.isEmpty()) {
                Node peekedNode = queue.peek();
                 if (peekedNode.xCoordinate == grid.length - 1 && peekedNode.yCoordinate == grid[0].length - 1
                        && grid[peekedNode.xCoordinate][peekedNode.yCoordinate] == 0) {
                    minDepth = peekedNode.depth;
                    solutionFound = true;
                    break;
                } else {
                         List<Node> children = fetchNonVisistedChildren(peekedNode, visitedNodes, grid);
                         for (Node child : children) {
                             queue.add(child);
                             visitedNodes.add(child);
                         }
                         queue.poll();
                }
            }
            return solutionFound ? minDepth : -1;
        }
    }

    private List<Node> fetchNonVisistedChildren(Node peekedNode, Set<Node> visitedNodes, int grid[][]) {
        List<Node> childrenNodes = new ArrayList<>();

        if (peekedNode.yCoordinate + 1 < grid[0].length) {
            Node node = new Node(peekedNode.xCoordinate, peekedNode.yCoordinate + 1, peekedNode.depth + 1);
            addNonVisistedChildren(childrenNodes, node, visitedNodes, grid);
        }

        if (peekedNode.xCoordinate + 1 < grid.length) {
            Node node = new Node(peekedNode.xCoordinate + 1, peekedNode.yCoordinate, peekedNode.depth + 1);
            addNonVisistedChildren(childrenNodes, node, visitedNodes, grid);
        }

        if (peekedNode.yCoordinate - 1 >= 0) {
            Node node = new Node(peekedNode.xCoordinate, peekedNode.yCoordinate - 1, peekedNode.depth + 1);
            addNonVisistedChildren(childrenNodes, node, visitedNodes, grid);
        }

        if (peekedNode.xCoordinate - 1 >= 0) {
            Node node = new Node(peekedNode.xCoordinate - 1, peekedNode.yCoordinate, peekedNode.depth + 1);
            addNonVisistedChildren(childrenNodes, node, visitedNodes, grid);
        }

        if (peekedNode.xCoordinate + 1 < grid.length && peekedNode.yCoordinate + 1 < grid[0].length) {
            Node node = new Node(peekedNode.xCoordinate + 1, peekedNode.yCoordinate + 1, peekedNode.depth + 1);
            addNonVisistedChildren(childrenNodes, node, visitedNodes, grid);
        }

        if (peekedNode.xCoordinate - 1 >=0 && peekedNode.yCoordinate - 1 >=0) {
            Node node = new Node(peekedNode.xCoordinate - 1, peekedNode.yCoordinate - 1, peekedNode.depth + 1);
            addNonVisistedChildren(childrenNodes, node, visitedNodes, grid);
        }

        if (peekedNode.xCoordinate - 1 >=0 && peekedNode.yCoordinate + 1 < grid[0].length) {
            Node node = new Node(peekedNode.xCoordinate - 1, peekedNode.yCoordinate + 1, peekedNode.depth + 1);
            addNonVisistedChildren(childrenNodes, node, visitedNodes, grid);
        }

        if (peekedNode.xCoordinate + 1 < grid.length && peekedNode.yCoordinate - 1 >=0) {
            Node node = new Node(peekedNode.xCoordinate + 1, peekedNode.yCoordinate - 1, peekedNode.depth + 1);
            addNonVisistedChildren(childrenNodes, node, visitedNodes, grid);
        }

        return childrenNodes;
    }

    private void addNonVisistedChildren(List<Node> childrenNodes, Node node, Set<Node> visitedNodes, int grid[][]) {
        if (grid[node.xCoordinate][node.yCoordinate] == 0 && !visitedNodes.contains(node)) {
            childrenNodes.add(node);
        }
    }


    static class Node {
        private int xCoordinate;
        private int yCoordinate;
        private int depth;

        public Node(int xCoordinate, int yCoordinate, int depth) {
            this.xCoordinate = xCoordinate;
            this.yCoordinate = yCoordinate;
            this.depth = depth;
        }

        public int hashCode() {
            return Objects.hash(xCoordinate, yCoordinate);
        }

        public boolean equals(Object object) {
            Node another = (Node)object;
            return this.xCoordinate == another.xCoordinate && this.yCoordinate == another.yCoordinate;
        }
    }
}
