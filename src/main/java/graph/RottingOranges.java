package graph;


import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Objects;

/**
 You are given an m x n grid where each cell can have one of three values:

 a.) 0 representing an empty cell,
 b.) 1 representing a fresh orange, or
 c.) 2 representing a rotten orange.

 Condition --> Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.

 Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.

 Source --> Leetcode

 Note : Important thing to note here is multiple rotten oranges can be present in the matrix which can lead to faster rate at which
 all the fresh oranges will become rotten {Reason enough to make use of BFS instead of DFS}
 * */

public class RottingOranges {

    private Node head = null;
    private Node tail = null;

    public static void main(String ar[]) {
        RottingOranges unit = new RottingOranges();

        int grid[][] = {{2, 1, 1}, {1, 1, 0}, {1, 1, 2}};

        System.out.println("Minimum time in which all fresh oranges will be rotten is " + unit.orangesRotting(grid));
    }


    public int orangesRotting(int[][] grid) {
        //Maintain queue of oranges which are already rotten
        Set<Node> freshOranges = processToFindRottenOrangesAndMarkFreshOranges(grid);

        if (freshOranges.isEmpty()) {
            return 0;
        } else if (head == null) {
            return -1;
        }

        int minimumTime = processToComputeTimeForFreshOrangesToBecomeRotten(freshOranges, grid);
        return freshOranges.size() > 0 ? -1 : minimumTime;
    }

    private int processToComputeTimeForFreshOrangesToBecomeRotten(Set<Node> freshOranges, int[][] grid) {
        int minimumTime = -1;
        Node traversalNode = head;
        //Set<Node> adjacentNodes = new HashSet<>();

        while (traversalNode != null) {
            List<Node> resultantNodes = findAdjacentNodes(traversalNode, grid);
            for (Node node : resultantNodes) {
                if (freshOranges.contains(node)) {
                    freshOranges.remove(node);
                }
                tail.next = node;
                node.previous = tail;
                tail = node;
            }
            minimumTime = traversalNode.time > minimumTime ? traversalNode.time : minimumTime;
            traversalNode = traversalNode.next;
        }
        return minimumTime;
    }

    private List<Node> findAdjacentNodes(Node traversalNode, int[][] grid) {

        List<Node> resultantNodes = new ArrayList<>();

        if (traversalNode.coordinateX + 1 < grid.length
                && grid[traversalNode.coordinateX + 1][traversalNode.coordinateY] == 1) {
            Node adjacentNode = new Node(traversalNode.coordinateX + 1, traversalNode.coordinateY, 2, traversalNode.time + 1);
            grid[traversalNode.coordinateX + 1][traversalNode.coordinateY] = 2;
            resultantNodes.add(adjacentNode);
        }

        if (traversalNode.coordinateX - 1 >= 0
                && grid[traversalNode.coordinateX - 1][traversalNode.coordinateY] == 1) {
            Node adjacentNode = new Node(traversalNode.coordinateX - 1, traversalNode.coordinateY, 2, traversalNode.time + 1);
            grid[traversalNode.coordinateX - 1][traversalNode.coordinateY] = 2;
            resultantNodes.add(adjacentNode);
        }

        if (traversalNode.coordinateY + 1 < grid[0].length
                && grid[traversalNode.coordinateX][traversalNode.coordinateY + 1] == 1) {
            Node adjacentNode = new Node(traversalNode.coordinateX, traversalNode.coordinateY + 1, 2, traversalNode.time + 1);
            grid[traversalNode.coordinateX][traversalNode.coordinateY + 1] = 2;
            resultantNodes.add(adjacentNode);
        }

        if (traversalNode.coordinateY - 1 >= 0
                && grid[traversalNode.coordinateX][traversalNode.coordinateY - 1] == 1) {
            Node adjacentNode = new Node(traversalNode.coordinateX, traversalNode.coordinateY - 1, 2, traversalNode.time + 1);
            grid[traversalNode.coordinateX][traversalNode.coordinateY - 1] = 2;
            resultantNodes.add(adjacentNode);
        }
        return resultantNodes;
    }

    private Set<Node> processToFindRottenOrangesAndMarkFreshOranges(int[][] grid) {
        Set<Node> freshOranges = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    Node rottenOrangeNode = new Node(i, j, 2, 0);
                    if (head == null && tail == null) {
                        head = rottenOrangeNode;
                        tail = head;
                    } else {
                        tail.next = rottenOrangeNode;
                        rottenOrangeNode.previous = tail;
                        tail = rottenOrangeNode;
                    }
                } else if (grid[i][j] == 1) {
                    freshOranges.add(new Node(i, j, 1, -1));
                }
            }
        }
        return freshOranges;
    }


    static class Node {
        private int coordinateX;
        private int coordinateY;
        private int time;
        private int state;

        private Node next;
        private Node previous;

        public Node(int coordinateX, int coordinateY, int state, int time) {
            this.coordinateX = coordinateX;
            this.coordinateY = coordinateY;
            this.time = time;
            this.state = state;
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
