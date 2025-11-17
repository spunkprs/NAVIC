package leetcode75;

import java.util.*;

/**
Problem : 1926

You are given an m x n matrix maze (0-indexed) with empty cells (represented as '.') and walls (represented as '+').
You are also given the entrance of the maze, where entrance = [entrancerow, entrancecol] denotes the row and column of the cell you are initially standing at.

In one step, you can move one cell up, down, left, or right. You cannot step into a cell with a wall, and you cannot step outside the maze.
Your goal is to find the nearest exit from the entrance. An exit is defined as an empty cell that is at the border of the maze. The entrance does not count as an exit.

Return the number of steps in the shortest path from the entrance to the nearest exit, or -1 if no such path exists

Constraints:-
a.) maze.length == m
b.) maze[i].length == n
c.) 1 <= m, n <= 100
d.) maze[i][j] is either '.' or '+'.
e.) entrance.length == 2
f.) 0 <= entrancerow < m
g.) 0 <= entrancecol < n
h.) entrance will always be an empty cell.

 * */

public class NearestExitFromEntrance {

    public int nearestExit(char[][] maze, int[] entrance) {
        Pair entrancePair = new Pair(entrance[0], entrance[1], 0);

        Queue<Pair> pairQueue = new LinkedList<>();
        pairQueue.add(entrancePair);
        Set<Pair> visitedNodes = new HashSet<>();
        visitedNodes.add(entrancePair);
        int result = 0;

        while (!pairQueue.isEmpty()) {
            Pair peekedNode = pairQueue.peek();
            if ((peekedNode.x == 0 || peekedNode.x == maze.length - 1 || peekedNode.y == 0 || peekedNode.y == maze[0].length - 1)
                    && (peekedNode.x != entrancePair.x || peekedNode.y != entrancePair.y)) {
                result = peekedNode.distance;
                break;
            }
            for (Pair p : fetchChildren(peekedNode, maze, visitedNodes)) {
                pairQueue.add(p);
                visitedNodes.add(p);
            }
            pairQueue.poll();
        }

        return result == 0 ? -1 : result;
    }

    private List<Pair> fetchChildren(Pair peekedNode, char[][] maze, Set<Pair> visitedNodes) {
        List<Pair> children = new ArrayList<>();

        if (peekedNode.x + 1 < maze.length && maze[peekedNode.x + 1][peekedNode.y] == '.') {
            Pair childNode = new Pair(peekedNode.x + 1, peekedNode.y, peekedNode.distance + 1);
            if (!visitedNodes.contains(childNode)) {
                children.add(childNode);
            }
        }

        if (peekedNode.y + 1 < maze[0].length && maze[peekedNode.x][peekedNode.y + 1] == '.') {
            Pair childNode = new Pair(peekedNode.x, peekedNode.y + 1, peekedNode.distance + 1);
            if (!visitedNodes.contains(childNode)) {
                children.add(childNode);
            }
        }

        if (peekedNode.x - 1 >= 0 && maze[peekedNode.x - 1][peekedNode.y] == '.') {
            Pair childNode = new Pair(peekedNode.x - 1, peekedNode.y, peekedNode.distance + 1);
            if (!visitedNodes.contains(childNode)) {
                children.add(childNode);
            }
        }

        if (peekedNode.y - 1 >= 0 && maze[peekedNode.x][peekedNode.y - 1] == '.') {
            Pair childNode = new Pair(peekedNode.x, peekedNode.y - 1, peekedNode.distance + 1);
            if (!visitedNodes.contains(childNode)) {
                children.add(childNode);
            }
        }
        return children;
    }

    static class Pair {
        private int x;
        private int y;
        private int distance;

        public Pair(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return x == pair.x && y == pair.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
