package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 You are given an m x n grid rooms initialized with these three possible values.

 1.) -1 A wall or an obstacle.
 2.)  0 A gate.
 3.) INF Infinity means an empty room. We use the value pow(2,31) - 1 = 2147483647 to represent INF as you may assume that the
 distance to a gate is less than 2147483647.
 Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF

 Constraints:

 1.) m == rooms.length
 2.) n == rooms[i].length
 3.) 1 <= m, n <= 250
 4.) rooms[i][j] is -1, 0, or pow(2,31) - 1.

 Time Complexity = O(m * n * k), where k = number of indexes with value = 0 i.e Gate
 Space Complexity = O(m * n)

 Source : LeetCode

 * */

public class WallsAndGates {

    public static void main(String ar[]) {
        WallsAndGates unit = new WallsAndGates();
        int rooms[][] = {{2147483647,-1,0,2147483647},{2147483647,2147483647,2147483647,-1},{2147483647,-1,2147483647,-1},{0,-1,2147483647,2147483647}};
        unit.wallsAndGates(rooms);
        System.out.println("Updated values in the rooms are " + rooms);
    }

    public void wallsAndGates(int[][] rooms) {
        List<Position> positionOfGates = new ArrayList<>();

        populatePositionOfGates(rooms, positionOfGates);

        for (Position position : positionOfGates) {
            updateDistance(position, rooms);
        }
    }

    private void updateDistance(Position position, int[][] rooms) {
        Queue<Position> queue = new LinkedList<>();
        queue.add(position);
        while (!queue.isEmpty()) {
            for (Position child : fetchChildren(queue.peek(), rooms)) {
                if (child.val < rooms[child.x][child.y]) {
                    queue.add(child);
                    rooms[child.x][child.y] = child.val;
                }
            }
            queue.poll();
        }
    }

    private List<Position> fetchChildren(Position position, int[][] rooms) {
        List<Position> children = new ArrayList<>();

        if (position.x + 1 < rooms.length && rooms[position.x + 1][position.y] != -1 && rooms[position.x + 1][position.y] != 0) {
            children.add(new Position(position.x + 1, position.y, position.val + 1));
        }

        if (position.x - 1 >= 0 && rooms[position.x - 1][position.y] != -1 && rooms[position.x - 1][position.y] != 0) {
            children.add(new Position(position.x - 1, position.y, position.val + 1));
        }

        if (position.y + 1 < rooms[0].length && rooms[position.x][position.y + 1] != -1 && rooms[position.x][position.y + 1] != 0) {
            children.add(new Position(position.x, position.y + 1, position.val + 1));
        }

        if (position.y - 1 >= 0 && rooms[position.x][position.y - 1] != -1 && rooms[position.x][position.y - 1] != 0) {
            children.add(new Position(position.x, position.y - 1, position.val + 1));
        }
        return children;
    }

    private void populatePositionOfGates(int[][] rooms, List<Position> positionOfGates) {
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[i].length; j++) {
                if (rooms[i][j] == 0) {
                    positionOfGates.add(new Position(i, j, 0));
                }
            }
        }
    }


    static class Position {
        private int x;
        private int y;
        private int val;

        public Position(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }


}
